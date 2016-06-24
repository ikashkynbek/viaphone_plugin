package com.viaphone.sdk.utils;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

public class Utils {

    private static AtomicLong ref = new AtomicLong(0);

    public static long nextRef() {
        return ref.incrementAndGet();
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
