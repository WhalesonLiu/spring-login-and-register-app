package com.caroline.fruit.dto;

import com.caroline.fruit.model.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
/**
 * 添加订单
 * */
public class AddOrderDto implements Serializable {

    private String weChatAccount;

    private String deliveryId;

    //是否付款
    private Boolean isPay;

    //是否售后
    private Boolean isAftermarket;

    //售后处理方式
    private Integer dealWay;

    private List<AddOrderCommodityForm> commodities;

    //订单状态
    private Integer orderStatus;
}
