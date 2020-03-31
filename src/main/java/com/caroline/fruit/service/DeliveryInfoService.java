package com.caroline.fruit.service;

import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.message.Result;

public interface DeliveryInfoService {

    Result findDeliveryInfoByWeChatAccount(String weChatAccount) throws FruitException;

    Result findByInfoId(String infoId) throws FruitException;
}
