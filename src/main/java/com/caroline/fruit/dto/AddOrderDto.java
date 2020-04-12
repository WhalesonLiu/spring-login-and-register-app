package com.caroline.fruit.dto;

import com.caroline.fruit.constant.FruitConstant;
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

    private List<AddOrderCommodityForm> commodities;

    //订单状态
    private Integer orderStatus;

    private String remark;

    //-------------------------------------订单更新时----------------------------------
    //订单号
    private String orderId;

    //拒绝原因
    private String refuseReason;

    private String updateExpressCompany;

    private String updateExpressNo;

    //是否售后
    private Boolean isAfterMarket;

    //售后处理方式
    private Integer afterMarketWay;


    public void setAfterMarketWay(Integer afterMarketWay) {
        this.afterMarketWay = afterMarketWay;
    }

    public void setAfterMarketWay(String afterMarketWay) {
        this.afterMarketWay = Integer.parseInt(afterMarketWay);
    }
}
