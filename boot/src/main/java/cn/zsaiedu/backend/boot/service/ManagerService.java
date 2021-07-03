package cn.zsaiedu.backend.boot.service;

import cn.zsaiedu.backend.boot.vo.AddressVo;
import cn.zsaiedu.backend.boot.vo.TokenVo;

public interface ManagerService {

    TokenVo getToken();

    AddressVo getAddress(String phone, String applyProfession, String userToken);

}
