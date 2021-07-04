package cn.zsaiedu.backend.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author jackybian
 */
@EnableSwagger2
@SpringBootApplication
@MapperScan("cn.zsaiedu.backend.boot.mapper")
public class BackendStarter {

    public static void main (String[] args) {
        SpringApplication.run(BackendStarter.class, args);
    }

}
