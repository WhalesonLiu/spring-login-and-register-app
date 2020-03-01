package com.kgregorczyk.library.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

  @GetMapping("/")
  public String index(){
    return "home";
  }

  @GetMapping("/add/2019-nCoV")
  public String addCases() {
    return "add_2019-nCoV";
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

}
