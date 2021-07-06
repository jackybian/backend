package cn.zsaiedu.backend.boot.entity;

import lombok.Data;

@Data
public class User {

    private Long id;

    private String name;

    private Integer sex;

    private String idCard;

    private String idCardImg;

    private String phone;

    private Integer age;

    private Integer standardCulture;

    private String province;

    private String city;

    private String area;

    private String address;

    private Integer studentType;

    private String applyProfession;

}
