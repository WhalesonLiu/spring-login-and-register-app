package com.caroline.fruit.service;

import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.message.Result;
import com.caroline.fruit.model.CouponFlow;

public interface CouponFlowService {

    /**
     * 查询该用户是否有此优惠券
     * */
    CouponFlow findCouponByIdAndUserId(String flowId, Long userId) throws FruitException;

    /**
     * 删除优惠券
     * */
    void deleteByFlowId(String flowId) throws FruitException;

    /**
     * 新增优惠券
     * */
    Result addCoupon(CouponFlow couponFlow) throws FruitException;

}
