package com.caroline.fruit.web.controller;

import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.message.Result;
import com.caroline.fruit.service.DeliveryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
public class DeliveryInfoController {

    private final DeliveryInfoService deliveryInfoService;

    @Autowired
    public DeliveryInfoController(DeliveryInfoService deliveryInfoService){
        this.deliveryInfoService = deliveryInfoService;

    }

    @GetMapping("")
    public Result findDeliverInfoByUserId(@RequestParam(value = "weChatAccount") String weChatAccount) throws FruitException {

        return deliveryInfoService.findDeliveryInfoByWeChatAccount(weChatAccount);
    }
}
