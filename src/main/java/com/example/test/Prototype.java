package com.example.test;

/**
 * 原型模式（prototype）
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/5 10:18
 * @Version 1.0
 */
public class Prototype implements Cloneable{

    public Object clone() throws CloneNotSupportedException {
        Prototype prototype = (Prototype) super.clone();
        return prototype;
    }
}
