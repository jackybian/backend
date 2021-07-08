package cn.zsaiedu.backend.boot.util;

import cn.zsaiedu.backend.boot.constants.Constants;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.util.*;

import static cn.zsaiedu.backend.boot.constants.Constants.APP_ID;
import static cn.zsaiedu.backend.boot.constants.Constants.APP_SECRET;

/**
 * @author jackybian
 */
@Slf4j
public class SignUtil {



    /**
     * 微信支付签名算法sign
     *
     * @param parameters
     * @return
     */
    public static String createSign(SortedMap<String, Object> parameters, String keyName) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !keyName.equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }

        sb.append(keyName).append("=").append(parameters.get(keyName)); //这里是商户那里设置的key);
        log.info("params=> {}", sb.toString());
        String sign = md5Password(sb.toString()).toUpperCase();
        return sign;
    }

    /**
     * 生成32位md5码
     *
     * @param key
     * @return
     */
    public static String md5Password(String key) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
//        Map<String, Object> params = ParamUtil.getRequestParam(new HashMap<String, Object>(), APP_ID, APP_SECRET);
//        String result = HttpUtil.sendPost(Constants.QGJY_SERVER_URL_TOKEN , JSON.toJSONString(params));
//        System.out.println(result);
        Map<String, Object> map = new HashMap<>();
        map.put("a1", "123");
        map.put("a2", "567");
        map.put("c3","ddd");
        map.put("c4","bomb");
        map.put("d5", null);
        map.put("e6","");
        map.put("token", "123333");
        SortedMap<String, Object> parameters = new TreeMap<>();
        parameters.putAll(map);
        System.out.println(createSign(parameters, "token"));
    }

}
