package com.example.test;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/2/23 10:02
 * @Version 1.0
 */
public class Test extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}


class ThreadTest {
    public static void main(String [] args){
        Test test = new Test();
        test.start();

        MyRunnable myRunnable = new MyRunnable();
        Thread runnable = new Thread(myRunnable);
        runnable.start();
    }
}