package com.caroline.fruit.service;

import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.exception.FruitMsgEnum;
import com.caroline.fruit.message.Result;
import com.caroline.fruit.model.CouponFlow;
import com.caroline.fruit.repository.CouponFlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CouponFlowServiceImpl implements  CouponFlowService {

    private final CouponFlowRepository couponFlowRepository;

    @Autowired
    public CouponFlowServiceImpl(CouponFlowRepository couponFlowRepository){
        this.couponFlowRepository = couponFlowRepository;

    }
    /**
     * 查询该用户是否有此优惠券
     * */
    @Override
    public CouponFlow findCouponByIdAndUserId(String flowId, Long userId) throws FruitException {
        try{
            CouponFlow couponFlow =
                    couponFlowRepository.findByCouponFlowIdAndAndUserId_Id(flowId, userId);
            /*if(couponFlow == null){
                throw new FruitException(FruitMsgEnum.UserHaveNoCoupon);
            }*/
            return couponFlow;
        }catch (Exception e){
            throw new FruitException(FruitMsgEnum.Exception);
        }
    }

    /**
     * 删除优惠券
     * */
    @Override
    public void deleteByFlowId(String flowId) throws FruitException {
        couponFlowRepository.deleteByCouponFlowId(flowId);
    }

    @Override
    public Result addCoupon(CouponFlow couponFlow) throws FruitException {
        try{

            couponFlow = couponFlowRepository.save(couponFlow);
            if(couponFlow == null){
                throw new FruitException(FruitMsgEnum.AddUserCouponFailed);
            }
            return new Result(FruitMsgEnum.AddUserCouponSuccess);

        }catch (Exception e){
            throw new FruitException(FruitMsgEnum.Exception);
        }
    }
}
