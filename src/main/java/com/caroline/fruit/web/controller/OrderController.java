package com.caroline.fruit.web.controller;

import com.caroline.fruit.core.util.FruitUtil;
import com.caroline.fruit.core.util.OrderStatus;
import com.caroline.fruit.dto.AddOrderCommodityForm;
import com.caroline.fruit.dto.AddOrderDto;
import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.exception.FruitMsgEnum;
import com.caroline.fruit.message.Result;
import com.caroline.fruit.model.*;
import com.caroline.fruit.projection.OrderList;
import com.caroline.fruit.service.CouponFlowService;
import com.caroline.fruit.service.DeliveryInfoService;
import com.caroline.fruit.service.OrderService;
import com.caroline.fruit.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    public final UserService userService;

    public final DeliveryInfoService deliveryInfoService;

    public final OrderService orderService;

    public final CouponFlowService couponFlowService;

    public final FruitUtil fruitUtil;

    @Autowired
    public OrderController(UserService userService,
                           DeliveryInfoService deliveryInfoService,
                           OrderService orderService,
                           CouponFlowService couponFlowService,
                           FruitUtil fruitUtil){
        this.userService = userService;
        this.deliveryInfoService = deliveryInfoService;
        this.orderService = orderService;
        this.couponFlowService = couponFlowService;
        this.fruitUtil = fruitUtil;

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
                                             @RequestParam("size") Integer size) throws FruitException{

        Result result = orderService.findAllOrderListByPageable(PageRequest.of(page,size));
        Page<Order> orderPage = (Page<Order>) result.getResponseReplyInfo();

        //传给页面的order
        List<OrderList> orderList = new ArrayList<>();
        orderPage.getContent().forEach(
                order -> {
                    OrderList orderDto = new OrderList();
                    //订单Id
                    orderDto.setOrderId(order.getOrderId());

                    //订单状态
                    orderDto.setOrderStatus(OrderStatus.OrderStatusEnum.getOrderMessage(
                            order.getOrderStatus()));

                    //订单日期
                    orderDto.setOrderCreationDate(order.getCreationDate());

                    //商品名称
                    orderDto.setCommodityName(
                            order.getOrderCommodity().getCommodityId().getCommodityName());

                    //付款人名称
                    orderDto.setPayerName(order.getPayer().getRealName());
                    orderDto.setPayerId(order.getPayer().getId());

                    //收货人名称
                    orderDto.setDeliveryName(order.getDelivery().getDeliveryName());
                    orderDto.setDeliveryId(order.getDelivery().getInfoId());

                    //商品总价
                    BigDecimal costPrice = order.getOrderCommodity().getCommodityId().getCommodityPrice();

                    orderDto.setOrderTotalPrice(costPrice);
                    /*//优惠券面额
                    BigDecimal quota = order.getOrderCommodity().getCoupon().getCouponType().getQuota();
                    //商品折扣
                    BigDecimal discount = order.getOrderCommodity().getDiscount();
                    //邮费(运费)
                    BigDecimal freight = order.getOrderCommodity().getFreight();
                    orderDto.setOrderTotalPrice(null);*/
                    //商品数目
                    Integer commodityNum = order.getOrderCommodity().getCommodityNum();
                    orderDto.setCommodityNum(commodityNum);

                    orderList.add(orderDto);
                }

        );

        return fruitUtil.getResult(orderList, orderPage);
    }
}
