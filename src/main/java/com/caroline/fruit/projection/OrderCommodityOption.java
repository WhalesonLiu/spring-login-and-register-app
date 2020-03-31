package com.caroline.fruit.projection;

import java.math.BigDecimal;

public interface OrderCommodityOption {
    String getCommodityId();

    String getCommodityName();

    BigDecimal getFreight();
}
