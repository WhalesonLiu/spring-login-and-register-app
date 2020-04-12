package com.caroline.fruit.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderExpressInfo {

    private String expressInfoId;

    private String expressCompany;

    private String expressNo;

    private String lastProcess;
}
