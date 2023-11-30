package org.tongji.programming;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author cinea
 */
@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(info = @Info(
        title = "高程论坛",
        description = "高程论坛系统API文档。<br/><b>部分接口有授权限制</b>，不要动歪脑筋哦。<br/>本文档仅限登录用户访问。",
        version = "v1",
        summary = "高程论坛API文档"
))
@MapperScan("org.tongji.programming.mapper")
public class ForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
    }

}
