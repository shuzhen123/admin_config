package com.mzfk.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author
 * @ClassName  Swagger2
 * @Description
 * @date
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

	@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mzfk.controller"))
                .paths(PathSelectors.any())
//                .paths(Predicates.or(
//                        //这里添加你需要展示的接口
//                        PathSelectors.ant("/rest/**"),
//                        PathSelectors.ant("/admin/**")
//                        )
//                )
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("8xx-Config API")//标题
                .description("8xx-Config Restfull API Doc")//描述
                //listen
                //.termsOfServiceUrl("http://8xx-config")//服务地址
                .version("1.0")
                .build();
    }
}
