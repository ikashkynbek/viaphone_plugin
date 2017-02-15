package com.viaphone.sdk.utils.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.viaphone.sdk.utils.Utils;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class GsonHelper {

    public static String toJson(Object object) {
        GsonBuilder gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateSerializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .registerTypeAdapter(LocalTime.class, new LocalTimeSerializer())
                .registerTypeAdapter(java.sql.Date.class, new SqlDateSerializer())
                .registerTypeAdapter(java.sql.Time.class, new SqlTimeSerializer());
        return gson.create().toJson(object);
    }

    public static boolean isJson(String value) {
        try {
            new Gson().fromJson(value, Object.class);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Object fromJson(InputStream inputStream, Class classe) {
        return fromJson(Utils.toString(inputStream), classe);
    }

    public static Object fromJson(String bodyString, Class classe) {
        return gsonBuilder().create().fromJson(bodyString, classe);
    }

    public static <T> T fromJson(InputStream inputStream, TypeToken typeToken) {
        return fromJson(Utils.toString(inputStream), typeToken);
    }

    public static <T> T fromJson(String bodyString, TypeToken typeToken) {
        return gsonBuilder().create().fromJson(bodyString, typeToken.getType());
    }

    private static GsonBuilder gsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateSerializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .registerTypeAdapter(LocalTime.class, new LocalTimeSerializer());
    }
}
