package someday.fly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("someday.fly.controller")) //以扫描包的方式
                .paths(PathSelectors.regex("/users.*"))
                .build();
    }

    private ApiInfo apiInfo = new ApiInfoBuilder()
            .title("spring boot 项目接口文档")
            .description("spring boot 项目的接口文档，符合RESTful API。")
            .version("1.0-SNAPSHOT")
            .build();
}
