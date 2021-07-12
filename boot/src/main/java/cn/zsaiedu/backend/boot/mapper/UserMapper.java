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
            "values (#{name},#{sex},#{idcard},#{phone},#{idcardImg}," +
            "#{age},#{standardCulture},#{province},#{city},#{area}," +
            "#{address},#{studentType},#{applyProfession})")
    int save(User user);


    @Select({"<script>" +
            "select `id`,`name`,`sex`,`id_card`,`phone`,`id_card_img`," +
            "`age`,`standard_culture`,`province`,`city`,`area`," +
            "`address`,`student_type`,`apply_profession`, `sync_flag` from user " +
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

    @Update("<script>" +
            "update user " +
            "<set> " +
            "<if test = \"name != null and name != ''\">  `name` = #{name},</if>" +
            "<if test = \"sex != null\">  `sex` = #{sex},</if>" +
            "<if test = \"idCard != null and idCard != ''\">  `id_card` = #{idCard},</if>" +
            "<if test = \"phone != null and phone != ''\">  `phone` = #{phone},</if>" +
            "<if test = \"idCardImg != null and idCardImg != ''\">  `id_card_img` = #{idCardImg},</if>" +
            "<if test = \"age != null\">  `age` = #{age},</if>" +
            "<if test = \"standardCulture != null\">  `standard_culture` = #{standardCulture},</if>" +
            "<if test = \"province != null and province != ''\">  `province` = #{province},</if>" +
            "<if test = \"city != null and city != ''\">  `city` = #{city},</if>" +
            "<if test = \"area != null and area != ''\">  `area` = #{area},</if>" +
            "<if test = \"address != null and address != ''\">  `address` = #{address},</if>" +
            "<if test = \"studentType != null\">  `student_type` = #{studentType},</if>" +
            "<if test = \"applyProfession != null and applyProfession != ''\">  `apply_profession` = #{applyProfession},</if>" +
            "</set>" +
            " where id=#{id}" +
            "</script>"
    )
    int updateUserById(User user);

    @Select(
            "select `name`,`sex`,`id_card`,`phone`,`id_card_img`," +
            "`age`,`standard_culture`,`province`,`city`,`area`," +
            "`address`,`student_type`,`apply_profession`,`sync_flag` from user " +
            "where id=#{id} "
            )
    User queryUserById(@Param("id") Long id);

    @Select(
            "select `name`,`sex`,`id_card`,`phone`,`id_card_img`," +
                    "`age`,`standard_culture`,`province`,`city`,`area`," +
                    "`address`,`student_type`,`apply_profession`,`sync_flag` from user " +
                    "where phone=#{phone} "
    )
    User queryUserByPhone(@Param("phone") String phone);

    @Select({"<script>" +
            "select `id`,`name`,`sex`,`id_card`,`phone`,`id_card_img`," +
                    "`age`,`standard_culture`,`province`,`city`,`area`," +
                    "`address`,`student_type`,`apply_profession`,`sync_flag` from user " +
                    "where id in "+
                    "<foreach item=\"item\" index=\"index\" collection=\"ids\" open=\"(\" separator=\",\" close=\")\">\n" +
                    "#{item}" +
                    "</foreach>"+
            "</script>"}
    )
    List<User> queryUserByIds(@Param("ids") List<Long> ids);


    @Update("<script>" +
            "update user " +
            "<set> " +
            "sync_flag = true" +
            "</set>" +
            "where id in "+
            "<foreach item=\"item\" index=\"index\" collection=\"ids\" open=\"(\" separator=\",\" close=\")\">\n" +
            "#{item}" +
            "</foreach>"+
            "</script>"
    )
    int updateUserByIds(@Param("ids") List<Long> ids);

}
