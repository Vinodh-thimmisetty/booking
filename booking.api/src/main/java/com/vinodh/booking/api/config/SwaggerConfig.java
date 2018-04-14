package com.vinodh.booking.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket bookingApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(basePackage("com.vinodh.booking.api"))
                //.paths(regex("/booking.*"))
                .paths(any())
                .build()
                .apiInfo(metaData());
    }

    //@Bean
    public SecurityConfiguration securityConfig() {
        return SecurityConfigurationBuilder
                .builder()
                .appName("Hotel Booking")
                .clientId("admin")
                .clientSecret("admin")
                .scopeSeparator(" ")
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .build();
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Hotel Booking API")
                .description("Spring boot + Angular + Mongo DB based web application")
                .version("0.0.1")
                .license("MIT Licence")
                .licenseUrl("https://opensource.org/licenses/MIT")
                .contact(new Contact("Vinodh Kumar Thimmisetty",
                        "https://github.com/Vinodh-thimmisetty/booking",
                        "vinodh5052@gmail.com"))
                .build();
    }


}
