package com.c4tman.play.logPrint.desense;

/**
 * Created by zhangxiaoman on 2018/6/27.
 */
public enum GenderEnum {
    MALE("male","男性"),
    FEMALE("female", "女性");

    private String value;
    private String desc;

    GenderEnum(String val, String desc) {
        this.value = val;
        this.desc = desc;
    }
}
