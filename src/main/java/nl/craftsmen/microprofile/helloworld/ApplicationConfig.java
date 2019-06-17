package nl.craftsmen.microprofile.helloworld;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(title = "Hello MicroProfile application",
        version = "1.0.0",
        contact = @Contact(
                name = "Gerry Noij",
                email = "gerry.noij@craftsmen.nl",
                url = "https://www.craftsmen.nl")
        ),
        servers = {
                @Server(url = "/hello-microprofile", description = "localhost")
        }
)
@LoginConfig(authMethod = "MP-JWT")
@DeclareRoles({"user", "admin"})
@ApplicationPath("/")
@ApplicationScoped
public class ApplicationConfig extends Application {
}
