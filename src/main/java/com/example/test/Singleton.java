package com.example.test;

/**
 * 单例模式
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/5 10:03
 * @Version 1.0
 */
public class Singleton {

    /**
     * 私有方法，防止被实例化
     */
    private Singleton() {
    }

    /**
     * 此处使用一个内部类来维护单例
     */
    private static class SingletonFactory {
        private static Singleton singleton = new Singleton();
    }

    /*获取实例*/
    private static Singleton instance() {
        return SingletonFactory.singleton;
    }

    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
    public Object readResolve() {
        return instance();
    }
}
