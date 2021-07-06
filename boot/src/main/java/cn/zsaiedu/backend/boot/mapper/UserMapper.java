package cn.zsaiedu.backend.boot.mapper;


import cn.zsaiedu.backend.boot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into user(`name`,`sex`,`id_card`,`phone`,`id_card_img`," +
            "`age`,`standard_culture`,`province`,`city`,`area`," +
            "`address`,`student_type`,`apply_profession`) " +
            "values (#{name},#{sex},#{idCard},#{phone},#{idCardImg}," +
            "#{age},#{standardCulture},#{province},#{city},#{area}," +
            "#{address},#{studentType},#{applyProfession})")
    int save(User user);


    @Select({"<script>" +
            "select `name`,`sex`,`id_card`,`phone`,`id_card_img`," +
            "`age`,`standard_culture`,`province`,`city`,`area`," +
            "`address`,`student_type`,`apply_profession` from user " +
            "where 1 = 1 "  +
            " <when test='idCard!=null'> " +
            " AND id_card = #{idCard} " +
            " </when> " +
            " <when test='phone!=null'> "+
            " AND phone = #{phone} "+
            " </when> "+
            " order by id desc  "+
            "</script>"})
    List<User> queryUserByConditions(@Param("idCard") String idCard, @Param("phone")String phone);


    @Delete("delete from user where id = #{id}")
    int deleteUserById(@Param("id") Long id);
}
