package nl.craftsmen.microprofile.helloworld;

import org.eclipse.microprofile.rest.client.RestClientBuilder;

import java.net.MalformedURLException;
import java.net.URL;

public class CustomerRestClient {

    private static final String CUSTOMER_URI = "http://localhost:8080/api/customers";
    private CustomerService customerService;

    public CustomerRestClient() throws MalformedURLException {
        customerService = RestClientBuilder.newBuilder()
                .baseUrl(new URL(CUSTOMER_URI))
                .build(CustomerService.class);
    }

    public Customer getCustomer(String customerId) {
        return customerService.getCustomer(customerId);
    }
}
