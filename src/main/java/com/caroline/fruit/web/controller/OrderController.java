package com.caroline.fruit.web.controller;

import com.caroline.fruit.core.util.FruitUtil;
import com.caroline.fruit.core.util.OrderStatus.*;
import com.caroline.fruit.dto.AddOrderDto;
import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.exception.FruitMsgEnum;
import com.caroline.fruit.message.Result;
import com.caroline.fruit.model.ExpressInfo;
import com.caroline.fruit.model.Order;
import com.caroline.fruit.model.RedeliverOrder;
import com.caroline.fruit.projection.OrderList;
import com.caroline.fruit.repository.ExpressInfoRepository;
import com.caroline.fruit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    public final UserService userService;

    public final DeliveryInfoService deliveryInfoService;

    public final OrderService orderService;

    public final CouponFlowService couponFlowService;

    public final FruitUtil fruitUtil;

    public final RedeliverOrderService redeliverOrderService;

    @Autowired
    public OrderController(UserService userService,
                           DeliveryInfoService deliveryInfoService,
                           OrderService orderService,
                           CouponFlowService couponFlowService,
                           FruitUtil fruitUtil,
                           RedeliverOrderService redeliverOrderService){
        this.userService = userService;
        this.deliveryInfoService = deliveryInfoService;
        this.orderService = orderService;
        this.couponFlowService = couponFlowService;
        this.fruitUtil = fruitUtil;
        this.redeliverOrderService = redeliverOrderService;

    }
    @PostMapping("")
    public Result addOrder(@RequestBody AddOrderDto addOrderDto) throws FruitException {

        if(addOrderDto.getCommodities() == null || addOrderDto.getCommodities().isEmpty() ){

            throw new FruitException(FruitMsgEnum.AddOrderNoCommodity);
        }else{
             return orderService.addOrder(addOrderDto);
        }
    }

    @GetMapping("/list")
    public Result findAllOrderListByPageable(@RequestParam("page") Integer page,
                                             @RequestParam("size") Integer size,
                                             @RequestParam(value = "orderVal",required = false) String orderVal) throws FruitException{

        Result result = orderService.findAllOrderListByPageable(PageRequest.of(page,size, Sort.by("orderStatus")), orderVal);
        Page<Order> orderPage = (Page<Order>) result.getResponseReplyInfo();

        //传给页面的order
        List<OrderList> orderList = new ArrayList<>();
        orderPage.getContent().forEach(
                order -> {
                    OrderList orderDto = fruitUtil.orderToOrderList(order);
                    orderList.add(orderDto);
                }
        );

        return fruitUtil.getResult(orderList, orderPage);
    }

    /**
     * 根据订单id或快递单号获取订单详情
     * */
    @GetMapping("/{orderExpressNo}")
    public Result getOrderDetail(@PathVariable("orderExpressNo") String orderExpressNo) throws FruitException{
        try {
             return orderService.findByOrderId(orderExpressNo);

        }catch (Exception e){
            throw new FruitException(FruitMsgEnum.Exception);
        }
    }

    @PutMapping
    public Result updateOrderInfo(@RequestBody AddOrderDto addOrderDto) throws FruitException{
        try{

            Result orderResult = orderService.findOrderByOrderId(addOrderDto.getOrderId());
            Order order = (Order) orderResult.getResponseReplyInfo();

            //设置付款状态
            order.setIsPay(addOrderDto.getIsPay());

            //设置售后方式
            order.setIsAfterMarket(addOrderDto.getIsAfterMarket());

            //判断是否需要售后-需要售后
            if(addOrderDto.getIsAfterMarket() != null && addOrderDto.getIsAfterMarket()){
                //获取售后处理方式
                Integer afterMarketWay = addOrderDto.getAfterMarketWay();

                order.setAfterMarketWay(addOrderDto.getAfterMarketWay());

                //售后 状态为非拒绝时应该将拒绝理由设置为空
                order.setRefuseReason(null);
                //0 -拒绝
                if(AfterMarketWay.REFUSE.getStatus() == afterMarketWay){

                    //设置拒绝理由
                    order.setRefuseReason(addOrderDto.getRefuseReason());

                }else if(AfterMarketWay.REFUND.getStatus() == afterMarketWay){

                    //1 - 退款(订单状态设置为已退款)
                    order.setOrderStatus(OrderStatusEnum.REFUNDED.getStatus());
                }else if(AfterMarketWay.RESEND.getStatus() == afterMarketWay){

                    //2 - 重发
                    RedeliverOrder redeliverOrder = new RedeliverOrder();
                    redeliverOrder.setRedeliverOrderId(fruitUtil.getTableId());
                    redeliverOrder.setOrderId(addOrderDto.getOrderId());
                    redeliverOrder.setExpressCompany(addOrderDto.getUpdateExpressCompany());
                    redeliverOrder.setExpressNo(addOrderDto.getUpdateExpressNo());

                    redeliverOrderService.saveRedeliverOrderService(redeliverOrder);
                }else {
                    throw new FruitException(FruitMsgEnum.IllegalParameter);
                }

            }else {
                //不需要售后时如果订单状态为签收或好评以及完成时支付状态必须为已支付
                if((OrderStatusEnum.RECEIVED.getStatus() == addOrderDto.getOrderStatus() ||
                        OrderStatusEnum.PRAISED.getStatus() == addOrderDto.getOrderStatus() ||
                        OrderStatusEnum.FINISH.getStatus() == addOrderDto.getOrderStatus()) &&
                        !addOrderDto.getIsPay()){

                    throw new FruitException(FruitMsgEnum.OrderNotPay);

                }else {

                    List<String> expressNoList =
                            fruitUtil.stringToList(addOrderDto.getUpdateExpressNo(),";");

                    if( expressNoList != null && !expressNoList.isEmpty()){

                        List<ExpressInfo> expressInfos = new ArrayList<>();

                        expressNoList.forEach( e->{
                            ExpressInfo expressInfo = new ExpressInfo();

                            expressInfo.setExpressNo(e);

                            expressInfo.setExpressCompany(
                                    addOrderDto.getUpdateExpressCompany());

                            expressInfo.setExpressInfoId(fruitUtil.getTableId());

                            expressInfos.add(expressInfo);
                        });
                        order.getOrderCommodity().setExpressInfo(expressInfos);
                    }
                    order.setOrderStatus(addOrderDto.getOrderStatus());
                }


            }
            order.setRemark(addOrderDto.getRemark());

            order = orderService.addOrder(order);

            if(order == null){

                throw new FruitException(FruitMsgEnum.UpdateOrderFailed);
            }else {

                return new Result(FruitMsgEnum.UpdateOrderSuccess);
            }
        }catch (Exception e){
            e.getStackTrace();

            throw new FruitException(FruitMsgEnum.Exception);
        }
    }
}
