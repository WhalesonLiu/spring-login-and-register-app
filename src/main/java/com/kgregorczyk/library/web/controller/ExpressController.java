package com.kgregorczyk.library.web.controller;

import com.kgregorczyk.library.enums.ResultEnum;
import com.kgregorczyk.library.message.Message;
import com.kgregorczyk.library.model.express.*;
import com.kgregorczyk.library.repository.MessageTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/express")
public class ExpressController {

    private Message message = new Message();

    private final RestTemplate restTemplate;

    private final MessageTemplateRepository messageTemplateRepository;

    @Autowired
    public ExpressController(RestTemplate restTemplate,
                             MessageTemplateRepository messageTemplateRepository){
        this.restTemplate = restTemplate;
        this.messageTemplateRepository = messageTemplateRepository;
    }

    @GetMapping("/list")
    public ResponseEntity<Message> getExpressInfo(@RequestParam(value = "keys[]") List<String> keys){

        if( keys == null || keys.isEmpty()){

            message.setMsg(ResultEnum.PARAM_EMPEY);

            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        List<ExpressResult> expressResults = new ArrayList<>();

        //MessageTemplate messageTemplate = messageTemplateRepository.findByFormatKey("EXPRESS_INFO_FORMAT");

        //String expressFormatMsg = messageTemplate.getFormatMeaage();

        for (String key : keys ) {

            if(!StringUtils.isEmpty(key)){
                String url = "https://api.chenyistyle.com/order/listCu?keyword="+ key+"&peopleId=10800324";
                ExpressResult expressResult = restTemplate.getForObject(url, ExpressResult.class);

                //订单详细信息
                expressResult.getData().forEach( e ->{
                    String orderDetailUrl = "https://api.chenyistyle.com/order/getCu?orderNo="+e.getOrderNo()+"&peopleId=10800324&tmst=" + System.currentTimeMillis();

                    OrderResult orderResult = restTemplate.getForObject(orderDetailUrl,OrderResult.class);

                    DetailOrder detailOrder = orderResult.getData();

                    e.setDetailOrder(detailOrder);
                    detailOrder.getOrderTrackings().forEach(orderT ->{
                        //查询最新的快递信息
                        String lastProcessUrl = "https://api.chenyistyle.com/order/logisticLast?trackingNo="+
                                orderT.getTrackingNo()+"&expressCompanyName="+ orderT.getExpressCompanyName()
                                +"&businessCode=" + orderT.getBusinessCode()
                                +"&tmst="+ System.currentTimeMillis();
                        OrderTracking orderTracking = restTemplate.getForObject(lastProcessUrl,OrderTracking.class);
                        orderT.setLastProcess(orderTracking.getLastProcess());

                    });

                });
                String goodsInfo = expressResult.getData().stream().map(expressData -> {
                    if("已发货".equals(expressData.getOrderStatus())){
                        return expressData.getGoodsName();
                    }
                    return null;
                }).filter(x-> x!= null).collect(Collectors.toList()).toString()
                        .replace(",","、");

                Calendar cal = Calendar.getInstance();

                int hour = cal.get(Calendar.HOUR_OF_DAY);

                String hello = "";

                if (hour >= 6 && hour < 8) {
                    hello = "早上好呀";
                } else if (hour >= 8 && hour < 11) {
                    hello = "上午好呀,";
                } else if (hour >= 11 && hour < 13) {
                    hello = "中午好呀,";
                } else if (hour >= 13 && hour < 18) {
                    hello = "下午好呀,";
                } else {
                    hello = "晚上好呀,";
                }
                goodsInfo = hello + "您购买的" + goodsInfo.replaceAll("[\\[\\]]","") + "均已发货";
                expressResult.setMessage(goodsInfo);
                expressResults.add(expressResult);
                message.setMsg(ResultEnum.QUERY_SECCESS,expressResults);
            }
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
