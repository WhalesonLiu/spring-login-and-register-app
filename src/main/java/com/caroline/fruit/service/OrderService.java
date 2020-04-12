package com.caroline.fruit.service;

import com.caroline.fruit.dto.AddOrderDto;
import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.message.Result;
import com.caroline.fruit.model.Order;
import com.caroline.fruit.model.OrderCommodity;
import org.springframework.data.domain.PageRequest;

public interface OrderService {

    Result addOrder(AddOrderDto addOrderDto) throws FruitException;

    Order addOrder(Order order) throws FruitException;

    void addOrderCommodity(OrderCommodity orderCommodity) throws FruitException;

    Result findAllOrderListByPageable(PageRequest pageRequest, String orderVal) throws FruitException;

    Result findByOrderId(String orderId) throws FruitException;

    Result findOrderByOrderId(String orderId) throws FruitException;

}
