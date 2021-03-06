package cn.zsaiedu.backend.boot.util;

import cn.zsaiedu.backend.boot.bo.ExamLocation;
import cn.zsaiedu.backend.boot.bo.ExamLocationDataBo;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUtil {

    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String,Object>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            if (null == value) {
                continue;
            }
            map.put(fieldName, value);
        }
        return map;
    }

    public static void main(String[] args) {
//        CourseProgressBo courseProgressBo = new CourseProgressBo();
//        List<CourseProgress> courseProgressList = new ArrayList<>();
//        CourseProgress courseProgress1 = new CourseProgress();
//        courseProgress1.setCourseNo("001");
//        courseProgress1.setCourseName("基础课程001");
//        courseProgress1.setProgress("100");
//        courseProgress1.setCourseStarTime("2021-05-10");
//        courseProgress1.setCourseEndTime("2021-06-10");
//        CourseProgress courseProgress2 = new CourseProgress();
//        courseProgress2.setCourseNo("002");
//        courseProgress2.setCourseName("基础课程002");
//        courseProgress2.setProgress("100");
//        courseProgress2.setCourseStarTime("2021-05-10");
//        courseProgress2.setCourseEndTime("2021-06-10");
//        courseProgressList.add(courseProgress1);
//        courseProgressList.add(courseProgress2);
//        courseProgressBo.setCourseInfo(courseProgressList);
//        courseProgressBo.setPhone("13656226805");
//        courseProgressBo.setApplyProfession("0005");
//
//        System.out.println(JSONObject.toJSONString(courseProgressBo));

        ExamLocationDataBo examLocationDataBo = new ExamLocationDataBo();
        ExamLocation examLocation = new ExamLocation();
        examLocation.setPhone("13656226805");
        examLocation.setApplyProfession("0005");
        examLocation.setProvince("江苏");
        examLocation.setCity("苏州");
        examLocation.setArea("园区");
        examLocation.setExpectExamBeginTime("2021-06-10");
        examLocation.setExpectExamEndTime("2021-06-10");
        List<ExamLocation> examLocationList = new ArrayList<>();
        examLocationList.add(examLocation);
        examLocationDataBo.setExamLocationList(examLocationList);
        System.out.println(JSONObject.toJSONString(examLocationDataBo));
    }

}
