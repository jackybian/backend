package cn.zsaiedu.backend.boot.bo;

import lombok.Data;

import java.util.List;

@Data
public class CourseProgressBo {

    private List<CourseProgress> courseInfo;

    private String phone;

    private String applyProfession;
}
