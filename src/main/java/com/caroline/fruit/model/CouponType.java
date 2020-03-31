package com.caroline.fruit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 优惠券类型
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_coupon_type")
public class CouponType implements Serializable {


    @Id
    private String couponTypeId;

    //优惠券名称
    private String couponTypeName;

    //额度
    @Column(name="quota", precision=10, scale=2)
    private BigDecimal quota;

    //备注
    private String remark;
}
