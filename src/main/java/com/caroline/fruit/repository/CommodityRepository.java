package com.caroline.fruit.repository;

import com.caroline.fruit.model.Commodity;
import com.caroline.fruit.projection.CommodityTypeList;
import com.caroline.fruit.projection.OrderCommodityOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommodityRepository extends JpaRepository<Commodity, String> {

    Commodity save(Commodity commodity);

    Commodity findByCommodityId(String commodityId);

    Page<CommodityTypeList> findAllByParentIdIsNull(Pageable pageable);


    Page<Commodity> findAllByParentIdIsNotNull(Pageable pageable);

    Page<Commodity> findAllByParentIdIsNotNullAndCommodityNameContains(Pageable pageable,String commodityName);

    List<OrderCommodityOption> findAllByParentIdIsNotNull();
}
