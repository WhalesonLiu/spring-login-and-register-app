package com.kgregorczyk.library.model.express;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 一个快递信息
 * */
public class ExpressData implements Serializable {

    private static final long serialVersionUID = -7618122047867897683L;

    private String id;

    private String orderNo;

    private String receiverName;

    private String commitTime;

    private String goodsName;

    private String goodsImageUrl;

    private String orderStatus;

    private DetailOrder detailOrder;
}
