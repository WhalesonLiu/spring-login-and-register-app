package com.caroline.fruit.interceptor;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class FruitResponseWrapper extends HttpServletResponseWrapper {

    public FruitResponseWrapper(HttpServletResponse response) {

        super(response);


    }
}
