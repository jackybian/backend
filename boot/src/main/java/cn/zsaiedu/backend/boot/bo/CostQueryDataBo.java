package cn.zsaiedu.backend.boot.bo;

import lombok.Data;

import java.util.List;
@Data
public class CostQueryDataBo {

    private List<Cost> costBoList;

    private String applyProfession;

    private String userToken;

}
