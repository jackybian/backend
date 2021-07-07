package cn.zsaiedu.backend.boot.util;

import sun.misc.BASE64Encoder;

public class Base64Util {

    public static String getBase64Code(byte[] data) {
        if (null == data) {
            return null;
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }



}
