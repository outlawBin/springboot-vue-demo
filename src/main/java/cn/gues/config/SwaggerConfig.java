package cn.gues.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                // 分组
                .groupName("dev")
                // 是否开启swagger
                .enable(true).select()
                // 扫描指定路径下的文件
                .apis(RequestHandlerSelectors.basePackage("cn.gues.controller"))
                // 不过滤指定下的任何路径
                .paths(PathSelectors.any())
                .build().pathMapping("/");
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .contact(new Contact("Outlaw","",""))
                .title("springboot-vue-demo")
                .version("1.0.0")
                .description("springboot-vue-demo")
                .build();
    }
}
