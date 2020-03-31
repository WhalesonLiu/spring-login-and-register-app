package com.caroline.fruit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CommodityForm implements Serializable {

    private static final long serialVersionUID = -1212972316558114155L;

    private String commodityTypeId;

    private String commodityName;

    private String commodityDesc;

    //商品规格
    private String commoditySpecifications;

    //产地/分发地
    private String originDispatch;

    //商品售价
    private BigDecimal commodityPrice;

    //成本
    private BigDecimal costPrice;

    //运费
    private BigDecimal freight;

    private Integer commodityStock;

    private String commodityIcon;

    //商品折扣率
    private BigDecimal commodityDiscountRate;

    //商品图片
    private String commodityUrl;

    //快递公司
    private String expressCompany;

    //说明
    private String comments;
}
