package com.panjura.mosque.miyabarimosque;

import java.util.Calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MiyabarimosqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiyabarimosqueApplication.class, args);
	}
	
	@Bean
	public Docket connector() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.regex("/*"))
				.build()
				.pathMapping("/")
				.directModelSubstitute(Calendar.class, String.class);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Panjura miya bari mosque")
				.description("Panjura miya bari mosque controlling Apps")
				.contact(new Contact("Md Akramul Hasan", null, "akramul3@gmail.com"))
				.version("0.0.1")
				.build();
	}
}
