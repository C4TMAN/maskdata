package com.c4tman.play.logPrint.desense;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

/**
 * Created by zhangxiaoman on 2018/5/29.
 */
public class MyTypeAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {

        Class<T> rawType = (Class<T>) typeToken.getRawType();
        boolean b = rawType.isAnnotationPresent(DesensitizationType.class);
        if (b) {
            return new MyTypeAdapter();
        }
        return null;
    }
}
