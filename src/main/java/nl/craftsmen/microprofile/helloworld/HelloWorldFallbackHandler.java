package nl.craftsmen.microprofile.helloworld;

import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

import javax.enterprise.context.Dependent;
import javax.ws.rs.core.Response;

@Dependent
public class HelloWorldFallbackHandler implements FallbackHandler<Response> {

    @Override
    public Response handle(ExecutionContext executionContext) {
        return Response.ok("Sorry, no greeting possible!").build();
    }
}
