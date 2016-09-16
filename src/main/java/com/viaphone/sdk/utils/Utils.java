package com.viaphone.sdk.utils;

import com.google.gson.*;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

import static com.viaphone.sdk.utils.Constants.SIMPLE_DT_FOMRATE;

public class Utils {

    private static AtomicLong ref = new AtomicLong(0);
    private static Gson gson = new GsonBuilder()
            .setDateFormat(SIMPLE_DT_FOMRATE)
            .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
            .registerTypeAdapter(LocalTime.class, new LocalTimeSerializer())
            .create();

    public static long nextRef() {
        return ref.incrementAndGet();
    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static Object fromJson(String bodyString, Class classe) {
        return gson.fromJson(bodyString, classe);
    }

    public static File generateQr(String code) {
        File temp = null;
        try {
            temp = File.createTempFile(code, ".jpg");
            temp.deleteOnExit();
            ByteArrayOutputStream out = QRCode.from(code).to(ImageType.JPG).stream();
            out.writeTo(new FileOutputStream(temp));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    private static class LocalDateSerializer implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

        private static final String PATTERN = "yyyy-MM-dd";
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

        @Override
        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(formatter));
        }

        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDate.parse(json.getAsString(), formatter);
        }
    }

    private static class LocalTimeSerializer implements JsonSerializer<LocalTime>, JsonDeserializer<LocalTime> {

        private static final String PATTERN = "HH:mm:ss";
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

        @Override
        public JsonElement serialize(LocalTime time, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(time.format(formatter));
        }

        @Override
        public LocalTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalTime.parse(json.getAsString(), formatter);
        }
    }
}
