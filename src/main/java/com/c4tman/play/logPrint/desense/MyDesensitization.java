package com.c4tman.play.logPrint.desense;

/**
 * Created by zhangxiaoman on 2018/5/29.
 */
public class MyDesensitization {

    public static String trueName(String data){
        Integer length = data.length();
        data = data.replaceAll("(\\S{1})(\\S+)", length == 2 ? "$1*" : "$1**");
        return data;
    }

    public static String area(String data){

        return "脱敏地址："+data;
    }

    public static String book(String data){

        return "**"+data+"**";
    }
}
