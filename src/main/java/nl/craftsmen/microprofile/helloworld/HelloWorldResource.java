package nl.craftsmen.microprofile.helloworld;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("hello")
@Tag(name = "Hello World greet service", description = "Get a greeting with a config property")
public class HelloWorldResource {

    @Inject
    @ConfigProperty(name = "name", defaultValue = "World")
    private String name;

    @GET
    @Metered(name = "greets", unit = MetricUnits.SECONDS)
    @Traced(value=true, operationName = "HelloWorldResource.greet")
    @Operation(description = "Get a greeting")
    @APIResponse(responseCode = "200", description = "Successful, returning the greeting")
    @Produces(MediaType.TEXT_PLAIN)
    public Response greet() {
        return Response.ok("Hello " + name + "!").build();
    }
}
