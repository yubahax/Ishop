package com.Ishop.common.util.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    public static String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return format.format(date);
    }

    /**
     * 获取本周开始的一天
     */

    public static String getLastWeek() {
        Calendar calendar = Calendar.getInstance();
        //获取当前日期
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar first = (Calendar) calendar.clone();
        first.add(Calendar.DAY_OF_WEEK,
                first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));
        // and add six days to the end date
        //下一周日期
        return format.format(first.getTime());
    }

    /**
     * 获取本周结束的一天
     */
    public static String getNextWeek() {
        Calendar calendar = Calendar.getInstance();
        //获取当前日期
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar first = (Calendar) calendar.clone();
        first.add(Calendar.DAY_OF_WEEK,
                first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));
        // and add six days to the end date
        Calendar last = (Calendar) first.clone();
        last.add(Calendar.DAY_OF_YEAR, 6);
        //下一周日期
        return format.format(last.getTime());
    }



    /**
     * 获取月初的一天
     */
    public static String getLastMonth() {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH,1);

        //设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        return first;

    }

    public static String getNext30Time() {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE,10);
        //设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        return first;

    }


    /**
     * 获取月末
     */
    public static String getNextMonth() {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        return last;
    }
}
