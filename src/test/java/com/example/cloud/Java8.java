package com.example.cloud;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/4/17 16:35
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Java8 {

    public static void main(String [] args){
//        Arrays.asList('a','b','c').forEach(e -> System.out.println(e));

//        Arrays.asList( "a", "b", "d" ).forEach( e -> {
//            System.out.print( e );
//            System.out.print( e );
//        } );

//        String str = ",";
//        Arrays.asList("a", "b", "d").forEach( e -> System.out.println(e+str));

//        Arrays.asList("2", "1", "3").sort( ( e1, e2 ) ->  e1.compareTo( e2 ));

//        Arrays.asList( "2", "1", "3" ).sort( ( e1, e2 ) -> {
//            int result = e1.compareTo( e2 );
//            return result;
//        } );
//        System.out.println(2>>3);

//        String s1 = "Programming";
//        String s2 = new String("Programming");
//        String s3 = "Program" + "ming";
//        System.out.println(s1 == s2);
//        System.out.println(s1 == s3);
//        System.out.println(s1 == s1.intern());

        // Java 8
//        LocalDateTime dt = LocalDateTime.now();
//        System.out.println(dt.getYear());
//        System.out.println(dt.getMonthValue());     // 1 - 12
//        System.out.println(dt.getDayOfMonth());
//        System.out.println(dt.getHour());
//        System.out.println(dt.getMinute());
//        System.out.println(dt.getSecond());
//        Calendar.getInstance().getTimeInMillis();
//        System.currentTimeMillis();
//        Clock.systemDefaultZone().millis();

//        Calendar time = Calendar.getInstance();
//        System.out.println(time.getActualMaximum(Calendar.DAY_OF_MONTH));
//
//        // Java 8
//        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        LocalDate date2 = LocalDate.now();
//        System.out.println(date2.format(newFormatter));
//
//        LocalDateTime today = LocalDateTime.now();
//        LocalDateTime yesterday = today.minusDays(1);
//
//        System.out.println(yesterday);

        // 使用lambda表达式
//        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
//        costBeforeTax.stream().map((cost) -> cost + .12*cost).forEach(System.out::println);

        //获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println(stats.toString());
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }
}
