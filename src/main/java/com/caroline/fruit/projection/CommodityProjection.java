package com.caroline.fruit.projection;

import com.caroline.fruit.model.Commodity;

import java.math.BigDecimal;

public interface CommodityProjection {

    Long getCommodityId();

    Commodity getParentId();

    String getCommodityName();

    String getCommodityDesc();

    String getCommoditySpecifications();

    String getOriginDispatch();

    BigDecimal getCommodityPrice();

    BigDecimal getCostPrice() ;

    Integer getCommodityStock() ;

    String getCommodityIcon() ;

    BigDecimal getCommodityDiscountRate() ;

    String getCommodityUrl();

    String getExpressCompany();

    String getComments();

}
