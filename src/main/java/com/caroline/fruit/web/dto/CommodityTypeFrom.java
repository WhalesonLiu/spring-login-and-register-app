package com.caroline.fruit.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityTypeFrom implements Serializable {

    private static final long serialVersionUID = 1160700100359679957L;

    private String commodityTypeName;

    private String commodityTypeDesc;
}
