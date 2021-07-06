package cn.zsaiedu.backend.boot.service.impl;

import cn.zsaiedu.backend.boot.bo.*;
import cn.zsaiedu.backend.boot.constants.Constants;
import cn.zsaiedu.backend.boot.entity.User;
import cn.zsaiedu.backend.boot.service.ManagerService;
import cn.zsaiedu.backend.boot.service.UserService;
import cn.zsaiedu.backend.boot.util.HttpUtil;
import cn.zsaiedu.backend.boot.util.MapUtil;
import cn.zsaiedu.backend.boot.util.ParamUtil;
import cn.zsaiedu.backend.boot.vo.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.obs.services.ObsClient;
import com.obs.services.model.ObsObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.zsaiedu.backend.boot.constants.Constants.APP_ID;
import static cn.zsaiedu.backend.boot.constants.Constants.APP_SECRET;

@Slf4j
@Service
public class ManagerServiceImpl implements ManagerService {

    @Resource
    private UserService userService;

    @Override
    public TokenVo getToken() {
        Map<String, Object> params = ParamUtil.getRequestParam(new HashMap<String, Object>(), APP_ID, APP_SECRET);
        String result = HttpUtil.sendPost(Constants.QGJY_SERVER_URL_TOKEN, JSON.toJSONString(params));

        TokenVo tokenVo = new TokenVo();
        if (StringUtils.isEmpty(result)) {
            tokenVo.setStatus(500);
            tokenVo.setErrorMessage("服务器异常");
        } else {
            try {

                JSONObject jsonObject = JSONObject.parseObject(result);
                String token = jsonObject.getString("data");
                if (StringUtils.isEmpty(token)) {
                    tokenVo.setStatus(500);
                    tokenVo.setErrorMessage("服务器返回token为空");
                } else {
                    tokenVo.setToken(token);
                }

            } catch (Exception ex) {
                tokenVo.setStatus(500);
                tokenVo.setErrorMessage("服务器返回数据异常");
            }

        }
        return tokenVo;
    }

    @Override
    public AddressVo getAddress(String phone, String applyProfession, String userToken) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("phone", phone);
        requestData.put("applyProfession", applyProfession);
        Map<String, Object> params = ParamUtil.getRequestParam(requestData, userToken);
        String result = HttpUtil.sendPost(Constants.QGJY_SERVER_URL_SYNC_EXAM_ADDRESS, JSON.toJSONString(params));
        AddressVo addressVo = new AddressVo();
        if (StringUtils.isEmpty(result)) {
            addressVo.setStatus(500);
            addressVo.setErrorMessage("服务器异常");
        } else {
            try {

                JSONObject jsonObject = JSONObject.parseObject(result);
                String code = jsonObject.getString("code");
                if (!"200".equals(code)) {
                    addressVo.setStatus(500);
                    addressVo.setErrorMessage(jsonObject.getString("msg"));
                } else {
                    String data = jsonObject.getString("data");
                    if (StringUtils.isEmpty(data)) {
                        addressVo.setStatus(500);
                        addressVo.setErrorMessage("服务器返回数据为空");
                    } else {
                        JSONObject jsonData = JSONObject.parseObject(result);
                        addressVo.setAddr(jsonData.getString("addr"));
                        addressVo.setTime(jsonData.getString("time"));
                    }
                }

            } catch (Exception ex) {
                addressVo.setStatus(500);
                addressVo.setErrorMessage("服务器返回数据异常");
            }

        }
        return addressVo;
    }

    @Override
    public ExamLocationVo getLocation(List<ExamLocation> examLocationList, String userToken) {

        Map<String, Object> params = ParamUtil.getRequestParam(examLocationList, userToken);
        String result = HttpUtil.sendPost(Constants.QGJY_SERVER_URL_SYNC_ALLOT_EXAM, JSON.toJSONString(params));
        ExamLocationVo examLocationVo = new ExamLocationVo();
        if (StringUtils.isEmpty(result)) {
            examLocationVo.setStatus(500);
            examLocationVo.setErrorMessage("服务器异常");
        } else {
            try {
                JSONObject jsonObject = JSONObject.parseObject(result);
                String code = jsonObject.getString("code");
                // 对接文档写的不详细，暂时按照非200，就是全部失败
                if (!"200".equals(code)) {
                    examLocationVo.setStatus(500);
                    examLocationVo.setErrorMessage(jsonObject.getString("msg"));
                } else {
                    String data = jsonObject.getString("data");
                    if (StringUtils.isEmpty(data)) {
                        examLocationVo.setStatus(500);
                        examLocationVo.setErrorMessage("服务器返回数据为空");
                    } else {
                        Map<String, Object> jsonData = JSONObject.parseObject(result, Map.class);
                        examLocationVo.setAllocateExamLocation(jsonData);
                    }
                }

            } catch (Exception ex) {
                examLocationVo.setStatus(500);
                examLocationVo.setErrorMessage("服务器返回数据异常");
            }

        }
        return examLocationVo;
    }

    @Override
    public ExamCostVo getCost(List<Cost> costList, String applyProfession, String userToken) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("applyProfession", applyProfession);
        requestData.put("student", costList);
        Map<String, Object> params = ParamUtil.getRequestParam(requestData, userToken);
        String result = HttpUtil.sendPost(Constants.QGJY_SERVER_URL_SYNC_COST, JSON.toJSONString(params));
        ExamCostVo examCostVo = new ExamCostVo();
        if (StringUtils.isEmpty(result)) {
            examCostVo.setStatus(500);
            examCostVo.setErrorMessage("服务器异常");
        } else {
            try {
                JSONObject jsonObject = JSONObject.parseObject(result);
                String code = jsonObject.getString("code");
                // 对接文档写的不详细，暂时按照非200，就是全部失败
                if (!"200".equals(code)) {
                    examCostVo.setStatus(500);
                    examCostVo.setErrorMessage(jsonObject.getString("msg"));
                } else {
                    String data = jsonObject.getString("data");
                    if (StringUtils.isEmpty(data)) {
                        examCostVo.setStatus(500);
                        examCostVo.setErrorMessage("服务器返回数据为空");
                    } else {
                        Map<String, Object> jsonData = JSONObject.parseObject(result, Map.class);
                        examCostVo.setExamCost(jsonData);
                    }
                }

            } catch (Exception ex) {
                examCostVo.setStatus(500);
                examCostVo.setErrorMessage("服务器返回数据异常");
            }

        }
        return examCostVo;
    }

    @Override
    public BasicVo syncProgress(List<CourseProgress> courseInfo, String phone, String applyProfession, String userToken) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("applyProfession", applyProfession);
        requestData.put("phone", phone);
        requestData.put("courseInfo", courseInfo);
        Map<String, Object> params = ParamUtil.getRequestParam(requestData, userToken);
        String result = HttpUtil.sendPost(Constants.QGJY_SERVER_URL_SYNC_PROGRESS, JSON.toJSONString(params));
        BasicVo basicVo = new BasicVo();
        if (StringUtils.isEmpty(result)) {
            basicVo.setStatus(500);
            basicVo.setErrorMessage("服务器异常");
        } else {
            try {
                JSONObject jsonObject = JSONObject.parseObject(result);
                String code = jsonObject.getString("code");
                // 对接文档写的不详细，暂时按照非200，就是全部失败
                if (!"200".equals(code)) {
                    basicVo.setStatus(500);
                    basicVo.setErrorMessage(jsonObject.getString("msg"));
                } else {
                    String data = jsonObject.getString("data");
                    if (StringUtils.isEmpty(data)) {
                        basicVo.setStatus(500);
                        basicVo.setErrorMessage("服务器返回数据为空");
                    } else {
                        Map<String, Object> jsonData = JSONObject.parseObject(result, Map.class);
//                        basicVo.setExamCost(jsonData);
                    }
                }

            } catch (Exception ex) {
                basicVo.setStatus(500);
                basicVo.setErrorMessage("服务器返回数据异常");
            }

        }
        return basicVo;
    }

    @Override
    public BasicVo syncUser(SyncBo syncBo) {
        BasicVo basicVo = new BasicVo();
        User user = userService.queryUserById(syncBo.getId());
        if (null ==  user) {
            basicVo.setStatus(500);
            basicVo.setErrorMessage("id对应的记录不存在");
            return basicVo;
        }
        Map<String, Object> param = null;
        try {
            param = MapUtil.objectToMap(user);
        } catch (Exception ex) {
            basicVo.setStatus(500);
            basicVo.setErrorMessage("id对应的记录不存在");
            return basicVo;
        }

        Map<String, Object> params = ParamUtil.getRequestParam(param, syncBo.getUserToken());
        String result = HttpUtil.sendPost(Constants.QGJY_SERVER_URL_SYNC_USER, JSON.toJSONString(params));

        if (StringUtils.isEmpty(result)) {
            basicVo.setStatus(500);
            basicVo.setErrorMessage("服务器异常");
        } else {
            try {
                JSONObject jsonObject = JSONObject.parseObject(result);
                String code = jsonObject.getString("code");
                // 对接文档写的不详细，暂时按照非200，就是全部失败
                if (!"200".equals(code)) {
                    basicVo.setStatus(500);
                    basicVo.setErrorMessage(jsonObject.getString("msg"));
                } else {
                    String data = jsonObject.getString("data");
                    if (StringUtils.isEmpty(data)) {
                        basicVo.setStatus(500);
                        basicVo.setErrorMessage("服务器返回数据为空");
                    } else {
                        Map<String, Object> jsonData = JSONObject.parseObject(result, Map.class);
//                        basicVo.setExamCost(jsonData);
                    }
                }

            } catch (Exception ex) {
                basicVo.setStatus(500);
                basicVo.setErrorMessage("服务器返回数据异常");
            }

        }
        return basicVo;
    }

    @Override
    public UserInfoVo saveUser(UserInfoBo userInfoBo) {
        // 目前只保存一条用户记录
        UserInfoVo userInfoVo = new UserInfoVo();
        long result = 0;
        try {
            result = userService.save(userInfoBo);
        } catch (DuplicateKeyException ex) {
            userInfoVo.setStatus(500);
            userInfoVo.setErrorMessage("手机号或者身份证号已经存在");
            return userInfoVo;
        } catch (Exception ex) {
            log.error("save error:", ex);
            userInfoVo.setStatus(500);
            userInfoVo.setErrorMessage("数据库插入错误");
        }

        if (result > 0 && null != userInfoBo.getId()) {
            userInfoVo.setId(userInfoBo.getId());
        }
        return userInfoVo;
    }

    @Override
    public List<User> queryUserByConditions(String idCard, String phone, Page page) {
        return userService.queryUserByConditions(idCard, phone, page);
    }

    public static void main(String[] args) throws IOException {
        // 需要将endPoint/ak/sk更新为实际信息
        String endPoint = "obs.cn-east-3.myhuaweicloud.com";
        String ak = "FQJH6MC0ZVOPP62JELKN";
        String sk = "v5DQAXZgqdrks7vXYIvYXRfB0Cmhmc0BZHPHI1iv";
        String bucketName = "master-edu";                // 需要将bucketName更新为实际信息
        // 创建ObsClient实例
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);
//        ObjectMetadata meta = new ObjectMetadata();
//        try {
//            InputStream is = new BufferedInputStream(
//                    new FileInputStream("D:/midway.jpg"));
//            obsClient.putObject(bucketName, "midway3", is);
//        } catch (Exception ex) {
//            System.out.println("end");
//        }

        ObsObject obsObject = obsClient.getObject(bucketName, "midway3");
        InputStream content = obsObject.getObjectContent();
        File file = new File("D:/midway_copy1.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        StringBuffer stringBuffer = new StringBuffer();
        if (content != null)
        {


            while (true)
            {
                byte[] buf=new byte[1024];
                int len=0;
                while((len=content.read(buf))!=-1){   //将byte数据读到最多buf长度的buf数组中
                    fileOutputStream.write(buf,0,len);         //将buf中 从0-len长度的数据写到文件中
                }
            }
//            reader.close();
        }

//        PrintStream ps = new PrintStream(file);
//        ps.append(stringBuffer.toString());
//        ps.flush();
        obsClient.close();




    }


}
