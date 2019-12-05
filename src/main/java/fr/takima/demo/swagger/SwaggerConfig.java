package fr.takima.demo.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.ant("/**")).build().apiInfo(apiInfo()).useDefaultResponseMessages(false);

    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Whatch Your Family",
                "API description",
                "API TOS",
                "Terms of service",
                new Contact("EPF Roxane, Mathilde, Anto et Mika", "localhost:8080/", "mathilde.darras@epfedu.fr"), "License of API", "API license URL", Collections.emptyList());
        return apiInfo;
    }
}