package com.caroline.fruit.model.express;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 臣颐根据手机号查到的快递信息
 * */
public class ExpressResult implements Serializable {
    private static final long serialVersionUID = 2808237673991124925L;

    private String code;
    private String message;
    private List<ExpressData> data;
}
