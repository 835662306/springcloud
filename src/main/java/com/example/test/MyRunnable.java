package com.example.test;

/**
 * 一个类只能继承一个父类，存在局限；一个类可以实现多个接口。
 * 在实现Runnable接口的时候调用Thread的Thread(Runnable run)或者Thread(Runnablerun,String name)构造方法创建进程时，
 * 使用同一个Runnable实例，建立的多线程的实例变量也是共享的；但是通过继承Thread类是不能用一个实例建立多个线程，故而实现Runnable接口适合于资源共享；
 * 当然，继承Thread类也能够共享变量，能共享Thread类的static变量
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/2/23 10:05
 * @Version 1.0
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

class ThreadDemoTest extends Thread {
    private Thread t;
    private String threadName;

    ThreadDemoTest( String name) {
        threadName = name;
        System.out.println("Creating " +  threadName );
    }

    public void run() {
        System.out.println("Running " +  threadName );
        try {
            for(int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                // 让线程睡眠一会
                Thread.sleep(50);
            }
        }catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
        System.out.println("Thread " +  threadName + " exiting.");
    }

    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}
class TestThread {
    /**
     * 该方法尽管被列为一种多线程实现方式，但是本质上也是实现了 Runnable 接口的一个实例。
     * @param args
     */
    public static void main(String args[]) {
        ThreadDemoTest T1 = new ThreadDemoTest( "Thread-1");
        T1.start();

        ThreadDemoTest T2 = new ThreadDemoTest( "Thread-2");
        T2.start();
    }
}