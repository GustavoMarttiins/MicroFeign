package com.br.Boleto.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

@OpenAPIDefinition(info =
@Info(title = "API Boleto",
        version = "v1",
        description = "Documentação Service API"))

public class SwaggerConfig {

    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("API Boleto")
                        .version("v1"));
    }
}
