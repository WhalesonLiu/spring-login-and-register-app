package com.caroline.fruit.repository;

import com.caroline.fruit.model.OrderCommodity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCommodityRepository extends JpaRepository<OrderCommodity,String> {
}
