package com.kgregorczyk.library.library;


import com.kgregorczyk.library.model.NCoV;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NCoVTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String fieldName = "cumulativeConfirmedCasesRemark";
        System.out.println("get" + fieldName.substring(0,1).toUpperCase()+ fieldName.substring(1));

        NCoV nCoV = new NCoV();
        nCoV.setCumulativeConfirmedCasesTaiwanRemark("哈哈");
        Method getDeclaredMethod = nCoV.getClass().getDeclaredMethod("getCumulativeConfirmedCasesTaiwanRemark",null);
        Object value = getDeclaredMethod.invoke(nCoV,null);
        System.out.println(value);
    }
}
