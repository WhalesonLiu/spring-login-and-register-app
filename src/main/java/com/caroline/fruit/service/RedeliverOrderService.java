package com.caroline.fruit.service;

import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.model.RedeliverOrder;

public interface RedeliverOrderService {

    RedeliverOrder saveRedeliverOrderService(RedeliverOrder redeliverOrder) throws FruitException;

    RedeliverOrder getRecentRedeliverOrder(String orderId) throws FruitException;
}
