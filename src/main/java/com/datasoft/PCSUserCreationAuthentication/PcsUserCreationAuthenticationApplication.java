package com.datasoft.PCSUserCreationAuthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.function.Predicate;

@SpringBootApplication
public class PcsUserCreationAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcsUserCreationAuthenticationApplication.class, args);
	}

	//Following method is for stop showing the "basic-error-controller" in swagger ui
//	@Bean
//	public Docket swagger() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.select()
//				.apis(RequestHandlerSelectors.basePackage(this.getClass().getPackageName()))
//				.paths(PathSelectors.any())
//				.build()
//				.useDefaultResponseMessages(false);
//	}
}
