package cn.zsaiedu.backend.boot.bo;

import cn.zsaiedu.backend.boot.constants.Constants;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel("分页查询条件")
@Data
public class QueryPage implements Serializable {
    private Long pageNum = 1L;

    private Long pageSize = 10L;

    private Boolean needSort;

    private String sortField = "update_time";

    private String sortOrder = Constants.SORT_DESCEND;
}
