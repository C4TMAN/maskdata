package com.c4tman.play.logPrint.desense;

/**
 * Created by zhangxiaoman on 2018/5/31.
 */
public class DesensitizationUtil {


    public static String mask(String type, String data) {
        String rst = "****";
        if (type.equals("MOBILE")) {
            rst = data.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        } else if (type.equals("ID_CARD")) {
            rst = data.replaceAll("(\\S{6})\\S{9}(\\S{3})", "$1**********$2");
        } else if (type.equals("PASSWORD")) {
            rst = "********";
        } else if (type.equals("VERIFY_CODE")) {
            rst = data.replaceAll("\\S", "*");
        }
        return rst;
    }


    public static void main(String[] args) {
        System.out.println(mask("VERIFY_CODE", "12345678"));
    }
}
