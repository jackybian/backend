package cn.zsaiedu.backend.boot.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import static cn.zsaiedu.backend.boot.constants.Constants.HTTP_REQUEST_CONTENT_TYPE;
import static cn.zsaiedu.backend.boot.constants.Constants.HTTP_REQUEST_CONTENT_TYPE_JSON;

public class HttpUtil {

    public static String sendPost(String url, String contentType, String params){
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        String result = null;
        try {
            StringEntity entity = new StringEntity(params);
            httpPost.setEntity(entity);
            httpPost.setHeader(HTTP_REQUEST_CONTENT_TYPE, contentType);
            response = client.execute(httpPost);
            result = EntityUtils.toString(response.getEntity());
        } catch (Exception ex) {

        } finally {
            if (null != client) {
                try {
                    client.close();
                } catch (Exception ex) {

                }
            }
        }
        return result;
    }

    public static String sendPost(String url, String params) {
        return sendPost(url, HTTP_REQUEST_CONTENT_TYPE_JSON, params);
    }

}
