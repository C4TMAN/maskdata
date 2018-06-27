package com.c4tman.play.logPrint.desense;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by zhangxiaoman on 2018/6/1.
 */
public class KeyWordTypeAdapter extends TypeAdapter {
    @Override
    public void write(JsonWriter jsonWriter, Object o) throws IOException {

    }

    @Override
    public Object read(JsonReader jsonReader) throws IOException {
        return jsonReader.nextString();
    }
}
