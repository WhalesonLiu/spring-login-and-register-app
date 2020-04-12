package com.caroline.fruit.model;

import com.caroline.fruit.audit.CreateAndLastModifiedDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 *
 * 售后处理-重新发货
 * */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "t_redeliver_order")
public class RedeliverOrder extends CreateAndLastModifiedDate implements Serializable {

    private static final long serialVersionUID = -8104606999257456877L;

    @Id
    private String redeliverOrderId;

    private String orderId;

    //快递公司
    private String expressCompany;

    //快递单号
    private String expressNo;

}
