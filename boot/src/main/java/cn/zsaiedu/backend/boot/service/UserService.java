package cn.zsaiedu.backend.boot.service;

import cn.zsaiedu.backend.boot.entity.User;
import com.github.pagehelper.Page;

import java.util.List;

public interface UserService {


    long save(User user) throws Exception;

    List<User> queryUserByConditions(String idCard, String phone, Page page);

    int deleteUserById(Long id);

    int updateUserById(User user);

    User queryUserById(Long id);

    User queryUserByPhone(String phone);

    List<User> queryUserByIds(List<Long> ids);

    int updateUserByIds(List<Long> ids);
}
