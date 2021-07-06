package cn.zsaiedu.backend.boot.service;

import cn.zsaiedu.backend.boot.bo.*;
import cn.zsaiedu.backend.boot.entity.User;
import cn.zsaiedu.backend.boot.vo.*;
import com.github.pagehelper.Page;

import java.util.List;

public interface ManagerService {

    TokenVo getToken();

    AddressVo getAddress(String phone, String applyProfession, String userToken);

    ExamLocationVo getLocation(List<ExamLocation> examLocationList, String userToken);

    ExamCostVo getCost(List<Cost> costList, String applyProfession, String userToken);

    BasicVo syncProgress(List<CourseProgress> courseInfo, String phone, String applyProfession, String userToken );

    BasicVo syncUser(SyncBo syncBo);

    UserInfoVo saveUser(UserInfoBo userInfoBo);

    List<User> queryUserByConditions(String idCard, String phone, Page page);
}
