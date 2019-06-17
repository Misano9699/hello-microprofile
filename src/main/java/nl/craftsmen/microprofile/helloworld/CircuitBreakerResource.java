package nl.craftsmen.microprofile.helloworld;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.temporal.ChronoUnit;

// import io.opentracing.Tracer;

@ApplicationScoped
@Path("circuit")
@CircuitBreaker(delay = 10, delayUnit = ChronoUnit.SECONDS,
        requestVolumeThreshold = 3, failureRatio = 1.0)
@Timeout(value = 3, unit = ChronoUnit.SECONDS)
@Tag(name = "Circuitbreaker example service",
        description = "Get a greeting from the backend service or a fallback handler when the backend service is slow or not available")
public class CircuitBreakerResource {

    @Inject
    @ConfigProperty(name = "helloWorldUrl", defaultValue = "http://localhost:8080/hello-microprofile/hello")
    private String helloWorldUrl;

    static int counter = 0;

    @GET
    @Fallback(HelloWorldFallbackHandler.class)
    @Operation(description = "Get a greeting from a backend service or fallback handler")
    @APIResponse(responseCode = "200", description = "Successful, returning the greeting")
    @Produces(MediaType.TEXT_PLAIN)
    public Response greet() {
        String greeting = callToBackend();
        return Response.ok(greeting).build();
    }

    private String callToBackend() {
        // every three call, this service will fail deliberately
        counter++;
        if (counter % 3 == 0) {
            throw new RuntimeException("Something went wrong!");
        }

        try {
            return RestClientBuilder.newBuilder()
                    .baseUri(new URI(helloWorldUrl))
                    .build(HelloWorldResourceService.class).greet();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Something went wrong!", e);
        }
    }
}
