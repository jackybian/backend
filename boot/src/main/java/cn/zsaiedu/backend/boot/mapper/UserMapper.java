package cn.zsaiedu.backend.boot.mapper;


import cn.zsaiedu.backend.boot.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface UserMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into user(`name`,`sex`,`id_card`,`phone`) " +
            "values (#{name},#{sex},#{idCard},#{phone})")
    int save(User user);

}
