package com.caroline.fruit.repository;


import com.caroline.fruit.model.ExpressInfo;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ExpressInfoRepository extends JpaRepository<ExpressInfo, String> {

    @Transactional
    @Modifying
    @Query(value = "DELETE  FROM t_express_info WHERE ORDER_EXPRESS_ID = ?1 ",nativeQuery = true)
    void deleteByOrderExpressId(String orderExpressId);
}
