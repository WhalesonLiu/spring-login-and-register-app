package com.caroline.fruit.web.controller;

import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.message.Result;
import com.caroline.fruit.projection.OrderList;
import com.caroline.fruit.service.CommodityService;
import com.caroline.fruit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    private final  CommodityService commodityService;

    private final OrderService orderService;

    @Autowired
    public IndexController(CommodityService commodityService,
                           OrderService orderService){
        this.commodityService = commodityService;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }


    @GetMapping("/add/user")
    public String addUser(){
        return "add_user";
    }


    @GetMapping("/user")
    public String showUser(){
        return "user";
    }

    @GetMapping("/order")
    public String showOrder(){
        return "order";
    }

    @GetMapping("/add/order")
    public String addOrder(){
        return "add_order";
    }

    @GetMapping("/order/detail")
    public String showOrderDetail() {

        return "order_detail";
    }


    @GetMapping("/add/commodity")
    public String addCommodity(){
        return "add_commodity";
    }

    @GetMapping("/add/commodity/type")
    public String addCommodityType(){
        return "add_commodity_type";
    }


    @GetMapping("/commodity")
    public String showCommodity(){
        return "commodity";
    }

    @GetMapping("/express")
    public String showExpress(){
        return "express";
    }

    @GetMapping("/commodity/detail")
    public String showCommodityDetail()
            throws FruitException {

        return "commodity_detail";
    }

    @GetMapping("/add/2019-nCoV")
    public String addCases() {
        return "add_2019-nCoV";
    }
}
