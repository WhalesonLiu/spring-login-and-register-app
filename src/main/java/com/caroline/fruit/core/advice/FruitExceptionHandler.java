package com.caroline.fruit.core.advice;

import com.caroline.fruit.constant.FruitHttpConstant;
import com.caroline.fruit.exception.FruitMsgEnum;
import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.message.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.NoRouteToHostException;
import java.util.concurrent.TimeoutException;

@ControllerAdvice
public class FruitExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handlerException(Exception ex){
        Result result = new Result();
        result.setResponseType(FruitHttpConstant.RESPONSE_TYPE_ERROR);

        ex.getStackTrace();

        if( ex instanceof FruitException){

            result.setResponseCode(((FruitException) ex).getResponseCode());

            result.setResponseMessage(((FruitException) ex).getResponseMessage());
        }else if(ex instanceof TimeoutException) {
            result.setResponseCode(
                    FruitMsgEnum.TimeOutException.getResponseCode());

            result.setResponseMessage(
                    FruitMsgEnum.TimeOutException.getResponseMessage());

        }else if(ex instanceof NoRouteToHostException){
            result.setResponseCode(
                    FruitMsgEnum.NoRouteToHostException.getResponseCode());

            result.setResponseMessage(
                    FruitMsgEnum.NoRouteToHostException.getResponseMessage());
        }else{
            result.setResponseCode(
                    FruitMsgEnum.Exception.getResponseCode());

            result.setResponseMessage(
                    FruitMsgEnum.Exception.getResponseMessage());
        }
        return result;
    }
}
