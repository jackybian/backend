package cn.zsaiedu.backend.boot.vo;

import cn.zsaiedu.backend.boot.entity.User;
import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

@Data
public class UserQueryPageVo extends BasicVo{

    public UserQueryPageVo(Page page, List<User> userList){
        super();
        this.pageResult = new PageResult(page);
        this.userList = userList;

    }

    private PageResult pageResult;

    private List<User> userList;
}
