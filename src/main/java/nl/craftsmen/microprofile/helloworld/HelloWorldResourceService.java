package nl.craftsmen.microprofile.helloworld;

import javax.ws.rs.GET;

public interface HelloWorldResourceService {
    @GET
    String greet();
}
