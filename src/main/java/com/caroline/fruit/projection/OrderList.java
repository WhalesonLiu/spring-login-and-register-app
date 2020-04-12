package com.caroline.fruit.projection;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderList {

    //订单Id
    String orderId;

    //订单状态
    String orderStatus;

    Integer orderStatusId;

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

    //快递信息
    List<OrderExpressInfo> expressInfos;

    //商品图片
    private String commodityUrl;

    //运费
    private BigDecimal freight;

    //是否付款
    private Boolean isPay;

    //是否售后
    private Boolean isAfterMarket;

    //售后处理方式
    private Integer afterMarketWay;

    //拒绝原因
    private String refuseReason;

    //备注
    private String remark;
}
