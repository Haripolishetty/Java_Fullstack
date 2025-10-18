package com.mnc.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
@Configuration
public class OpenApiConfig {
   @Bean
   public OpenAPI customOpenAPI() {
       Server server = new Server();
       server.setUrl("http://localhost:8086");
       server.setDescription("Development Server");
       Info info = new Info()
               .title("Employee Management System API")
               .version("1.0.0")
               .description("Complete REST API for Employee Management System")
               .contact(new Contact()
                       .name("MNC Company")
                       .email("support@mnc.com"));
       return new OpenAPI()
               .info(info)
               .servers(List.of(server))
               .components(new Components());
   }
}