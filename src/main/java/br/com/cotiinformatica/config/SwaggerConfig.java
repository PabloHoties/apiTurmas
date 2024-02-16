package br.com.cotiinformatica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {

		return new OpenAPI().components(new Components())
				.info(new Info().title("API para controle de turmas")
						.description("API Spring Boot para gerenciamento de turmas, alunos, professores e matrículas")
						.version("1.0"));
	}
}
