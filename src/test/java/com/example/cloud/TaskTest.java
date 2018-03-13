package com.example.cloud;

import com.example.test.AsyncTask;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/13 11:41
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskTest {
    private Logger logger = Logger.getLogger(TaskTest.class);

    @Autowired
    private AsyncTask asyncTask;

    @org.junit.Test
    public void AsyncTaskTest() throws InterruptedException, ExecutionException {
        Future<String> task1 = asyncTask.doTask1();
        Future<String> task2 = asyncTask.doTask2();

        while(true) {
            if(task1.isDone() && task2.isDone()) {
                logger.info("Task1 result: {"+ task1.get()+"}");
                logger.info("Task2 result: {"+ task2.get()+"}");
                break;
            }
            Thread.sleep(1000);
        }

        logger.info("All tasks finished.");
    }
}
