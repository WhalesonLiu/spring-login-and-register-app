package com.caroline.fruit.web.controller;

import com.caroline.fruit.dto.CommodityForm;
import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.exception.FruitMsgEnum;
import com.caroline.fruit.message.Result;
import com.caroline.fruit.model.Commodity;
import com.caroline.fruit.service.CommodityService;
import com.caroline.fruit.web.dto.CommodityTypeFrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLDataException;

@RestController()
@RequestMapping("/commodity")
public class CommodityController {

    private final CommodityService commodityService;

    @Autowired
    public CommodityController(CommodityService commodityService){

        this.commodityService = commodityService;

    }
    @PostMapping("/type/add")
    public Result addCommodityType(@RequestBody CommodityTypeFrom commodityTypeFrom) throws FruitException {

        return commodityService.addCommodityType(commodityTypeFrom);

    }

    @PostMapping
    public Result addCommodity(@RequestBody CommodityForm commodityFrom) throws FruitException {

        if(commodityFrom == null){
            throw new FruitException(FruitMsgEnum.InsertCommodityFailed);
        }else{

            return commodityService.addCommodity(commodityFrom);
        }

    }

    /**
     * get add commodity type
     *
     * */
    @GetMapping("/type/list")
    public Result commodityTypeList(@RequestParam("page") Integer page,
                                                    @RequestParam("size") Integer size,
                                                    @RequestParam(value = "commodityName",required = false) String commodityName) throws Exception{

        return commodityService.getCommodityTypeList(PageRequest.of(page,size),commodityName);

    }

    @GetMapping("/list")
    public Result commodityList(@RequestParam("page") Integer page,
                                                    @RequestParam("size") Integer size,
                                                @RequestParam(value = "commodityName",required = false) String commodityName) throws Exception{

        return commodityService.getCommodityList(PageRequest.of(page,size), commodityName);

    }
    @GetMapping("/all/list")
    public Result getAllCommodity() throws Exception{

        return commodityService.getAllCommodities();

    }

    /**
     * 根据订单id或快递单号获取订单详情
     * */
    @GetMapping("/{commodityId}")
    public Result getCommodityDetail(@PathVariable("commodityId") String commodityId) throws FruitException{
        try {

            Result result = new Result();
            Commodity commodity = commodityService.getCommodityById(commodityId);
            if(commodity == null){
                throw new FruitException(FruitMsgEnum.CommodityResultEmpty);
            }else {
                result.setResponseReplyInfo(commodity);
            }
            return result;
        }catch (Exception e){
            throw new FruitException(FruitMsgEnum.Exception);
        }
    }

    @PutMapping
    public Result updateCommodity(@RequestBody CommodityForm commodityFrom) throws FruitException{
        try {

            Commodity commodity =
                    commodityService.getCommodityById(commodityFrom.getCommodityId());

            if (commodity == null){
                throw new FruitException(FruitMsgEnum.CommodityResultEmpty);
            }
            //设置更新值

            //设置规格
            commodity.setCommoditySpecifications(
                    commodityFrom.getCommoditySpecifications());

            //设置产地/分发地
            commodity.setOriginDispatch(commodityFrom.getOriginDispatch());

            //设置成本价
            //commodity

            //售价
            commodity.setCommodityPrice(commodityFrom.getCommodityPrice());

            //商品描述
            commodity.setCommodityDesc(commodityFrom.getCommodityDesc());

            //快递公司
            commodity.setExpressCompany(commodityFrom.getExpressCompany());

            //说明
            commodity.setComments(commodityFrom.getComments());
            //commodityService.

           commodity = commodityService.addCommodity(commodity);

           if( commodity == null){
               throw new FruitException(FruitMsgEnum.InsertCommodityFailed);
           }else {
               return new Result(FruitMsgEnum.InsertCommoditySuccess);
           }
        }catch (Exception e){

            e.getStackTrace();
            throw new FruitException(FruitMsgEnum.Exception);
        }
    }

    @GetMapping("/lists")
    public Result commodityLists(@RequestParam("num") Integer num) throws Exception{

        Result result = new Result();
        if(num == 1){
            throw new NullPointerException("空了");
        }else if( num == 2){
            throw new SQLDataException("错了");
        }else if( num == 3){
            throw new IndexOutOfBoundsException("错了");
        }else if (num == 4){
            throw new FruitException();
        }else {
            result.setResponseReplyInfo("德玛西亚");
        }

        return result;
    }

}
