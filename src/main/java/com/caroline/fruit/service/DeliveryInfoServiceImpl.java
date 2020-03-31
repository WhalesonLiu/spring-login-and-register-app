package com.caroline.fruit.service;

import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.exception.FruitMsgEnum;
import com.caroline.fruit.message.Result;
import com.caroline.fruit.model.DeliveryInfo;
import com.caroline.fruit.repository.DeliveryInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DeliveryInfoServiceImpl implements DeliveryInfoService {

    private final DeliveryInfoRepository deliveryInfoRepository;

    @Autowired
    public DeliveryInfoServiceImpl(DeliveryInfoRepository deliveryInfoRepository){
        this.deliveryInfoRepository = deliveryInfoRepository;

    }
    @Override
    public Result findDeliveryInfoByWeChatAccount(String weChatAccount) throws FruitException {


        try {
            Result result = new Result();

            List<DeliveryInfo> deliveryInfos =
                    deliveryInfoRepository.findAllByUser_WeChatAccount(weChatAccount);

            if(deliveryInfos != null && !deliveryInfos.isEmpty()){

                result.setResponseReplyInfo(deliveryInfos);
                return result;
            }else {
                throw new FruitException(FruitMsgEnum.NoRouteToHostException);
            }
        }catch (Exception e){

            throw new FruitException(FruitMsgEnum.Exception);
        }
    }

    @Override
    public Result findByInfoId(String infoId) throws FruitException {

        try{

            Result result = new Result();
            DeliveryInfo deliveryInfo = deliveryInfoRepository.findByInfoId(infoId);
            if(deliveryInfo == null){
                throw new FruitException(FruitMsgEnum.NotFoundDeliveryInfo);
            }else {

                result.setResponseReplyInfo(deliveryInfo);

                return result;
            }
        }catch (Exception e){

            throw new FruitException(FruitMsgEnum.Exception);
        }
    }
}
