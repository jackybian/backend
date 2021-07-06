package cn.zsaiedu.backend.boot.mapper;


import cn.zsaiedu.backend.boot.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface UserMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into user(`name`,`sex`,`id_card`,`phone`,`id_card_img`," +
            "`age`,`standard_culture`,`province`,`city`,`area`" +
            "`address`,`student_type`,`apply_profession`) " +
            "value (#{name},#{sex},#{idCard},#{phone},#{idCardImg}," +
            "#{age},#{standardCulture},#{province},#{city},#{area}," +
            "#{address},#{studentType},#{applyProfession})")
    int save(User user);

}
