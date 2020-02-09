package com.springboot.restservices.rest_services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
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
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

	// For putting all the information on swagger
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).select()
//				.apis(RequestHandlerSelectors.any())
//
//				.paths(PathSelectors.any()).build();
//	}

	// Swagger metadata -> http://localhost:8080/v2/api-docs
	// Swagger UI URL -> http://localhost:8080/swagger-ui.html
	//Swagger online editor -> editor.swagger.io

	// For limiting the scope of swagger doc i.e controlling what information to
	// show on swagger out of all the
	// packages created
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.springboot.restservices.rest_services"))
				.paths(PathSelectors.ant("/users/**")).build();
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("User Management Service")
				.description("This page lists all API's of User Management").version("2.0")
				.contact(new Contact("Devansh", "https://example.com", "dummyId@xyz.com")).license("License 2.0")
				.licenseUrl("http://dummyLicenseUrl/license.html").build();
	}
}
