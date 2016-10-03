package com.viaphone.sdk.utils;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.concurrent.atomic.AtomicLong;

public class Utils {

    private static AtomicLong ref = new AtomicLong(0);

    public static long nextRef() {
        return ref.incrementAndGet();
    }

    public static String toString(InputStream is) {
        try {
            return IOUtils.toString(is, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
