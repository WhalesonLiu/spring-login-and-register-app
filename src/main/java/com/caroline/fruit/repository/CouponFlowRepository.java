package com.caroline.fruit.repository;

import com.caroline.fruit.model.CouponFlow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponFlowRepository extends JpaRepository<CouponFlow,String> {

    CouponFlow findByCouponFlowIdAndAndUserId_Id(String couponFlowId,Long id);

    void deleteByCouponFlowId(String couponFlowId);

}
