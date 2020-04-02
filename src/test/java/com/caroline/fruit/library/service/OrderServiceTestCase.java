package com.caroline.fruit.library.service;

import com.caroline.fruit.core.util.OrderStatus;
import com.caroline.fruit.dto.AddOrderCommodityForm;
import com.caroline.fruit.dto.AddOrderDto;
import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.library.LibraryApplicationTests;
import com.caroline.fruit.message.Result;
import com.caroline.fruit.model.Order;
import com.caroline.fruit.service.OrderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceTestCase extends LibraryApplicationTests {


    @Autowired
    private OrderService orderService;

    @Test
    public void testAddOrder() throws FruitException {
        /*AddOrderDto addOrderDto = new AddOrderDto();
        addOrderDto.setWeChatAccount("KobeGoo");
        addOrderDto.setDeliveryId("3");
        addOrderDto.setIsPay(true);
        List<AddOrderCommodityForm> commodityFormList = new ArrayList<>();
        AddOrderCommodityForm addOrderCommodityForm = new AddOrderCommodityForm();
        addOrderCommodityForm.setCommodityId("f01225d8-0fcc-4b39-b13f-dfb1e1740505");
        addOrderCommodityForm.setCommodityNum(1);
        //addOrderCommodityForm.setOrderStatus(OrderStatus.OrderStatusEnum.PENDING.getStatus());
        addOrderDto.setCommodities(commodityFormList);

        orderService.addOrder(addOrderDto);*/

        Result result = orderService.findAllOrderListByPageable(PageRequest.of(0,10));

        System.out.println(result);
        System.out.println(result.getResponseReplyInfo());
    }
}
