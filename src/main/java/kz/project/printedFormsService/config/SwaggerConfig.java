package kz.project.printedFormsService.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Template Service API",
                description = "Loyalty System", version = "1.0.0",
                contact = @Contact(
                        name = "Struchkov Mark",
                        email = "mark@struchkov.dev",
                        url = "https://mark.struchkov.dev"
                )
        )
)

@Configuration
public class SwaggerConfig {

}
