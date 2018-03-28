package com.example.test.exercise;

import java.util.*;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/23 10:47
 * @Version 1.0
 */
public class JavaSort {
    public static void sortNumbersInArrayList() {
        List<Integer> integers = new ArrayList<>();
        integers.add(5);
        integers.add(10);
        integers.add(0);
        integers.add(-1);
        System.out.println(integers);
        Collections.sort(integers);
        System.out.println(integers);
        Collections.sort(integers,Collections.reverseOrder());
        System.out.println(integers);
    }

    public static void sortNumbersInSet() {
        Set<Integer> set = new HashSet<>();
        set.add(5);
        set.add(10);
        set.add(0);
        set.add(-1);
        System.out.println(set);
        List list = new ArrayList(set);
        Collections.sort(list);
        System.out.println(list);
        Collections.sort(list,Collections.reverseOrder());
        System.out.println(list);
    }

    private static void sortNumbersInTreeSet() {
        Set<Integer> integers = new TreeSet<>();
        integers.add(5);
        integers.add(10);
        integers.add(0);
        integers.add(-1);
        System.out.println("Original set: " + integers);
        System.out.println("Sorted set: "+ integers);
        Set<Integer> reversedIntegers = new TreeSet(Collections.reverseOrder());
        reversedIntegers.add(5);
        reversedIntegers.add(10);
        reversedIntegers.add(0);
        reversedIntegers.add(-1);
        System.out.println("Reversed set: " + reversedIntegers);
    }


    public static void main(String [] args){
//        sortNumbersInArrayList();

        sortNumbersInTreeSet();
    }
}
