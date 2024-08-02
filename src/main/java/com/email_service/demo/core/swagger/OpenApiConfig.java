package com.email_service.demo.core.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class OpenApiConfig {

    @Value("${API_EMAIL_PORT}")
    private String port;
    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Email de Ordens de Serviço")
                        .version("v1")
                        .description("Documento para auxiliar no manuseio da API"))
                .components(new Components()
                        .addSchemas("ProblemDetail", new Schema()
                                .type("object")
                                .addProperty("type", new StringSchema().example("https://api/ordem_servico/errors/entity-not-found"))
                                .addProperty("title", new StringSchema().example("Email not found"))
                                .addProperty("status", new StringSchema().example(404))
                                .addProperty("detail", new StringSchema().example("Erro ao encaminhar o email."))
                                .addProperty("timestamp", new StringSchema().example("2024-08-16sT19:32:54.253417400Z"))
                        ));
    }

    @PostConstruct
    public void swaggerLog(){
        log.info("Acesse a documentação no caminho http://localhost:"+ port + "/swagger-ui/index.html");
    }
}
