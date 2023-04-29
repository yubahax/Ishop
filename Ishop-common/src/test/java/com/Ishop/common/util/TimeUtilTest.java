package com.Ishop.common.util;

import com.Ishop.common.entity.User;
import junit.framework.TestCase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtilTest extends TestCase {

    public void testGetTime() throws ParseException {
        Date d =new Date();
//        System.out.println("当前时间："+ d);
//
//        SimpleDateFormat  sdf =new SimpleDateFormat();
//        String s =sdf.format(d);
//        System.out.println("当前时间： "+ s);
//
//        SimpleDateFormat  sdf2 =new SimpleDateFormat("yyyy年 MM月 dd日   HH：mm：ss");
//        String s2 =sdf2.format(d);
//        System.out.println(s2);
//
//        //String ————Date （解析）
//        //注意  再把一个字符串解析为日期的时候，注意格式必须和给定的字符串格式匹配
//        String str ="2020-05-05 12:12:12";
        String str = TimeUtil.getTime();
        SimpleDateFormat  sdf3 =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        d=sdf3.parse(str);
        System.out.println(d);
    }

    public void testGetLastWeek() {
        System.out.println(TimeUtil.getLastMonth());
    }

    public void testGetNextWeek() {
        System.out.println(TimeUtil.getNextWeek());
    }

    public void testGetLastMonth() throws ParseException {
//        System.out.println(TimeUtil.getLastMonth());
    }

    public void testGetNextMonth() {
        System.out.println(TimeUtil.getNextMonth());
    }
}