package com.c4tman.play.logPrint.desense;

import com.google.gson.FieldNamingStrategy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by zhangxiaoman on 2018/5/29.
 */
public class MyFieldNamingStrategy implements FieldNamingStrategy {
    @Override
    public String translateName(Field field) {
        String name = field.getName();
        Annotation[]  annotations = field.getDeclaredAnnotations();
        if(annotations==null){

        }else {
            for(Annotation annotation : annotations){
                Class Z =  annotation.getClass();
                String cName = Z.getName();
            }
        }
        return name;
    }
}
