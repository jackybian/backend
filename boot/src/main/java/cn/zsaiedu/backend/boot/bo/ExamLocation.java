package cn.zsaiedu.backend.boot.bo;

import lombok.Data;

import static cn.zsaiedu.backend.boot.constants.Constants.APPLY_PROFESSION;

@Data
public class ExamLocation {

    private String phone;

    private String applyProfession = APPLY_PROFESSION;

    private String province;

    private String city;

    private String area;

    private String expectExamBeginTime;

    private String expectExamEndTime;

    public String getApplyProfession() {
        return APPLY_PROFESSION;
    }

    public void setApplyProfession(String applyProfession) {
        this.applyProfession = APPLY_PROFESSION;
    }
}
