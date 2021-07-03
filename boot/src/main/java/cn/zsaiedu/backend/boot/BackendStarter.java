package cn.zsaiedu.backend.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author jackybian
 */
@EnableSwagger2
@SpringBootApplication
public class BackendStarter {

    public static void main (String[] args) {
        SpringApplication.run(BackendStarter.class, args);
    }

}
