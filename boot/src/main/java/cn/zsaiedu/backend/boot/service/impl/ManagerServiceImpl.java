package cn.zsaiedu.backend.boot.service.impl;

import cn.zsaiedu.backend.boot.bo.Cost;
import cn.zsaiedu.backend.boot.bo.CourseProgress;
import cn.zsaiedu.backend.boot.bo.ExamLocation;
import cn.zsaiedu.backend.boot.bo.UserInfo;
import cn.zsaiedu.backend.boot.constants.Constants;
import cn.zsaiedu.backend.boot.service.ManagerService;
import cn.zsaiedu.backend.boot.util.HttpUtil;
import cn.zsaiedu.backend.boot.vo.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.zsaiedu.backend.boot.constants.Constants.APP_ID;
import static cn.zsaiedu.backend.boot.constants.Constants.APP_SECRET;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Override
    public TokenVo getToken() {
        Map<String, Object> params = ParamUtil.getRequestParam(new HashMap<String, Object>(), APP_ID, APP_SECRET);
        String result = HttpUtil.sendPost(Constants.QGJY_SERVER_URL_TOKEN , JSON.toJSONString(params));

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
        String result = HttpUtil.sendPost(Constants.QGJY_SERVER_URL_SYNC_EXAM_ADDRESS , JSON.toJSONString(params));
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
        String result = HttpUtil.sendPost(Constants.QGJY_SERVER_URL_SYNC_ALLOT_EXAM , JSON.toJSONString(params));
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
        String result = HttpUtil.sendPost(Constants.QGJY_SERVER_URL_SYNC_COST , JSON.toJSONString(params));
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
        String result = HttpUtil.sendPost(Constants.QGJY_SERVER_URL_SYNC_PROGRESS , JSON.toJSONString(params));
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
    public BasicVo syncUser(List<UserInfo> userInfos, String userToken) {
        Map<String, Object> params = ParamUtil.getRequestParam(userInfos, userToken);
        String result = HttpUtil.sendPost(Constants.QGJY_SERVER_URL_SYNC_USER , JSON.toJSONString(params));
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


}
