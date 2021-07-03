package cn.zsaiedu.backend.boot.vo;

import cn.zsaiedu.backend.boot.util.SignUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class ParamUtil {

    public static Map<String, Object> getRequestParam(Map<String, Object> origParam,
                                                      String appId, String appSecret) {
        String timeStamp = Long.valueOf(System.currentTimeMillis() / 1000).toString();

        TreeMap<String, Object> param = new TreeMap<>();
        param.put("appId", appId);
        param.put("timeStamp", timeStamp);
        param.put("appSecret", appSecret);
        String sign = SignUtil.createSign(param);
        param.put("sign", sign);

        Map<String, Object> params = new HashMap<>();
        params.put("data", param);
        Map<String, Object> headers = new HashMap<>();
        headers.put("ip", IpUtil.getLocalIp());
        headers.put("timestamp", Long.valueOf(timeStamp).toString());
        params.put("headers", headers);

        return params;
    }

}
