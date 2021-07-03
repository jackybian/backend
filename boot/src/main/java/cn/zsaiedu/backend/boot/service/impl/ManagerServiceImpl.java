package cn.zsaiedu.backend.boot.service.impl;

import cn.zsaiedu.backend.boot.constants.Constants;
import cn.zsaiedu.backend.boot.service.ManagerService;
import cn.zsaiedu.backend.boot.util.HttpUtil;
import cn.zsaiedu.backend.boot.vo.ParamUtil;
import cn.zsaiedu.backend.boot.vo.TokenVo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
}
