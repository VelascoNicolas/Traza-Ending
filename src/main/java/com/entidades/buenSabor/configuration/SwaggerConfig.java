package com.entidades.buenSabor.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Buen Sabor Grupo Panic at the Kernel")
                        .version("V.2.7.5")
                        .description("Proyecto final <b>\"El Buen Sabor\"</b> E-Commerce de empresas gastronomicas. </br> API documentada mediante Swagger UI" +
                                "<h3> Integrantes: </h3>" +
                                "<ul> <li> Velasco Nicolas (Back-End) </li> <li> Ianchina Tomas (Back-End) </li> <li> Manino Martin Jenaro (Back-End/Front-End) </li> <li> Silva Franco (Front-End)</li>  <li> Ribotta Martin (Front-End)</li> </ul>")
                        .contact(new Contact()
                                .name("Grupo: Panic at the Kernel"))
                        .contact(new Contact()
                                .name("Github:")
                                .url("https://github.com/VelascoNicolas/Traza-Final")
                        )
                );
    }
}
