package com.caroline.fruit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryInfoDto implements Serializable {

    private String deliveryIndex;

    //收货人姓名
    private String deliveryName;

    //收货人手机号
    private String phoneNumber;

    //收货人地址[家，公司，学校]
    private String deliveryAddress;

    //备注
    private String deliveryRemark;
}
