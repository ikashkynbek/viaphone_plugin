package com.viaphone.plugin.utils;

public class Utils {

    private static long ref = 1;

    public static synchronized long nextRef() {
        return ref++;
    }
}
