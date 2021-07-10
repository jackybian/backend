package cn.zsaiedu.backend.boot.bo;

import lombok.Data;

@Data
public class ExamLocation {

    private String phone;

    private String applyProfession;

    private String province;

    private String city;

    private String area;

    private String expectExamBeginTime;

    private String expectExamEndTime;
}
