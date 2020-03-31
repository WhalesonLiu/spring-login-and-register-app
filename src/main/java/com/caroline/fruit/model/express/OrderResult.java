package com.caroline.fruit.model.express;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResult implements Serializable {

    private String code;

    private String message;

    private DetailOrder data;

}
