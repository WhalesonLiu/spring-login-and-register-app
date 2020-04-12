package com.caroline.fruit.repository;

import com.caroline.fruit.model.RedeliverOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RedeliverOrderRepository extends JpaRepository<RedeliverOrder,String> {

    List<RedeliverOrder> findByOrderIdOrderByCreationDateAsc(String orderId);
}
