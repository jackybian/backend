package cn.zsaiedu.backend.boot.bo;

import lombok.Data;

@Data
public class UserQueryBo extends QueryPage{

    private String idCard;

    private String phone;

}
