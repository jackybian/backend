package cn.zsaiedu.backend.boot.service.impl;

import cn.zsaiedu.backend.boot.entity.User;
import cn.zsaiedu.backend.boot.mapper.UserMapper;
import cn.zsaiedu.backend.boot.service.UserService;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public long save(User user) throws Exception{
        int result = userMapper.save(user);
        if (result > 0 ) {
            return user.getId();
        }
        return 0L;
    }

    @Override
    public List<User> queryUserByConditions(String idCard, String phone, Page page) {
        List<User> users =  userMapper.queryUserByConditions(idCard, phone);
        return users;
    }

    @Override
    public int deleteUserById(Long id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public int updateUserById(User user) {
        return userMapper.updateUserById(user);
    }

    @Override
    public User queryUserById(Long id) {
        return userMapper.queryUserById(id);
    }

    @Override
    public User queryUserByPhone(String phone) {
        return userMapper.queryUserByPhone(phone);
    }

    @Override
    public List<User> queryUserByIds(List<Long> ids) {
        return userMapper.queryUserByIds(ids);
    }

    @Override
    public int updateUserByIds(List<Long> ids) {
        return userMapper.updateUserByIds(ids);
    }

}
