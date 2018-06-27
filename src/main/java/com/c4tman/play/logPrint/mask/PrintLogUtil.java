package com.c4tman.play.logPrint.mask;

import com.c4tman.play.logPrint.desense.MyFieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangxiaoman on 2018/5/31.
 */
public class PrintLogUtil {
    private static Gson GSON = new GsonBuilder().setFieldNamingStrategy(new MyFieldNamingStrategy()).create();

    /**
     * 打印
     *
     * @param o
     * @return
     */
    public static Map<String, Object> print(Object o) {
        Class c = o.getClass();
        Map<String, Object> params = new HashMap<>();

//        try {
//            Field[] fields = c.getDeclaredFields();
//            for (Field f : fields) {
//                Class<?> clazz = f.getType();
//                boolean b = clazz.isPrimitive();
//                boolean isString = clazz.getName().equals(String.class.getName());
//                if (isString) { //String脱敏
//                    String paramName = f.getName();
//                    boolean isExist = f.isAnnotationPresent(Desensitization.class);
//                    Method m2 = c.getDeclaredMethod(getMethodName(paramName));
//                    Object oo= m2.invoke(o);
//                    if(oo == null){
//                        continue;
//                    }
//                    String value = m2.invoke(o).toString();
//                    if (isExist) {
//                        Desensitization d1 = f.getAnnotation(Desensitization.class);
//                        if (d1.type().name().equals("OTHER")) {
//                            String className = d1.className();
//                            String methodName = d1.method();
//
//                            Class clz = Class.forName(className);
//                            Object obj = clz.newInstance();
//                            Method m = obj.getClass().getDeclaredMethod(methodName, String.class);
//                            value = (String) m.invoke(obj, value);
//                        } else {
//                            value = DesensitizationUtil.mask(d1.type().name(), value);
//                        }
//                    }
//                    params.put(paramName, value);
//                } else if(!b){
////                    TODO 我还不能解决多层脱敏问 😔
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
       return params;
    }

    public static String getMethodName(String string) {
        char[] charArray = string.toCharArray();
        charArray[0] -= 32;
        return "get" + String.valueOf(charArray);
    }
}
