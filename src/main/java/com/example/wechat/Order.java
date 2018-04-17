package com.example.wechat;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/4/17 15:34
 * @Version 1.0
 */
public class Order {
    private Long id;
    private String orderNumber;
    private int sumFee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getSumFee() {
        return sumFee;
    }

    public void setSumFee(int sumFee) {
        this.sumFee = sumFee;
    }
}
