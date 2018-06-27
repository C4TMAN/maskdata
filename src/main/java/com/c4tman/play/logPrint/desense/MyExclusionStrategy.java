package com.c4tman.play.logPrint.desense;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Created by zhangxiaoman on 2018/5/29.
 */
public class MyExclusionStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        String name = fieldAttributes.getName();
//        String value = fieldAttributes.
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}
