package com.caroline.fruit.web.controller;

import com.caroline.fruit.core.util.FruitUtil;
import com.caroline.fruit.dto.AddOrderCommodityForm;
import com.caroline.fruit.dto.AddOrderDto;
import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.exception.FruitMsgEnum;
import com.caroline.fruit.message.Result;
import com.caroline.fruit.model.*;
import com.caroline.fruit.service.CouponFlowService;
import com.caroline.fruit.service.DeliveryInfoService;
import com.caroline.fruit.service.OrderService;
import com.caroline.fruit.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

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

        return null;
    }
}
