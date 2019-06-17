package nl.craftsmen.microprofile.helloworld;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Health
public class HelloWorldHealthResource implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("successful-check")
                .up()
                .withData("status", "I am alive!")
                .build();
    }
}
