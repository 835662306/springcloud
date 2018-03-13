package com.example.test;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/13 11:36
 * @Version 1.0
 */
@Component
public class AsyncTask {
    protected final Logger logger = Logger.getLogger(AsyncTask.class);

    @Async("mySimpleAsync")
    public Future<String> doTask1() throws InterruptedException{
        logger.info("Task1 started.");
        long start = System.currentTimeMillis();
        Thread.sleep(5000);
        long end = System.currentTimeMillis();
        logger.info("Task1 finished, time elapsed: {"+ (end-start)+"} ms.");

        return new AsyncResult<>("Task1 accomplished!");
    }

    @Async("myAsync")
    public Future<String> doTask2() throws InterruptedException{
        logger.info("Task2 started.");
        long start = System.currentTimeMillis();
        Thread.sleep(3000);
        long end = System.currentTimeMillis();

        logger.info("Task2 finished, time elapsed: {"+ (end-start)+"} ms.");

        return new AsyncResult<>("Task2 accomplished!");
    }
}
