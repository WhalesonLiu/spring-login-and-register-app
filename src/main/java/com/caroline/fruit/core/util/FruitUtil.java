package com.caroline.fruit.core.util;

import com.caroline.fruit.message.Result;
import com.caroline.fruit.model.Order;
import com.caroline.fruit.model.User;
import com.caroline.fruit.projection.OrderList;
import com.caroline.fruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class FruitUtil {

    private final UserService userService;
    @Autowired
    public FruitUtil(UserService userService){
        this.userService = userService;
    }
    public static String getId(){
        return UUID.randomUUID().toString();
    }

    public String getTableId(){
        return UUID.randomUUID().toString();
    }
    public  String getOrderNo(){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        String dateTime = dateTimeFormatter.format(localDateTime);
        User loggedInUser = userService.getLoggedInUser();
        return dateTime;
    }
    public Result getResult(Object coreData, Page pageData){
        Result result = new Result();

        Map<String,Object> map = new HashMap<>();

        map.put("content",coreData);
        map.put("size",pageData.getSize());
        map.put("totalPages",pageData.getTotalPages());
        map.put("first",pageData.isFirst());
        map.put("last",pageData.isLast());
        map.put("number",pageData.getNumber());
        map.put("totalElements",pageData.getTotalElements());

        result.setResponseReplyInfo(map);

        return result;
    }

    public String getDate(Date date){

        DateFormat dateFormat = DateFormat.getDateInstance();
        return  dateFormat.format(date);

    }
    public  BigDecimal getBigDecimal(String value){

        BigDecimal bigDecimal = new BigDecimal(value);

        return bigDecimal.setScale(2,BigDecimal.ROUND_DOWN);
    }

    public OrderList orderToOrderList(Order order){
        OrderList orderDto = new OrderList();
        //订单Id
        orderDto.setOrderId(order.getOrderId());

        //订单状态
        orderDto.setOrderStatus(OrderStatus.OrderStatusEnum.getOrderMessage(
                order.getOrderStatus()));
        orderDto.setOrderStatusId(order.getOrderStatus());

        //订单日期
        orderDto.setOrderCreationDate(order.getCreationDate());

        //商品名称
        orderDto.setCommodityName(
                order.getOrderCommodity().getCommodityId().getCommodityName());

        //付款人名称
        orderDto.setPayerName(order.getPayer().getRealName());
        orderDto.setPayerId(order.getPayer().getId());

        //收货人名称
        orderDto.setDeliveryName(order.getDelivery().getDeliveryName());
        orderDto.setDeliveryId(order.getDelivery().getInfoId());

        //商品总价
        BigDecimal costPrice = order.getOrderCommodity().getCommodityId().getCommodityPrice();

        orderDto.setOrderTotalPrice(costPrice);
                    /*//优惠券面额
                    BigDecimal quota = order.getOrderCommodity().getCoupon().getCouponType().getQuota();
                    //商品折扣
                    BigDecimal discount = order.getOrderCommodity().getDiscount();
                    //邮费(运费)
                    BigDecimal freight = order.getOrderCommodity().getFreight();
                    orderDto.setOrderTotalPrice(null);*/
        //商品数目
        Integer commodityNum = order.getOrderCommodity().getCommodityNum();
        orderDto.setCommodityNum(commodityNum);

        /*//快递公司
        orderDto.setExpressCompany(order.getOrderCommodity().getExpressCompany());

        //快递单号
        orderDto.setExpressNo(order.getOrderCommodity().getExpressNo());*/

        //商品图片
        orderDto.setCommodityUrl(order.getOrderCommodity().getCommodityId().getCommodityUrl());

        //运费

        orderDto.setFreight(getBigDecimal(order.getOrderCommodity().getFreight()));

        //是否付款
        orderDto.setIsPay(order.getIsPay());

        //是否售后
        orderDto.setIsAfterMarket(order.getIsAfterMarket());

        //售后处理方式
        orderDto.setAfterMarketWay(order.getAfterMarketWay());

        //拒绝理由
        orderDto.setRefuseReason(order.getRefuseReason());

        //备注
        orderDto.setRemark(order.getRemark());
        return orderDto;
    }

    public List<String> stringToList(@NotNull String listStr, @NotNull String symbol){

        if(listStr.startsWith(symbol) && listStr.length() >1){
            listStr = listStr.substring(1);
        }
        if(listStr.endsWith(symbol) && listStr.length() >1){
            listStr = listStr.substring(0,listStr.length() -1);
        }
        String []  strings= listStr.split(symbol);

        List<String> list =
                Arrays.asList(strings).stream().map(String::trim).collect(
                        Collectors.toList());

        return list;
    }

    public BigDecimal getBigDecimal(BigDecimal val){

        if( val == null){
            return new BigDecimal(0);
        }
        return val;
    }
}
