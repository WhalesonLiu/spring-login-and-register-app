package com.caroline.fruit.service;

import com.caroline.fruit.dto.CommodityForm;
import com.caroline.fruit.exception.FruitMsgEnum;
import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.message.Result;
import com.caroline.fruit.model.Commodity;
import com.caroline.fruit.projection.CommodityTypeList;
import com.caroline.fruit.projection.OrderCommodityOption;
import com.caroline.fruit.repository.CommodityRepository;
import com.caroline.fruit.web.dto.CommodityTypeFrom;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Component
public class CommodityServiceImpl implements CommodityService {

    private final  CommodityRepository commodityRepository;

    @Autowired
    public CommodityServiceImpl(CommodityRepository commodityRepository){

        this.commodityRepository = commodityRepository;
    }
    /**
     * Add commodity type.
     * @param commodityTypeFrom contain commodity name and describe.
     * @throws FruitException
     * */
    @Override
    public Result addComodityType(CommodityTypeFrom commodityTypeFrom) throws FruitException {

        try{

            Commodity commodity = new Commodity();

            commodity.setCommodityId(UUID.randomUUID().toString());

            commodity.setCommodityName(commodityTypeFrom.getCommodityTypeName());

            commodity.setCommodityDesc(commodityTypeFrom.getCommodityTypeDesc());

            commodity = commodityRepository.save(commodity);

            if( commodity != null){

                return new Result(FruitMsgEnum.InsertCommodityTypeSuccess);

            }

            throw new FruitException(FruitMsgEnum.InsertCommodityTypeFailed);

        }catch (Exception e){
            throw new FruitException(FruitMsgEnum.Exception);
        }

    }

    /**
     * get add commodity type
     * @param pageable page and size
     * @throws FruitException
     * */
    @Override
    public Result getCommodityTypeList(Pageable pageable,String commodityName) throws FruitException {

        Result result = new Result();

        try {

            Page<CommodityTypeList> commodityTypePage = null;

            if( !StringUtils.isEmpty(commodityName)){

            }else{

                commodityTypePage = commodityRepository.findAllByParentIdIsNull(pageable);
            }

            if(commodityTypePage == null || commodityTypePage.getContent() == null
                    || commodityTypePage.getContent().isEmpty()){

                throw new FruitException(FruitMsgEnum.CommodityTypeResultEmpty);
            }else{

                result.setResponseReplyInfo(commodityTypePage);

            }
        }catch (Exception e){

            throw new FruitException(FruitMsgEnum.Exception);
        }
        return result;
    }

    /**
     * add commodity
     * @param commodityFrom
     * @throws FruitException
     * */
    @Override
    public Result addCommodity(CommodityForm commodityFrom) throws FruitException {


        Commodity commodity = new Commodity();
        try {

            BeanUtils.copyProperties(commodityFrom,commodity);

            if( commodityFrom.getCommodityTypeId() != null ){

                Commodity parentCommodity = commodityRepository.findByCommodityId(
                        commodityFrom.getCommodityTypeId());

                commodity.setParentId(parentCommodity);

            }
            commodity.setCommodityId(UUID.randomUUID().toString());

            commodity = commodityRepository.save(commodity);

            if( commodity != null){

                return new Result(FruitMsgEnum.InsertCommoditySuccess);

            }else {

                throw new FruitException(FruitMsgEnum.InsertCommodityFailed);
            }
        }catch (Exception e){

            throw new FruitException(FruitMsgEnum.Exception);
        }
    }

    @Override
    public Result getCommodityList(PageRequest pageRequest,String commodityName) throws FruitException {

        Result result = new Result();

        try{

            Page<Commodity> commodityPage = null;

            if( !StringUtils.isEmpty(commodityName)){

                commodityPage = commodityRepository.findAllByParentIdIsNotNullAndCommodityNameContains(pageRequest,commodityName);

            }else{

                commodityPage = commodityRepository.findAllByParentIdIsNotNull(pageRequest);

            }

            if( commodityPage != null && commodityPage.getContent() != null){

                result.setResponseReplyInfo(commodityPage);

            }else{

                throw new FruitException(FruitMsgEnum.CommodityResultEmpty);
            }
        }catch (Exception e){

            throw new FruitException(FruitMsgEnum.Exception);

        }

        return result;
    }

    @Override
    public Commodity getCommodityById(String commodityId) throws FruitException {
        return commodityRepository.findByCommodityId(commodityId);
    }

    @Override
    public Result getAllCommodities() throws FruitException {
        try{
            Result result = new Result();
            List<OrderCommodityOption> commodityOptions = commodityRepository.findAllByParentIdIsNotNull();

            if(commodityOptions != null && !commodityOptions.isEmpty()){

                result.setResponseReplyInfo(commodityOptions);

                return result;
            }else {
                throw new FruitException(FruitMsgEnum.CommodityResultEmpty);
            }
        }catch (Exception e){
            throw new FruitException(FruitMsgEnum.Exception);
        }
    }
}
