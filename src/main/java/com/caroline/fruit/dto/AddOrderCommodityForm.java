package com.caroline.fruit.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
/**
 * 新增订单时，订单里面的商品.
 * */
public class AddOrderCommodityForm implements Serializable {

    private String commodityId;

    private Integer commodityNum;

    private String expressCompany;

    private String expressNo;

    private BigDecimal freight;

    //优惠券
    private String couponId;

    private String discount;



    public void setFreight(String freight){
        BigDecimal bigDecimal = new BigDecimal(freight);
        this.freight = bigDecimal.setScale(2,BigDecimal.ROUND_DOWN);
    }
}
