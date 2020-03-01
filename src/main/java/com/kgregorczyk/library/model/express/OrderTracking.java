package com.kgregorczyk.library.model.express;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderTracking implements Serializable {

    private String expressCompanyName;

    private String businessCode;

    private String trackingNo;

    private String importTime;

    private String afterSaleStatus;

    private String afterSaleTimes;

    //快递最新状态
    private String lastProcess;
}
