package nl.craftsmen.microprofile.helloworld;

import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/secure")
@RequestScoped
public class SecureResource {

    @Inject
    private JsonWebToken jwToken;

    @GET
    @RolesAllowed({"user", "admin"})
    public Response greet() {
        return Response.ok(jwToken.getSubject()).build();
    }
}
