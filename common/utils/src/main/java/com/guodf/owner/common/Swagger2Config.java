package com.guodf.owner.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Contact;
/**
 * 统一工具包配置
 */
@EnableSwagger2
@Configuration
@Slf4j
public class Swagger2Config {

    @Bean
    public Docket utilApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(utilApiInfo())
                .groupName("统一工具分组")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.guodf.owner.web"))
                .paths(PathSelectors.regex(".*/util/.*"))
                .build();
    }

    private ApiInfo utilApiInfo() {
        return new ApiInfoBuilder()
                .title("通用工具类")
                .description("通用工具类")
                .termsOfServiceUrl("https://github.com/BeliieveForMe")
                .contact(new Contact("guodf", "https://github.com/BeliieveForMe", "731998663@qq.com"))
                .version("1.0")
                .build();
    }

    @Bean
    public Docket clearnApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("V1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.guodf.owner.web"))
                .paths(PathSelectors.regex(".*/V1/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("版本swagger分组： V1")
                .description("统一接口文档管理")
                .termsOfServiceUrl("https://github.com/BeliieveForMe")
                .contact(new Contact("guodf", "https://github.com/BeliieveForMe", "731998663@qq.com"))
                .version("1.0")
                .build();
    }

//    @Bean
//    public Docket clearn_N_Api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(api_N_Info())
//                .groupName("分支版本规范")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.sitech.mios.web"))
//                .paths(PathSelectors.regex("/re/v2.*"))
//                .build();
//    }
//
//    private ApiInfo api_N_Info() {
//        return new ApiInfoBuilder()
//                .title("数据服务---接口文档")
//                .description("线上触点平台 | 数据服务平台")
//                .termsOfServiceUrl("https://github.com/BeliieveForMe")
//                .contact(new Contact("guodf", "https://github.com/BeliieveForMe", "731998663@qq.com"))
//                .version("1.0")
//                .build();
//    }


}