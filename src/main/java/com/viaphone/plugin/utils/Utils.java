package com.viaphone.plugin.utils;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utils {

    private static long ref = 1;

    public static synchronized long nextRef() {
        return ref++;
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
