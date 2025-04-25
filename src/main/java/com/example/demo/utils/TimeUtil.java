package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    public static String timeFmt = "yyyyMMdd'T'HHmmss'Z'";
    public static String timeFmt1 = "yyyy-MM-dd HH:mm:ss";

    public static String timeFmt2 = "yyyy-MM-dd'T'HH:mm";

    public static String getTime(String display_time){


        SimpleDateFormat datestr=new SimpleDateFormat(TimeUtil.timeFmt);
        SimpleDateFormat timeFmt2=new SimpleDateFormat(TimeUtil.timeFmt2);
        String str="";

        try{
            Date date=timeFmt2.parse(display_time);
            str = datestr.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(str);
        return str;
    }

    public static void main(String[] args) {
        TimeUtil util = new TimeUtil();
//        util.getTime("2022-02-26 02:52:56");
        util.getTime("2022-02-26T02:52");
    }
}
