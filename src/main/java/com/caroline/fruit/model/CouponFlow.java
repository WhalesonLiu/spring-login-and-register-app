package com.caroline.fruit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.FetchType.*;

/**
 * 优惠券流水表，用来记录用户的优惠券
 * */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_coupon_flow")
public class CouponFlow implements Serializable {

    @Id
    private String couponFlowId;

    //优惠券类型
    @ManyToOne(cascade = CascadeType.ALL, fetch = EAGER)
    private CouponType couponType;

    @ManyToOne(cascade = CascadeType.ALL, fetch = LAZY)
    private User userId;

    //优惠券的张数
    private Integer num;


}
