package com.caroline.fruit.repository;

import com.caroline.fruit.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {

    Page<Order> findAll(Pageable pageable);

    Page<Order> findAllByOrderIdContains(Pageable pageable,String orderId);

    Page<Order> findAllByOrderCommodity_ExpressInfo_ExpressNo(Pageable pageable,String expressNo);

    /**
     * 模糊查询，根据订单号查询订单
     * */
    Page<Order> findByOrderIdContains(Pageable pageable,String orderId);

    /**
     * 模糊查询，根据快递单号查询订单
     * */
    Page<Order> findByOrderCommodity_ExpressInfo_ExpressNo(Pageable pageable,String expressNo);


    /**
     * 精确查询，根据定单号查询订单
     * */
    Order findByOrderId(String orderId);

    /**
     * 精确查询，根据快递单号查询订单
     * */
    Order findByOrderCommodity_ExpressInfo_ExpressNo(String expressNo);
}
