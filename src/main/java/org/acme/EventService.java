package org.acme;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.acme.interceptor.MyInterceptor;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.rest.client.reactive.ClientExceptionMapper;
import io.smallrye.mutiny.Uni;

@RegisterRestClient(configKey = "api")
@MyInterceptor
public interface EventService {

    @GET
    @Path("/getEvents")
    Uni<List<Void>> getEvents();

    // @NoClassInterceptors
    @ClientExceptionMapper
    static RuntimeException toException(Response response) {
        if (response.getStatus() == Status.NOT_FOUND.getStatusCode()) {
            return new NotFoundException("not found");
        }
        return null;
    }
}
