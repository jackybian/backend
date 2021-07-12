package cn.zsaiedu.backend.boot.entity;

import lombok.Data;

import static cn.zsaiedu.backend.boot.constants.Constants.APPLY_PROFESSION;

@Data
public class User {

    private Long id;

    private String name;

    private Integer sex;

    private String idcard;

    private String idcardImg;

    private String phone;

    private Integer age;

    private Integer standardCulture;

    private String province;

    private String city;

    private String area;

    private String address;

    private Integer studentType;

    private String applyProfession = APPLY_PROFESSION;

    private boolean syncFlag;

    public String getApplyProfession() {
        return APPLY_PROFESSION;
    }

    public void setApplyProfession(String applyProfession) {
        this.applyProfession = APPLY_PROFESSION;
    }
}
