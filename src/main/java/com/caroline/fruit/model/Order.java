package com.caroline.fruit.model;

import com.caroline.fruit.audit.CreateAndLastModifiedDate;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Data
@Table(name = "t_order")
public class Order extends CreateAndLastModifiedDate implements Serializable {

    @Id
    private String orderId;

    //是否付款
    private Boolean isPay;
    //商家
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "merchant",referencedColumnName = "id")
    private User merchant;

    //购买者
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "payer",referencedColumnName = "id")
    private User payer;

    //收货人
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "delivery",referencedColumnName = "infoId")
    private DeliveryInfo delivery;


    @OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name ="order_commodities" )
    private OrderCommodity orderCommodity;

    //是否售后
    private Boolean isAftermarket;

    //拒绝原因
    private String refuseReason;

    //订单状态
    private Integer orderStatus;
}
