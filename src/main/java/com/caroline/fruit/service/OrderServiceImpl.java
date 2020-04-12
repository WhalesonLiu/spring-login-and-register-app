package com.caroline.fruit.service;

import com.caroline.fruit.core.util.FruitUtil;
import com.caroline.fruit.dto.AddOrderCommodityForm;
import com.caroline.fruit.dto.AddOrderDto;
import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.exception.FruitMsgEnum;
import com.caroline.fruit.message.Result;
import com.caroline.fruit.model.*;
import com.caroline.fruit.model.express.OrderTracking;
import com.caroline.fruit.projection.OrderExpressInfo;
import com.caroline.fruit.projection.OrderList;
import com.caroline.fruit.repository.ExpressInfoRepository;
import com.caroline.fruit.repository.OrderCommodityRepository;
import com.caroline.fruit.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {


    public final UserService userService;

    public final OrderRepository orderRepository;

    public final CommodityService commodityService;

    public final OrderCommodityRepository orderCommodityRepository;

    public final DeliveryInfoService deliveryInfoService;

    public final CouponFlowService couponFlowService;

    public final FruitUtil fruitUtil;

    private final RestTemplate restTemplate;

    private final ExpressInfoRepository expressInfoRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderCommodityRepository orderCommodityRepository,
                            ExpressInfoRepository expressInfoRepository,
                            DeliveryInfoService deliveryInfoService,
                            CouponFlowService couponFlowService,
                            CommodityService commodityService,
                            RestTemplate restTemplate,
                            UserService userService,
                            FruitUtil fruitUtil){
        this.fruitUtil = fruitUtil;
        this.userService = userService;
        this.restTemplate = restTemplate;
        this.orderRepository = orderRepository;
        this.commodityService = commodityService;
        this.couponFlowService = couponFlowService;
        this.deliveryInfoService = deliveryInfoService;
        this.expressInfoRepository = expressInfoRepository;
        this.orderCommodityRepository = orderCommodityRepository;
    }

    @Transactional
    @Override
    public Result addOrder(AddOrderDto addOrderDto) throws FruitException {

        try {
            Order order = new Order();

            BeanUtils.copyProperties(addOrderDto,order);

            //当前登录的用户-->商家
            User loggedInUser = userService.getLoggedInUser();

            if (loggedInUser == null){

                throw new FruitException(FruitMsgEnum.NoLoggedInUser);

            }

            //设置商家
            order.setMerchant(loggedInUser);

            //设置付款人
            Result payerResult = userService.findByWeChatAccount(addOrderDto.getWeChatAccount());
            User payer = (User)payerResult.getResponseReplyInfo();
            order.setPayer(payer);

            //设置收货人
            Result deliveryInfoResult = deliveryInfoService.findByInfoId(addOrderDto.getDeliveryId());
            DeliveryInfo deliveryInfo = (DeliveryInfo) deliveryInfoResult.getResponseReplyInfo();
            order.setDelivery(deliveryInfo);

            //订单中的商品信息
            //Set<OrderCommodity> orderCommoditySet = new HashSet<>();

            if(addOrderDto.getCommodities() == null || addOrderDto.getCommodities().isEmpty() ){

                throw new FruitException(FruitMsgEnum.AddOrderNoCommodity);
            }else{

                for(AddOrderCommodityForm addOrderCommodityForm :
                        addOrderDto.getCommodities()){

                    OrderCommodity orderCommodity = new OrderCommodity();

                    orderCommodity.setOrderCommodityId(FruitUtil.getId());
                    //订单设置id
                    order.setOrderId(fruitUtil.getOrderNo());

                    BeanUtils.copyProperties(addOrderCommodityForm, orderCommodity);

                    if( !StringUtils.isEmpty(addOrderCommodityForm.getExpressNo())){

                        List<String> expressNoList =
                                fruitUtil.stringToList(addOrderCommodityForm.getExpressNo(),";");

                        List<ExpressInfo> expressInfos = new ArrayList<>();

                        //该订单的快递信息
                        expressNoList.forEach(e -> {
                            ExpressInfo expressInfo = new ExpressInfo();
                            expressInfo.setExpressInfoId(fruitUtil.getTableId());

                            //设置快递公司名称
                            expressInfo.setExpressCompany(
                                    addOrderCommodityForm.getExpressCompany());

                            //设置快递单号
                            expressInfo.setExpressNo(
                                    e);

                            expressInfos.add(expressInfo);
                        });

                        //设置该订单的快递信息
                        orderCommodity.setExpressInfo(expressInfos);

                    }
                    //查询该用户是否有该优惠券
                    /*if(addOrderCommodityForm.getFreight() != null){
                        orderCommodity.setFreight(
                                fruitUtil.getBigDecimal(addOrderCommodityForm.getFreight()));
                    }*/

                    CouponFlow couponFlow =
                            couponFlowService.findCouponByIdAndUserId(addOrderCommodityForm.
                                    getCouponId(), payer.getId());
                    //减少优惠券数量
                    if(couponFlow != null && couponFlow.getNum() > 0){
                        couponFlow.setNum(couponFlow.getNum() -1);
                        //更新优惠券的数量
                        couponFlowService.addCoupon(couponFlow);
                    }

                    //设置订单状态

                    //
                    Commodity commodity =
                            commodityService.getCommodityById(addOrderCommodityForm.getCommodityId());
                    orderCommodity.setCommodityId(commodity);

                    orderCommodityRepository.save(orderCommodity);
                    //添加订单(一个商品是一个订单)
                    order.setOrderCommodity(orderCommodity);
                    orderRepository.save(order);
                }
            }

            return new Result(FruitMsgEnum.AddOrderSuccess);
        }catch (Exception e){

            e.getStackTrace();
            throw new FruitException(FruitMsgEnum.Exception);
        }
    }

    @Override
    @Transactional
    public Order addOrder(Order order) throws FruitException {
        try{
            expressInfoRepository.deleteByOrderExpressId(
                    order.getOrderCommodity().getOrderCommodityId());

            return orderRepository.save(order);
        }catch (Exception e){
            e.getStackTrace();
            throw new FruitException(FruitMsgEnum.Exception);
        }
    }

    @Override
    public void addOrderCommodity(OrderCommodity orderCommodity) throws FruitException {
        try {

            if(orderCommodity != null){

                orderCommodity.setOrderCommodityId(FruitUtil.getId());

                orderCommodity = orderCommodityRepository.save(orderCommodity);
                if(orderCommodity == null){
                    throw new FruitException(FruitMsgEnum.AddOrderFailed);
                }

            }else{
                throw new FruitException(FruitMsgEnum.ParamEmpty);
            }
        }catch (Exception e){
            throw new FruitException(FruitMsgEnum.Exception);
        }

    }

    @Override
    public Result findAllOrderListByPageable(PageRequest pageRequest, String orderVal) throws FruitException {
        try {

            Result result = new Result();
            Page<Order> orders =  null;

            if( StringUtils.isEmpty(orderVal)){

                orders = orderRepository.findAll(pageRequest);
            }else{

                orders = orderRepository.findAllByOrderIdContains(pageRequest,orderVal);

                if(orders  == null || orders.getContent() == null ||
                        orders.getContent().isEmpty()){

                    orders = orderRepository.findAllByOrderCommodity_ExpressInfo_ExpressNo(
                            pageRequest,orderVal);

                }
            }

            if( orders != null && orders.getContent() != null){

                result.setResponseReplyInfo(orders);

                return result;
            }else {
                throw new FruitException(FruitMsgEnum.NotFoundOrderList);
            }
        }catch (Exception e){
            throw new FruitException(FruitMsgEnum.Exception);
        }
    }

    /**
     * 根据订单号或快递单号查询单个订单
     * */
    @Override
    public Result findByOrderId(String id) throws FruitException {

        try{
            Result result = new Result();
            Order order = orderRepository.findByOrderId(id);
            if(order == null){
                order = orderRepository.findByOrderCommodity_ExpressInfo_ExpressNo(id);
            }
            if(order == null){
                throw new FruitException(FruitMsgEnum.NotFoundOrder);
            }

            OrderList orderList = fruitUtil.orderToOrderList(order);

            List<ExpressInfo> expressInfoList = order.getOrderCommodity().getExpressInfo();

            List<OrderExpressInfo> orderExpressInfos = new ArrayList<>();

            if(expressInfoList != null && !expressInfoList.isEmpty()){
                expressInfoList.forEach(expressInfo -> {
                    OrderExpressInfo orderExpressInfo = new OrderExpressInfo();
                    BeanUtils.copyProperties(expressInfo,orderExpressInfo);

                    String lastProcessUrl = "https://api.chenyistyle.com/order/logisticLast?"
                            + "trackingNo=" + expressInfo.getExpressNo()
                            + "&expressCompanyName=" + expressInfo.getExpressCompany()
                            + "&tmst="+ System.currentTimeMillis();

                    //根据快递公司和快递单号查询快递最新进展
                    OrderTracking orderTracking = restTemplate.getForObject(lastProcessUrl,OrderTracking.class);

                    orderExpressInfo.setLastProcess(orderTracking.getLastProcess());

                    orderExpressInfos.add(orderExpressInfo);
                });
                orderList.setExpressInfos(orderExpressInfos);
            }

            result.setResponseReplyInfo(orderList);

            return result;
        }catch (Exception e){
            throw new FruitException(FruitMsgEnum.Exception);
        }
    }

    @Override
    public Result findOrderByOrderId(String orderId) throws FruitException {

        try{
            Result result = new Result();

            Order order = orderRepository.findByOrderId(orderId);

            if(order == null){
                throw new FruitException(FruitMsgEnum.NotFoundOrder);
            }

            result.setResponseReplyInfo(order);

            return result;
        }catch (Exception e){

            throw new FruitException(FruitMsgEnum.Exception);

        }
    }


}
