package com.example.blogapi.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;


@Configuration
@OpenAPIDefinition
@SecurityScheme(name = "JWT",paramName = "Authorization", type = SecuritySchemeType.APIKEY,in = SecuritySchemeIn.HEADER)
@SecurityRequirement(name = "JWT")
public class SwaggerConfig {
	
	//public static final String AUTHORIZATION_HEADER = "Authorization";
	

	
	@Bean
	public Contact contact() {
		return new Contact()
				.name("MYBLOG")
				.email("myblog@gmail.com")
				.url("https://www.myblog.com");
	}

	public Contact getContact() {
		return contact();
	}

	@Bean
	public License mitLicense() {
		return new License()
				.name("MIT License")
				.url("https://choosealicense.com/licenses/mit/");
	}

	@Bean
	public Server getLocalServer() {
		Server localServer = new Server();
		localServer.setUrl("http://localhost:8085/myblog");
		localServer.setDescription("Server URL in Local environment");
		return localServer;
	}

	@Bean
	public Server getProdServer() {
		Server prodServer = new Server();
		prodServer.setUrl("http://google.com/");
		prodServer.setDescription("Server URL in Production environment");
		return prodServer;
	}

	@Bean
	public Info getInfo() {
		Info info = new Info()
				.title("MyBlog Spring Doc")
				.contact(getContact())
				.version("1.0.0")
				.description("MyBlog Open Api")
				.termsOfService("https://www.google.com")
				.license(mitLicense());
		return info;
	}

	@Bean
	public OpenAPI baseOpenAPI() {
		return new OpenAPI()
				.info(getInfo())
				.addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("JWT"))
				.servers(List.of(getLocalServer(), getProdServer()));

	}
	
	
}
