package com.c4tman.play.logPrint.desense;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangxiaoman on 2018/5/29.
 */
public class MyTypeAdapter extends TypeAdapter {
    private static Gson GSON = new GsonBuilder().setFieldNamingStrategy(new MyFieldNamingStrategy()).create();


    /**
     * 序列化
     *
     * @param jsonWriter
     * @param o
     * @throws IOException
     */
    @Override
    public void write(JsonWriter jsonWriter, Object o) throws IOException {
        Class c = o.getClass();
        boolean exist = c.isAnnotationPresent(DesensitizationType.class);
        if (exist) {
            jsonWriter.beginObject();
            try {
                writeObject(jsonWriter, o);
            } catch (Exception e) {
                e.printStackTrace();
            }
            jsonWriter.endObject();
        }
    }

    public void writeObject(JsonWriter jsonWriter, Object o) throws Exception {
        Class c = o.getClass();
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields) {
            Class<?> clazz = f.getType();
            String paramName = f.getName();
            Method m2 = c.getDeclaredMethod(getMethodName(clazz, paramName));
            Object oo = m2.invoke(o);
            if (oo == null) {
                continue;
            }
            boolean isBasic = clazz.isPrimitive(); //是否为基础类型
            boolean isString = clazz.getName().equals(String.class.getName()); //是否为String类型
            if (isBasic || isNumber(clazz) || isString || isBoolean(clazz) || clazz.isEnum()) { //String脱敏
                Object value = mask(f, oo);
                writeValue(paramName, value, jsonWriter);
            } else if (isCollection(clazz)) {
                writeCollection(jsonWriter, paramName, oo, f);
            } else if (isMap(clazz)) {
                writeMap(jsonWriter, paramName, oo);
            } else {
                write2(jsonWriter, paramName, oo);
            }
        }

    }

    public void write2(JsonWriter jsonWriter, String param, Object o) throws Exception {
        if (StringUtils.isEmpty(param)) {
            jsonWriter.beginObject();
        } else {
            jsonWriter.name(param).beginObject();
        }
        writeObject(jsonWriter, o);
        jsonWriter.endObject();
    }

    private void writeCollection(JsonWriter jsonWriter, String param, Object o, Field field) throws Exception {
        if (List.class.isAssignableFrom(o.getClass())) {
            Type t = field.getGenericType();
            ParameterizedType pt = (ParameterizedType) t;
            Type[] ts = pt.getActualTypeArguments();//这样就获取了List<ItemVo>中的泛型
            Type type = ts[0];
            writeList(jsonWriter, param, o, type);
        } else if (o.getClass().isArray()) {
            Class<?> componentType = (field.getType()).getComponentType();
            writeArray(jsonWriter, param, o, componentType);
        } else {
            throw new RuntimeException("无法识别的类型");
        }
    }

    private void writeMap(JsonWriter jsonWriter, String param, Object o) throws IOException {
        if (null != o) {
//            jsonWriter.name(param).beginObject();
            String value = GSON.toJson(o);
            jsonWriter.name(param).value(GSON.toJson(o));
//            jsonWriter.endObject();
        }
    }

    private void writeList(JsonWriter jsonWriter, String param, Object o, Type innerType) throws Exception {
        if (StringUtils.isEmpty(param)) {
            jsonWriter.beginArray();
        } else {
            jsonWriter.name(param).beginArray();
        }
        if (innerType.equals(String.class)
                || innerType.getClass().isPrimitive()
                || innerType.equals(Integer.class)
                || innerType.equals(Double.class)
                || innerType.equals(Float.class)
                || innerType.equals(Boolean.class)
                || innerType.equals(Long.class)
                || innerType.equals(Byte.class)) {
            for (Object oo : (Collection) o) {
                jsonWriter.value(oo.toString());
            }
        } else {
            for (Object oo : (Collection) o) {
                Type tt = oo.getClass();
                if (tt.equals(String.class)
                        || innerType.getClass().isPrimitive()
                        || tt.equals(Integer.class)
                        || tt.equals(Double.class)
                        || tt.equals(Float.class)
                        || tt.equals(Boolean.class)
                        || tt.equals(Long.class)
                        || tt.equals(Byte.class)) {
                    writeValue(null, oo, jsonWriter);
                } else if (isCollection(oo.getClass())) {
                    writeList(jsonWriter, null, oo, oo.getClass());
                } else if (isMap(oo.getClass())) {
                    //TODO 集合嵌套Map
                } else {
                    write2(jsonWriter, null, oo);
                }
            }
        }
        jsonWriter.endArray();
    }

    private void writeArray(JsonWriter jsonWriter, String param, Object o, Type innerType) throws Exception {
        if (StringUtils.isEmpty(param)) {
            jsonWriter.beginArray();
        } else {
            jsonWriter.name(param).beginArray();
        }
        boolean isBasic = innerType.getTypeName().equals("int")
                || innerType.getTypeName().equals("boolean")
                || innerType.getTypeName().equals("float")
                || innerType.getTypeName().equals("double")
                || innerType.getTypeName().equals("long")
                || innerType.getTypeName().equals("char")
                || innerType.getTypeName().equals("byte")
                || innerType.getTypeName().equals("short");

        if (innerType.getTypeName().equals("int")) {
            for (int oo : (int[]) o) {
                jsonWriter.value(oo);
            }
        } else if (innerType.getTypeName().equals("boolean")) {
            for (boolean oo : (boolean[]) o) {
                jsonWriter.value(oo);
            }
        } else if (innerType.getTypeName().equals("float")) {
            for (float oo : (float[]) o) {
                jsonWriter.value(oo);
            }
        } else if (innerType.getTypeName().equals("double")) {
            for (double oo : (double[]) o) {
                jsonWriter.value(oo);
            }
        } else if (innerType.getTypeName().equals("char")) {
            for (char oo : (char[]) o) {
                jsonWriter.value(oo);
            }
        } else if (innerType.getTypeName().equals("byte")) {
            for (byte oo : (byte[]) o) {
                jsonWriter.value(oo);
            }
        } else if (innerType.getTypeName().equals("short")) {
            for (short oo : (short[]) o) {
                jsonWriter.value(oo);
            }
        } else if (innerType.getTypeName().equalsIgnoreCase(STRING)) {
            for (String oo : (String[]) o) {
                jsonWriter.value(oo);
            }
        } else {
            for (Object oo : (Object[]) o) {
                write2(jsonWriter, null, oo);
            }
        }
        jsonWriter.endArray();
    }

    /**
     * 反序列化
     *
     * @param jsonReader
     * @return
     * @throws IOException
     */
    @Override
    public Object read(JsonReader jsonReader) throws IOException {
        return jsonReader.nextString();
    }

    private String getMethodName(Class<?> clazz, String string) {

        char[] charArray = string.toCharArray();
        charArray[0] -= 32;
        if (clazz.isAssignableFrom(boolean.class)) {
            return "is" + String.valueOf(charArray);
        }
        return "get" + String.valueOf(charArray);
    }

    private Object mask(Field f, Object oo) throws Exception {

        boolean isExist = f.isAnnotationPresent(Desensitization.class);

        if (isExist) { //只对字符串脱敏
            String value = oo.toString();
            Desensitization d1 = f.getAnnotation(Desensitization.class);
            if (d1.type().name().equals("OTHER")) {
                String className = d1.className();
                String methodName = d1.method();
                Class clz = Class.forName(className);
                Object obj = clz.newInstance();
                Method m = clz.getDeclaredMethod(methodName, String.class);
                value = (String) m.invoke(obj, value);
            } else {
                value = DesensitizationUtil.mask(d1.type().name(), value);
            }
            return value;
        }
        return oo;
    }

    private void writeValue(String name, Object o, JsonWriter jsonWriter) throws IOException {
        Class clazz = o.getClass();
        String className = clazz.getName();
        if (StringUtils.isEmpty(name)) {
            if (className.equalsIgnoreCase(STRING)) {
                jsonWriter.value((String) o);
            } else if (className.equalsIgnoreCase(LONG)) {
                jsonWriter.value((long) o);
            } else if (className.equalsIgnoreCase(INT)) {
                jsonWriter.value((int) o);
            } else if (className.equalsIgnoreCase(DOUBLE)) {
                jsonWriter.value((double) o);
            } else if (className.equalsIgnoreCase(FLOAT)) {
                jsonWriter.value((float) o);
            } else if (className.equalsIgnoreCase(BOOLEAN)) {
                jsonWriter.value((boolean) o);
            } else if (className.equalsIgnoreCase(NUMBER)) {
                jsonWriter.value((Number) o);
            } else {
                jsonWriter.value(o.toString());
            }
        } else {
            if (className.equalsIgnoreCase(STRING)) {
                jsonWriter.name(name).value((String) o);
            } else if (className.equalsIgnoreCase(LONG)) {
                jsonWriter.name(name).value((long) o);
            } else if (className.equalsIgnoreCase(INT)) {
                jsonWriter.name(name).value((int) o);
            } else if (className.equalsIgnoreCase(DOUBLE)) {
                jsonWriter.name(name).value((double) o);
            } else if (className.equalsIgnoreCase(FLOAT)) {
                jsonWriter.name(name).value((float) o);
            } else if (className.equalsIgnoreCase(BOOLEAN)) {
                jsonWriter.name(name).value((boolean) o);
            } else if (className.equalsIgnoreCase(NUMBER)) {
                jsonWriter.name(name).value((Number) o);
            } else {
                jsonWriter.name(name).value(o.toString());
            }
        }
    }

    private boolean isCollection(Class<?> clazz) {
        return Collection.class.isAssignableFrom(clazz) || clazz.isArray();
    }

    private boolean isMap(Class<?> clazz) {
        return Map.class.isAssignableFrom(clazz);
    }

    private boolean isNumber(Class<?> clazz) {
        return Number.class.isAssignableFrom(clazz);
    }

    private boolean isBoolean(Class<?> clazz) {
        return Boolean.class.isAssignableFrom(clazz);
    }

    private final static String NUMBER = "java.lang.Number";
    private final static String STRING = "java.lang.String";
    private final static String INT = "java.lang.Integer";
    private final static String DOUBLE = "java.lang.Double";
    private final static String FLOAT = "java.lang.Float";
    private final static String LONG = "java.lang.Long";
    private final static String BOOLEAN = "java.lang.Boolean";

}
