package cn.zsaiedu.backend.boot.vo;

import lombok.Data;

import java.util.Map;

@Data
public class ExamLocationVo extends BasicVo{
    private Map<String, Object> allocateExamLocation;

    public ExamLocationVo(){
        super();
    }
}
