package cn.zsaiedu.backend.boot.vo;

import java.net.Inet4Address;
import java.net.InetAddress;

public class IpUtil {

    public static String getLocalIp(){

        try {
            InetAddress ip = Inet4Address.getLocalHost();
            return ip.getHostAddress();
        } catch (Exception ex) {

        }
        return "127.0.0.1";
    }

}
