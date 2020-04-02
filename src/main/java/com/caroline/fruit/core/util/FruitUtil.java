package com.caroline.fruit.core.util;

import com.caroline.fruit.message.Result;
import com.caroline.fruit.model.User;
import com.caroline.fruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

}
