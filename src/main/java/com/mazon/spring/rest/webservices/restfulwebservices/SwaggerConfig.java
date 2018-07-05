package com.mazon.spring.rest.webservices.restfulwebservices;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	public static final Contact DEFAULT_CONTACT = new Contact("Moacir Mazon", "www.mazon.com", "mazon@mazon.com");
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Mazon Api Documentation", "Mazon  Api Documentation description", "1.0", "urn:tos",
	          DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = 
			Stream.of("application/json", "application/xml").collect(Collectors.toCollection(HashSet::new));
	
	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES).consumes(DEFAULT_PRODUCES_AND_CONSUMES);
	}
	//Bean - docket
	//Swagger 2
	//All paths
	//All apis
}
