package cn.zsaiedu.backend.boot.bo;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoBo {

    private List<UserInfo> userInfos;

    private String userToken;

}
