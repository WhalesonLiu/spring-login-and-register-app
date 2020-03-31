package com.caroline.fruit.repository;


import com.caroline.fruit.model.DeliveryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryInfoRepository extends JpaRepository<DeliveryInfo, Long> {

    List<DeliveryInfo> findAllByUser_WeChatAccount(String weChatAccount);

    DeliveryInfo findByInfoId(String infoId);
}
