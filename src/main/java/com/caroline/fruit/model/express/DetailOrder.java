package com.caroline.fruit.model.express;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单详情
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailOrder implements Serializable {

    private static final long serialVersionUID = 1708502346671552211L;


    private String goodsName;

    private Integer goodsPieces;

    private String goodsImageUrl;

    //零售价
    private BigDecimal retailPrice;

    //应收货款
    private BigDecimal goodsAmountReceivable;

    //运费
    private BigDecimal goodsAmountShipping;

    private String receiverName;

    private String receiverPhone;

    private String address;

    private String orderStatus;

    //退款状态
    private Integer refundStatus;

    //售后状态
    private String afterSaleStatus;

    //无效状态
    private String invalidStatus;

    private String orderNo;

    private String commitTime;

    private String isLive;

    private String supplierId;

    private List<OrderTracking> orderTrackings;


}
