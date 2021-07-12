package cn.zsaiedu.backend.boot.bo;

import lombok.Data;

import java.util.List;

import static cn.zsaiedu.backend.boot.constants.Constants.APPLY_PROFESSION;

@Data
public class CostQueryDataBo {

    private String phone;

    private String applyProfession = APPLY_PROFESSION;

    public String getApplyProfession() {
        return APPLY_PROFESSION;
    }

    public void setApplyProfession(String applyProfession) {
        this.applyProfession = APPLY_PROFESSION;
    }
}
