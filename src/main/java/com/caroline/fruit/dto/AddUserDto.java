package com.caroline.fruit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.DATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserDto implements Serializable {

    private static final long serialVersionUID = -6122500496078769757L;

    private String realName;

    private String gender;

    private String weChatAccount;

    private String weChatName;

    @Temporal(DATE)
    private Date birthDay;

    private String userRemark;

    private List<DeliveryInfoDto> deliveryInfoList;

}
