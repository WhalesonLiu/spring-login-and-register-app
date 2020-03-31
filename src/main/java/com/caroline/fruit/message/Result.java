package com.caroline.fruit.message;

import com.caroline.fruit.constant.FruitHttpConstant;
import com.caroline.fruit.exception.FruitMsgEnum;
import lombok.Data;

@Data

public class Result {

    private String responseType;

    private String responseCode;

    private String responseMessage;

    private Object responseReplyInfo;


    public Result(){

    }
    /**
     * Result success
     * */
    public Result(FruitMsgEnum fruitMsgEnum){

        this.responseType = FruitHttpConstant.RESPONSE_TYPE_NORMAL;

        this.responseCode = fruitMsgEnum.getResponseCode();

        this.responseMessage = fruitMsgEnum.getResponseMessage();

    }
    public void setResponseType(String responseType){

        this.responseType = responseType;

        if( FruitHttpConstant.RESPONSE_TYPE_NORMAL.equals(responseType)){

            this.responseCode = FruitMsgEnum.Normal.getResponseCode();

            this.responseMessage = FruitMsgEnum.Normal.getResponseMessage();

        }

    }

    /**
     * Result success
     * */
    public void setResponseReplyInfo(Object responseReplyInfo){

        this.responseType = FruitHttpConstant.RESPONSE_TYPE_NORMAL;

        this.responseCode = FruitMsgEnum.Normal.getResponseCode();

        this.responseMessage = FruitMsgEnum.Normal.getResponseMessage();

        this.responseReplyInfo = responseReplyInfo;
    }

}
