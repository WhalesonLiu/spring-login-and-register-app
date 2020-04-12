package com.caroline.fruit.core.util;

/**
 * 订单相关的枚举值
 * */
public class OrderStatus {

    /**
     * 订单状态
     * */
    public enum OrderStatusEnum{

        SCHEDULED(0, "预定中"),//预定中

        PENDING_ENTRY(1, "待录入"),//待录入

        NOT_SHIPPED(2, "未发货"),// 未发货

        SHIPPED(3, "已发货"),//已发货

        RECEIVED(4, "已签收"),//已签收

        PRAISED(5, "已好评"),//已好评

        CANCEL(6, "取消"),//取消

        FINISH(9, "完成"),//完成

        REFUNDED(20, "已退款"),//已退款

        PENDING(21, "待处理"),//待处理

        PROCESSING(22, "处理中");//处理中

        OrderStatusEnum(Integer status,String message) {
            this.status = status;
            this.message = message;
        }

        public static OrderStatusEnum getOrderStatus(Integer status){
            for (OrderStatusEnum orderStatus :  values()) {

                if(orderStatus.getStatus() == status){
                    return orderStatus;
                }

            }
            return null;
        }
        public static  String getOrderMessage(Integer status){
            for (OrderStatusEnum orderStatus :  values()) {

                if(orderStatus.getStatus() == status){
                    return orderStatus.getMessage();
                }

            }
            return null;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        private Integer status;

        private String message;

    }

    /**
     * 售后方式
     * */
    public enum AfterMarketWay{

        REFUSE(0),//拒绝
        REFUND(1),//退款
        RESEND(2);//重发

        public Integer status;

        AfterMarketWay(Integer status){
            this.status = status;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
    }

}
