package com.caroline.fruit.core.util;

import com.caroline.fruit.model.User;
import com.caroline.fruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

        String dateTime = dateTimeFormatter.format(localDateTime);
        User loggedInUser = userService.getLoggedInUser();
        return dateTime;
    }

    public  BigDecimal getBigDecimal(String value){

        BigDecimal bigDecimal = new BigDecimal(value);

        return bigDecimal.setScale(2,BigDecimal.ROUND_DOWN);
    }
    public static void main(String[] args) {
        /*LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        System.out.println(dateTimeFormatter.format(localDateTime));*/
    }
}
