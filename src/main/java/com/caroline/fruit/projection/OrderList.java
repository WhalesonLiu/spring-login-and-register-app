package com.caroline.fruit.projection;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderList {

    //订单Id
    String orderId;

    //订单状态
    String orderStatus;

    //订单日期
    Date orderCreationDate;

    //商品名称
    String commodityName;

    //付款人名称
    String payerName;

    Long payerId;

    //收货人名称
    String deliveryName;

    String deliveryId;

    //商品总价
    BigDecimal orderTotalPrice;

    //商品数量
    Integer commodityNum;
}
