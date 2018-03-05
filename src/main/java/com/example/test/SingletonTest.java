package com.example.test;

/**
 * 单例模式
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/5 10:10
 * @Version 1.0
 */
public class SingletonTest {
    private static SingletonTest instance = null;

    private SingletonTest(){

    }

    private static synchronized void syncInit(){
        if(instance == null){
            instance = new SingletonTest();
        }
    }

    public static SingletonTest getInstance() {
        if(instance == null){
            syncInit();
        }
        return instance;
    }
}
