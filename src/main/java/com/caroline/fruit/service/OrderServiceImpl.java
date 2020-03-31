package com.caroline.fruit.service;

import com.caroline.fruit.core.util.FruitUtil;
import com.caroline.fruit.dto.AddOrderCommodityForm;
import com.caroline.fruit.dto.AddOrderDto;
import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.exception.FruitMsgEnum;
import com.caroline.fruit.message.Result;
import com.caroline.fruit.model.*;
import com.caroline.fruit.repository.OrderCommodityRepository;
import com.caroline.fruit.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class OrderServiceImpl implements OrderService {


    public final UserService userService;

    public final OrderRepository orderRepository;

    public final CommodityService commodityService;

    public final OrderCommodityRepository orderCommodityRepository;

    public final DeliveryInfoService deliveryInfoService;

    public final CouponFlowService couponFlowService;

    public final FruitUtil fruitUtil;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderCommodityRepository orderCommodityRepository,
                            DeliveryInfoService deliveryInfoService,
                            CouponFlowService couponFlowService,
                            CommodityService commodityService,
                            FruitUtil fruitUtil,
                            UserService userService){
        this.orderRepository = orderRepository;
        this.orderCommodityRepository = orderCommodityRepository;
        this.deliveryInfoService = deliveryInfoService;
        this.couponFlowService = couponFlowService;
        this.fruitUtil = fruitUtil;
        this.userService = userService;

        this.commodityService = commodityService;
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
    public Result findAllOrderListByPageable(PageRequest pageRequest) throws FruitException {
        try {

        }catch (Exception e){
            throw new FruitException(FruitMsgEnum.Exception);
        }
        return null;
    }


}
