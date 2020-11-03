package com.yimeng.seed.organ.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
@Profile({"development", "test"})
public class SwaggerConfig {

    @Value("${spring.profiles.active}")
    private String[] profiles;

    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yimeng.seed.organ.controller"))
                .paths(PathSelectors.any())
                .build();
        //根据环境设置不同host
        if (Arrays.asList(profiles).contains("test")) {
            docket.host("test.sifei.info/gateway");
        }
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口在线接口文档")
                .description("价商超店铺管理在线接口文档")
                .contact(new Contact("WuJiaxin", "sifei.info", "wjx@sifei.info"))
                .version("1.0")
                .build();
    }

}
