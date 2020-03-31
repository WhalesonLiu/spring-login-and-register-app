package com.caroline.fruit.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class FruitException extends Exception {

    private String responseCode;

    private String responseMessage;

    public FruitException(){

    }
    public FruitException( String responseCode, String responseMessage) {

        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public FruitException(FruitMsgEnum fruitErrorEnum){

        this.responseCode = fruitErrorEnum.getResponseCode();

        this.responseMessage = fruitErrorEnum.getResponseMessage();
    }



}
