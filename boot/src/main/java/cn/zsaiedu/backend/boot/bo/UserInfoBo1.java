package cn.zsaiedu.backend.boot.bo;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoBo1 {

    private List<UserInfoBo> userInfos;

    private String userToken;

}
