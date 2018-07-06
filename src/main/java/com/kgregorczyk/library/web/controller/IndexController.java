package com.kgregorczyk.library.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

  @GetMapping("/home")
  public String home() {
    return "home";
  }

  @GetMapping("/")
  public String index() {
    return "home";
  }
}
