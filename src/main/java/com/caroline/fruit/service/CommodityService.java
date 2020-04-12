package com.caroline.fruit.service;

import com.caroline.fruit.dto.CommodityForm;
import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.message.Result;
import com.caroline.fruit.model.Commodity;
import com.caroline.fruit.web.dto.CommodityTypeFrom;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface CommodityService {

    Result addCommodityType(CommodityTypeFrom commodityTypeFrom) throws FruitException;

    Commodity addCommodity(Commodity commodity) throws FruitException;


    /**
     * get add commodity type
     * @param pageable page and size
     * @throws FruitException
     * */
    Result getCommodityTypeList(Pageable pageable,String commodityName) throws FruitException;

    /**
     * add commodity.
     * @throws FruitException
     * */
    Result addCommodity(CommodityForm commodityFrom) throws FruitException;

    /**
     * get commodities by pageRequest
     * @param pageRequest page and size
     * @throws FruitException
     * */
    Result getCommodityList(PageRequest pageRequest, String commodityName) throws FruitException;

    /**
     * get commodity by commodityId
     * @param commodityId
     * @throws FruitException
     * */
    Commodity getCommodityById(String commodityId) throws FruitException;

    /**
     * get all commodity
     * @throws FruitException
     * */
    Result getAllCommodities() throws FruitException;
}
