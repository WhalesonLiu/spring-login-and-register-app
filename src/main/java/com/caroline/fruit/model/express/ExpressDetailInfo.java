package com.caroline.fruit.model.express;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpressDetailInfo implements Serializable {

    private static final long serialVersionUID = -5979308662164348560L;

    private String ftime;

    private String opDesc;

    private String context;

    private String time;
}
