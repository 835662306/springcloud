package com.example.utils.date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/16 15:07
 * @Version 1.0
 */
public class DateUtil {
    public static String yyyy_MM_dd = "yyyy-MM-dd";
    public static String yyyyMMdd = "yyyyMMdd";
    public final static String FORMAT_19_2 = "yyyy/MM/dd HH:mm:ss";
    public final static String FORMAT_19 = "yyyy-MM-dd HH:mm:ss";
    public final static String FORMAT_19_1 = "yyyy.MM.dd";
    public final static String FORMAT_20 = "yyyyMMddHH:mm:ss";
    public final static String YYYY_MM_DD_HH = "yyyy-MM-dd-HH";
    public static String yyyyMMdd_1 = "yyyy/MM/dd";
    public final static String HHMMSS = "HH:mm:ss";
    public final static String HHMM = "HH:mm";
    public final static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public final static String FORMAT_19_00 = "yyyy-MM-dd 00:00:00";

    /**
     * 获得时间相差的天数
     * @param start
     * @param end
     * @return
     */
    public static long getDifferenceDay(Long start,Long end){
        long l = start - end;
        if (l > 0) {
            return l / 1000 / 60 / 60 / 24;
        }else {
            return 0;
        }
    }

    /**
     * 得到本周周日
     *
     * @Timestamp
     */
    public static Timestamp getSundayOfThisWeek() {
        Timestamp tp;
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 7);
        tp = new Timestamp(c.getTime().getTime());
        return tp;
    }

    /**
     * 得到本周周一
     *
     * @Timestamp
     */
    public static Timestamp getMondayOfThisWeek() {
        Timestamp tp;
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1);
        tp = new Timestamp(c.getTime().getTime());
        return tp;
    }

    /**
     * 得到本月的第一天
     * @return
     */
    public static Timestamp getMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMinimum(Calendar.DAY_OF_MONTH));
        return new Timestamp(calendar.getTime().getTime());
    }

    /**
     * 得到本月的最后一天
     *
     * @return
     */
    public static Timestamp getMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMaximum(Calendar.DAY_OF_MONTH));
        return new Timestamp(calendar.getTime().getTime());
    }

    /**
     * 某天最大时间 及:2015-05-05 23:59:59
     *
     * @param day
     * @return
     */
    public static Date getMaxTimeOfDay(Date day) {
        if (day == null) return null;
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * 某天最小时间 及:2015-05-05 00:00:00
     *
     * @param day
     * @return
     */
    public static Date getMinTimeOfDay(Date day) {
        if (day == null) return null;
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND,0);
        return c.getTime();
    }

    /**
     * 取得当前日期是多少周
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        /**设置一年中第一个星期所需的最少天数，例如，如果定义第一个星期包含一年第一个月的第一天，则使用值 1 调用此方法。
         * 如果最少天数必须是一整个星期，则使用值 7 调用此方法。 **/
        c.setMinimalDaysInFirstWeek(1);
        c.setTime(date);

        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 得到某一年周的总数
     *
     * @param year
     * @return
     */
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = Calendar.getInstance();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

        return getWeekOfYear(c.getTime());
    }

    /**
     * 得到某年某周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.WEEK_OF_YEAR, week);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//设置周一
        c.setFirstDayOfWeek(Calendar.MONDAY);

        return c.getTime();
    }

    /**
     * 得到日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        /**设置一年中第一个星期所需的最少天数，例如，如果定义第一个星期包含一年第一个月的第一天，则使用值 1 调用此方法。
         * 如果最少天数必须是一整个星期，则使用值 7 调用此方法。 **/
        c.setMinimalDaysInFirstWeek(1);
        c.setTime(date);

        return getFirstDayOfWeek(c.get(Calendar.YEAR), c.get(Calendar.WEEK_OF_YEAR));
    }

    /**
     * 得到某年某周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.WEEK_OF_YEAR, week);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday

        return c.getTime();
    }

    /**
     * 得到日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        /**设置一年中第一个星期所需的最少天数，例如，如果定义第一个星期包含一年第一个月的第一天，则使用值 1 调用此方法。
         * 如果最少天数必须是一整个星期，则使用值 7 调用此方法。 **/
        c.setMinimalDaysInFirstWeek(1);
        c.setTime(date);

        return getLastDayOfWeek(c.get(Calendar.YEAR), c.get(Calendar.WEEK_OF_YEAR));
    }

    /**
     * 得到某年某月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));

        return c.getTime();
    }

    /**
     * 得到某年某月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

        return c.getTime();
    }

    /**
     * 得到某年某季度第一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getFirstDayOfQuarter(int year, int quarter) {
        int month = 0;
        if (quarter > 4) {
            return null;
        } else {
            month = (quarter - 1) * 3 + 1;
        }
        return getFirstDayOfMonth(year, month);
    }

    /**
     * 得到某年某季度最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfQuarter(int year, int quarter) {
        int month = 0;
        if (quarter > 4) {
            return null;
        } else {
            month = quarter * 3;
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 得到某年第一天
     *
     * @param year
     * @return
     */
    public static Date getFirstDayOfYear(int year) {
        return getFirstDayOfQuarter(year, 1);
    }

    /**
     * 得到某年最后一天
     *
     * @param year
     * @return
     */
    public static Date getLastDayOfYear(int year) {
        return getLastDayOfQuarter(year, 4);
    }


    /**
     * 得到某日期前若干天的日期
     *
     * @param date 原始日期
     * @param day  前多少天,如果传入负数则是后多少天
     * @return
     */
    public static Date getDateBefore(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, c.get(Calendar.DATE) - day);
        return c.getTime();
    }

    /**
     * 按照指定格式获取当前时间
     *
     * @param format
     * @return
     */
    public static String getCurrentTime(String format) {
        String dateStr = null;
        SimpleDateFormat ymd = new SimpleDateFormat(format);
        dateStr = ymd.format(new Date());
        return dateStr;
    }

    /**
     * 把Date类型日期按照指定格式转换成字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDateToStr(Date date, String format) {
        String dateTime = null;
        SimpleDateFormat ymd = new SimpleDateFormat(format);
        try {
            dateTime = ymd.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateTime;
    }

    /**
     * 获取当前日期，截止到日
     *
     * @return
     */
    public static Date getCurrDateByDay() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 日期字符串转Date对象
     *
     * @param str
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String str, String format) throws ParseException {
        if (str == null || "".equals(str)) return null;
        SimpleDateFormat ymd = new SimpleDateFormat(format != null && !"".equals(format) ? format : yyyy_MM_dd);
        return ymd.parse(str);
    }

    /**
     * 两个日期相差天数
     * @param data1
     * @param date2
     * @return
     */
    public static long diffDay(Date data1,Date date2){
        long diff = data1.getTime() - date2.getTime();
        diff=Math.abs(diff);
        long days = diff / (1000 * 60 * 60 * 24);
        return days;
    }

    /**
     * 两个日期相差天数
     * @param date1
     * @param date2
     * @return
     */
    public static long diffDays(Date date1,Date date2){
        long diff = date1.getTime() - date2.getTime();
        long days = diff / (1000 * 60 * 60 * 24);
        return days;
    }

}
