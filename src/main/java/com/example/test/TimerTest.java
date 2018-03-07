package com.example.test;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 预防sql注入解决方法：
 * 1：PreparedStatement 采用预编译语句集，它内置了处理SQL注入的能力，只要使用它的setXXX方法传值即可
 * (1).代码的可读性和可维护性.
 * (2).PreparedStatement尽最大可能提高性能.
 * (3).最重要的一点是极大地提高了安全性.
 * 原理：
 * sql注入只对sql语句的准备(编译)过程有破坏作用
 * 而PreparedStatement已经准备好了,执行阶段只是把输入串作为数据处理,
 * 而不再对sql语句进行解析,准备,因此也就避免了sql注入问题.
 * 2：使用正则表达式过滤传入的参数
 * 3.字符串过滤
 * 4.jsp中调用该函数检查是否包函非法字符
 * 5.JSP页面判断代码
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/6 14:31
 * @Version 1.0
 */
@EnableScheduling
@Component
public class TimerTest {
    /**
     * lock更灵活，可以自由定义多把锁的枷锁解锁顺序（synchronized要按照先加的后解顺序）
     * 提供多种加锁方案，lock 阻塞式, trylock 无阻塞式, lockInterruptily 可打断式， 还有trylock的带超时时间版本。
     * 本质上和监视器锁（即synchronized是一样的）
     * 能力越大，责任越大，必须控制好加锁和解锁，否则会导致灾难。
     * 和Condition类的结合。
     * 性能更高
     *
     * 加锁：lock.lock()或 lock.lockInterruptibly();
     * 此处也是个不同，后者可被打断。当a线程lock后，b线程阻塞，此时如果是lockInterruptibly，
     * 那么在调用b.interrupt()之后，b线程退出阻塞，并放弃对资源的争抢，进入catch块
     *
     * 释放锁：r.unlock()
     * 必须做！何为必须做呢，要放在finally里面。以防止异常跳出了正常流程，导致灾难。这里补充一个小知识点，finally是可以信任的：经过测试，
     * 哪怕是发生了OutofMemoryError，finally块中的语句执行也能够得到保证。
     */
    private static ReentrantLock lock = new ReentrantLock();

    /**
     * *：代表所有可能的值
     * -：指定范围
     * ,：列出枚举  例如在分钟里，"5,15"表示5分钟和20分钟触发
     * /：指定增量  例如在分钟里，"3/15"表示从3分钟开始，没隔15分钟执行一次
     * ?：表示没有具体的值，使用?要注意冲突
     * L：表示last，例如星期中表示7或SAT，月份中表示最后一天31或30，6L表示这个月倒数第6天，FRIL表示这个月的最后一个星期五
     * W：只能用在月份中，表示最接近指定天的工作日
     * #：只能用在星期中，表示这个月的第几个周几，例如6#3表示这个月的第3个周五

     * 0 * * * * ? 每1分钟触发一次
     * 0 0 * * * ? 每天每1小时触发一次
     * 0 0 10 * * ? 每天10点触发一次
     * 0 * 14 * * ? 在每天下午2点到下午2:59期间的每1分钟触发
     * 0 30 9 1 * ? 每月1号上午9点半
     * 0 15 10 15 * ? 每月15日上午10:15触发
     * 5 * * * * ? 每隔5秒执行一次
     */
    // 0 */1 * * * ? 每隔1分钟执行一次
    // 0 0 5-15 * * ? 每天5-15点整点触发
    // * 0 0/3 * * * ? 每三分钟触发一次
    @Scheduled(cron = "*/5 * * * * ?")
    public void execute(){
        System.out.println("每五秒执行一次");
    }

    public static void main(String [] args){
        timer3();
    }
    // 第一种方法：设定指定任务task在指定时间time执行 schedule(TimerTask task, Date time)
    public static  void  time(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("-----2000--------");
            }
        }, 2000);// 设定指定的时间time,此处为2000毫秒
    }

    // 第二种方法：设定指定任务task在指定延迟delay后进行固定延迟peroid的执行
    // schedule(TimerTask task, long delay, long period)
    public static void timer2() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
           public void run() {
               System.out.println("-------设定要指定任务--------");
           }
        }, 1000, 5000);
    }

    // 第三种方法：设定指定任务task在指定延迟delay后进行固定频率peroid的执行。
    // scheduleAtFixedRate(TimerTask task, long delay, long period)
    public static void timer3() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("-------设定要指定任务--------");
            }
        }, 1000, 5000);
    }

    // 第四种方法：安排指定的任务task在指定的时间firstTime开始进行重复的固定速率period执行．
    // Timer.scheduleAtFixedRate(TimerTask task,Date firstTime,long period)
    public static void timer4() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date time = calendar.getTime();     // 得出执行任务的时间,此处为今天的12：00：00

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("-------设定要指定任务--------");
            }
        }, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
    }
}
