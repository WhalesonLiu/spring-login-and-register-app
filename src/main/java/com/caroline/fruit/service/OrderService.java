package com.caroline.fruit.service;

import com.caroline.fruit.dto.AddOrderDto;
import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.message.Result;
import com.caroline.fruit.model.OrderCommodity;
import org.springframework.data.domain.PageRequest;

public interface OrderService {

    Result addOrder(AddOrderDto addOrderDto) throws FruitException;

    void addOrderCommodity(OrderCommodity orderCommodity) throws FruitException;

    Result findAllOrderListByPageable(PageRequest pageRequest) throws FruitException;

}
