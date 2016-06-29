package com.viaphone.sdk.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import static com.viaphone.sdk.utils.Constants.SIMPLE_DT_FOMRATE;

public class Utils {

    private static AtomicLong ref = new AtomicLong(0);
    private static Gson gson = new GsonBuilder().setDateFormat(SIMPLE_DT_FOMRATE).create();

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
}
