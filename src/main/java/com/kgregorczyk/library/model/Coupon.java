package com.kgregorczyk.library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.FetchType.*;

/**
 * 优惠券
 * */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_coupon")
public class Coupon implements Serializable {

    @Id
    @GeneratedValue
    private Long couponId;

    //优惠券类型
    @ManyToOne(cascade = CascadeType.ALL, fetch = EAGER)
    private CouponType couponType;

    @ManyToOne(cascade = CascadeType.ALL, fetch = LAZY)
    private User userId;

    //优惠券的张数
    private Integer num;


}
