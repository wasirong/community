package com.lzt.ylu.community.utils;

import java.util.Date;

public class TimeUtils {

    public static Date getTime(){

        long time = System.currentTimeMillis();

        Date date = new Date(time);

        return date;
    }

}