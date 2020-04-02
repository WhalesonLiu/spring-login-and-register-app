package com.caroline.fruit.exception;


public enum FruitMsgEnum {

    Normal("00000","成功"),

    RedisSystemException("00001","Redis系统异常"),

    Exception("00002","系统内部异常"),

    NoRouteToHostException("00003","系统内部异常"),

    TimeOutException("00004","系统超时"),

    CommodityResultEmpty("00005","没有查询到商品"),

    InsertCommodityTypeSuccess("00000", "新增商品类型添加成功"),

    InsertCommodityTypeFailed("00006", "新增商品类型添加失败"),

    CommodityTypeResultEmpty("00007", "没有查询到商品类型"),

    InsertCommoditySuccess("00000","新增商品添加成功"),

    InsertCommodityFailed("00008","新增商品添加失败"),

    UserListEmpty("00009","没有查询到用户列表"),

    ParamEmpty("00010","参数为空"),

    AddUserSuccess("00000","新增用户添加成功"),

    AddUserFailed("00011","新增用户添加失败"),

    NotFoundDeliveryInfo("00012","没有查询到收货人信息"),

    NoLoggedInUser("00013","请先登录"),

    NotFoundUser("00014","没有查询到用户"),

    AddOrderNoCommodity("00015","新增订单中没有商品"),

    AddOrderFailed("00016","添加订单失败"),

    UserHaveNoCoupon("00017","该用户没有此优惠券"),

    AddUserCouponFailed("00018","添加或更新用户优惠券失败"),

    AddUserCouponSuccess("00019","添加或更新用户优惠券成功"),

    AddOrderSuccess("00020","添加订单成功"),

    NotFoundOrderList("00021","没有获取到订单列表");


    private String responseCode;

    private String responseMessage;

    FruitMsgEnum(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;

    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
