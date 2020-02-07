package com.kgregorczyk.library.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

  @GetMapping("/")
  public String index(){
    return "index";
  }

  @GetMapping("/add/2019-nCoV")
  public String addCases() {
    return "add_2019-nCoV";
  }


}
