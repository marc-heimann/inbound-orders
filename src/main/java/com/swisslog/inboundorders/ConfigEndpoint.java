package com.swisslog.inboundorders;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@Path("/config")
public class ConfigEndpoint {

    @Inject
    @ConfigProperty(name = "foo", defaultValue = "foobar")
    private String foo;

    @GET
    public Response getProperty() {
        return Response.ok(foo).build();
    }

        
    
}