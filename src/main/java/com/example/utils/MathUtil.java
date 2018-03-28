package com.example.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/23 15:44
 * @Version 1.0
 */
public class MathUtil {

    /**
     * 两个数相加
     * @param num1
     * @param num2
     * @return
     */
    public static double add(double num1, double num2) {
        BigDecimal b1 = new BigDecimal(num1);
        BigDecimal b2 = new BigDecimal(num2);
        return b1.add(b2).doubleValue();
    }

    /**
     * setScale(1)表示保留一位小数，默认用四舍五入方式
     * setScale(1,BigDecimal.ROUND_DOWN)直接删除多余的小数位，如2.35会变成2.3
     * setScale(1,BigDecimal.ROUND_UP)进位处理，2.35变成2.4
     * setScale(1,BigDecimal.ROUND_HALF_UP)四舍五入，2.35变成2.4
     * setScaler(1,BigDecimal.ROUND_HALF_DOWN)四舍五入，2.35变成2.3，如果是5则向下舍
     * @param num1
     * @param num2
     * @param setPrecision
     * @return
     */
    public static double add(double num1, double num2, int setPrecision) {
        BigDecimal b1 = new BigDecimal(num1);
        BigDecimal b2 = new BigDecimal(num2);
        //方法用于格式化小数点,保留几位小数
        return b1.add(b2).setScale(setPrecision, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 两个数相减
     * @param num1
     * @param num2
     * @return
     */
    public static double subtract(double num1, double num2) {
        BigDecimal b1 = new BigDecimal(num1);
        BigDecimal b2 = new BigDecimal(num2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 相当于num1-num2
     * @param num1
     * @param num2
     * @return
     */
    public static double subtract(double num1, double num2, int setPrecision) {
        BigDecimal b1 = new BigDecimal("" + num1);
        BigDecimal b2 = new BigDecimal("" + num2);
        return b1.subtract(b2).setScale(setPrecision, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 相当于num1*num2
     * @param num1
     * @param num2
     * @return
     */
    public static double multiply(double num1, double num2) {
        BigDecimal b1 = new BigDecimal("" + num1);
        BigDecimal b2 = new BigDecimal("" + num2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 相当于num1*num2
     * @param num1
     * @param num2
     * @return
     */
    public static double multiply(double num1, double num2, int setPrecision) {
        BigDecimal b1 = new BigDecimal("" + num1);
        BigDecimal b2 = new BigDecimal("" + num2);
        return b1.multiply(b2).setScale(setPrecision, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 相当于num1/num2
     * @param num1
     * @param num2
     * @return
     */
    public static double divide(double num1, double num2) {
        return divide(num1, num2, 8);
    }

    /**
     * 相当于num1/num2
     * @param num1
     * @param num2
     * @return
     */
    public static double divide(double num1, double num2, int setPrecision) {
        BigDecimal b1 = new BigDecimal("" + num1);
        BigDecimal b2 = new BigDecimal("" + num2);
        return b1.divide(b2, new MathContext(30)).setScale(setPrecision, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 相当于num1%num2
     * @param num1
     * @param num2
     * @return
     */
    public static double remainder(double num1, double num2) {
        BigDecimal b1 = new BigDecimal("" + num1);
        BigDecimal b2 = new BigDecimal("" + num2);
        return b1.remainder(b2).doubleValue();
    }

    /**
     * 相当于对num1/num2的结果取整
     * @param num1
     * @param num2
     * @return
     */
    public static double divideToIntegralValue(double num1, double num2) {
        BigDecimal b1 = new BigDecimal("" + num1);
        BigDecimal b2 = new BigDecimal("" + num2);
        return b1.divideToIntegralValue(b2).doubleValue();
    }
}
