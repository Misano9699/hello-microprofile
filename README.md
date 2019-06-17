# hello-microprofile
Some short Eclipse MicroProfile examples

## Running the application

Using maven you can create a war file for deployment on a MicroProfile compliant application server 
```
mvn clean install
```
And run it, for example on Payara Micro using the command 
```
java -jar payara-micro-5.184.jar --deploy hello-microprofile.war --postbootcommandfile ./postboot.txt
```
The --postbootcommandfile option enables the OpenTracing feature on the Payara Micro server.

This application runs on http://localhost:8080 and contains the following endpoints:
* /hello-microprofile/hello - the hello world greeting service
* /hello-microprofile/circuit - the circuitbreaker example
* /hello-microprofile/secure - the JWT example (does not work without an identity provider)


* /health - the health check endpoint
* /metrics - the metrics of the application
* /openapi - the openapi documentation
* /hello-microprofile/openapi-ui - the swagger-ui interface

The mvn install command also creates a MicroProfile Überjar with a Payara Micro server inside.

To run this use the command run
```
java -jar hello-microprofile-microbundle.jar  --postbootcommandfile ./postboot.txt
```