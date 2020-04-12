package com.caroline.fruit.model;

import com.caroline.fruit.audit.CreateAndLastModifiedDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_express_info")
public class ExpressInfo  extends CreateAndLastModifiedDate implements Serializable {

    @Id
    private String expressInfoId;

    private String expressCompany;

    private String expressNo;

}
