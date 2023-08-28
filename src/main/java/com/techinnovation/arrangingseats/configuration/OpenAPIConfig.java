package com.techinnovation.arrangingseats.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

  @Value("${bezkoder.openapi.dev-url}")
  private String devUrl;

  @Bean
  public OpenAPI myOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("Server URL in Development environment");

    License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

    SecurityScheme securityScheme = new SecurityScheme()
      .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
      .in(SecurityScheme.In.HEADER).name("Authorization");
    SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

    Info info = new Info()
      .title("Arrangingseats Service API")
      .version("1.0")
      .description("자리배치 API 서비스")
      .license(mitLicense);

    return new OpenAPI()
      .info(info)
      .servers(List.of(devServer))
      .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
      .security(Arrays.asList(securityRequirement));
  }

}
