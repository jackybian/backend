package cn.zsaiedu.backend.boot.util;

import cn.zsaiedu.backend.boot.constants.Constants;
import com.alibaba.fastjson.JSONObject;
import com.obs.services.ObsClient;
import com.obs.services.model.ObsObject;
import com.obs.services.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

@Slf4j
public class HuaweiUtil {

    private static ObsClient obsClient;

    static {
        obsClient = new ObsClient(Constants.HUAWEI_OBS_AK, Constants.HUAWEI_OBS_SK, Constants.HUAWEI_OBS_ENDPOINT);
    }

    public static boolean upload2Obs(String originFileName, String obsFileName) {
        PutObjectResult result = null;
        try {
            InputStream is = new BufferedInputStream(
                    new FileInputStream(originFileName));
            result = obsClient.putObject(Constants.HUAWEI_OBS_BUCKET, obsFileName, is);
            if (null != result) {
                log.info(JSONObject.toJSONString(result));
                if (200 == result.getStatusCode()) {
                    return true;
                }
            }
        } catch (Exception ex) {
            log.error("upload file fail, originFileName={}", originFileName);
        }
        return false;
    }

    public static boolean upload2Obs(MultipartFile file, String obsFileName) {
        PutObjectResult result = null;
        try {
            result = obsClient.putObject(Constants.HUAWEI_OBS_BUCKET, obsFileName, file.getInputStream());
            if (null != result) {
                log.info(JSONObject.toJSONString(result));
                if (200 == result.getStatusCode()) {
                    return true;
                }
            }
        } catch (Exception ex) {
            log.error("upload file fail, originFileName={}", file.getName());
        }
        return false;
    }


    public static byte[] downloadObs(String obsFileName) {

        ObsObject obsObject = obsClient.getObject(Constants.HUAWEI_OBS_BUCKET, obsFileName);

        InputStream content = obsObject.getObjectContent();

        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = content.read(buf)) != -1) {   //将byte数据读到最多buf长度的buf数组中
                data.write(buf, 0, len);
            }
        } catch (Exception ex) {
            return null;
        }
        return data.toByteArray();
    }

}
