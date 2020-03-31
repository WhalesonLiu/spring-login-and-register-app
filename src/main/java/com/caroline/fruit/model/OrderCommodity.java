package com.caroline.fruit.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单里面的商品，涉及订单信息(物流信息)
 * */
@Data
@Entity
@NoArgsConstructor
@Table(name = "t_order_commodity")
public class OrderCommodity implements Serializable {

    @Id
    private String orderCommodityId;

    //cascade:表的级联操作
    @ManyToOne(cascade = CascadeType.REFRESH) //JPA注释： 一对一 关系
    //referencedColumnName：参考列名,默认的情况下是列表的主键
    //nullable=是否可以为空，
    //insertable：是否可以插入，
    //updatable：是否可以更新
    // columnDefinition=列定义，
    //foreignKey=外键
    @JoinColumn(name="commodity",referencedColumnName="commodityId",nullable=false)
    private Commodity commodityId;

    private Integer commodityNum;

    //快递公司
    private String expressCompany;

    //快递单号
    private String expressNo;

    //运费
    @Column(precision=10, scale=2)
    private BigDecimal freight;

    //优惠券
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="coupon_flow_id",referencedColumnName="couponFlowId")
    private CouponFlow coupon;

    //折扣
    @Column(precision=10, scale=2)
    private BigDecimal discount;

}
