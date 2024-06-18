package com.laredo.mls.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "APIs de Pago para MLS",
                version = "v1",
                description = "Esta aplicación provee APIs REST para la aplicación web de pagos de MLS",
                contact = @Contact(
                        name = "Ricardo Laredo",
                        email = "ing.rlaredo@gmail.com"
                )
        ),
        servers = {
                @Server(
                        url = "http://172.16.61.15:8081",
                        description = " Servidor de desarrollo"
                ), @Server(
                url = "http://localhost:8081",
                description = " Servidor de desarrollo"
        )
        }
)
@SecurityScheme(
        name = "bearerToken",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "jwt"
)
public class OpenApiConfig {
}
