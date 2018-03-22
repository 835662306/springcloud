package com.example.utils;

import java.net.InetAddress;

/**
 * UUID生成
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/22 9:24
 * @Version 1.0
 */
public class UUIDGenerator {

    private final static int IP;

    private static final int JVM = (int)(System.currentTimeMillis() >>> 8);

    private static short counter = (short) 0;

    static{
        int ipadd = 0;
        try {
            ipadd = toInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
        IP = ipadd;
    }

    public static String generate() {
        return new StringBuilder(32).append(formate(getIp()))
                .append(formate(getJvm())).append(formate(getHiTime()))
                .append(formate(getLoTime())).append(formate(getCounter())).toString();
    }

    private static final String formate(int val) {
        //val以十六进制
        String formate = Integer.toHexString(val);
        StringBuilder builder = new StringBuilder("00000000");
        builder.replace(8-formate.length(), 8, formate);
        return builder.toString();
    }

    private static final String formate(short val) {
        String formate = Integer.toHexString(val);
        StringBuilder builder = new StringBuilder("0000");
        builder.replace(4-formate.length(), 4, formate);
        return builder.toString();
    }


    private static final int getIp(){return IP;}

    private static final int getJvm(){return JVM;}

    /**
     * Unique down to millisecond
     */
    private final static short getHiTime() {
        return (short) (System.currentTimeMillis() >>> 32);
    }

    private final static int getLoTime() {
        return (int) System.currentTimeMillis();
    }

    private static final short getCounter() {
        synchronized (UUIDGenerator.class){
            if(counter < 0) counter = 0;
            return counter++;
        }
    }

    private static int toInt(byte[] bytes) {
        int result = 0;
        for(int i = 0; i < 4; i++){
            result = (result << 8) - Byte.MIN_VALUE + (int)bytes[i];
        }
        return result;
    }

    public static void main(String [] args){
        System.out.println(generate());
    }
}
