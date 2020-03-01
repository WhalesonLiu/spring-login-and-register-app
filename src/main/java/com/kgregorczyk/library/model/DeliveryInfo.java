package com.kgregorczyk.library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

/**
 * 收货信息
 * */
@Data
@Entity
@Table(name = "t_delivery_info")
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryInfo {


    @Id
    @GeneratedValue
    private Long infoId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = LAZY)
    @JsonBackReference
    private User user;

    //收货人姓名
    private String deliveryName;

    //收货人手机号
    private String phoneNumber;

    //收货人地址[家，公司，学校]
    private String deliveryAddress;

    //备注
    private String remark;

}
