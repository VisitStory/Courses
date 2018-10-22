package net.tecgurus.courses.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.desc}")
    private String appDescription;

    @Value("${app.maintainer.name}")
    private String appMaintainerName;

    @Value("${app.maintainer.email}")
    private String appMaintainerEmail;

    @Value("${app.maintainer.url}")
    private String appMaintainerUrl;

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/.*"))
                .build()
                .pathMapping("/")
                .apiInfo(metadata());
    }

    private ApiInfo metadata() {

        return new ApiInfoBuilder()
                .title(appName)
                .description(appDescription)
                .version(appVersion)
                .contact(new Contact(
                        appMaintainerName,
                        appMaintainerUrl,
                        appMaintainerEmail
                )).build();
    }

}
