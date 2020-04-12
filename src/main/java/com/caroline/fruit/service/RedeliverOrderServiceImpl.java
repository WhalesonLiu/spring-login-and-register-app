package com.caroline.fruit.service;

import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.exception.FruitMsgEnum;
import com.caroline.fruit.model.RedeliverOrder;
import com.caroline.fruit.repository.RedeliverOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedeliverOrderServiceImpl implements RedeliverOrderService {

    public final RedeliverOrderRepository redeliverOrderRepository;

    @Autowired
    public RedeliverOrderServiceImpl(RedeliverOrderRepository redeliverOrderRepository){

        this.redeliverOrderRepository = redeliverOrderRepository;
    }

    @Override
    public RedeliverOrder saveRedeliverOrderService(RedeliverOrder redeliverOrder) throws FruitException {

        try{
            redeliverOrder = redeliverOrderRepository.save(redeliverOrder);

            if(redeliverOrder == null){
                throw new FruitException(FruitMsgEnum.AddRedeliverOrderFailed);
            }
            return redeliverOrder;
        }catch (Exception e){

            throw new FruitException(FruitMsgEnum.Exception);
        }

    }

    @Override
    public RedeliverOrder getRecentRedeliverOrder(String orderId) throws FruitException {
        try {
            List<RedeliverOrder> redeliverOrders =
                    redeliverOrderRepository.findByOrderIdOrderByCreationDateAsc(orderId);

            if(redeliverOrders != null && !redeliverOrders.isEmpty()){

                return redeliverOrders.get(0);
            }
            return null;
        }catch (Exception e){
            throw new FruitException(FruitMsgEnum.Exception);
        }
    }
}
