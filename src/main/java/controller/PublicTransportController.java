package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.PublicTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.PublicTransportService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;

@Path("/transport")
public class PublicTransportController {
    private PublicTransportService transportService = new PublicTransportService();
    private static final Logger log = LoggerFactory.getLogger(PublicTransportController.class);

    void setTransportService(PublicTransportService transportService) {
        this.transportService = transportService;
    }

    @Path("/add")
    @POST
    @Produces("application/json")
    public Response addPublicTransport(String input) {
        try {
            transportService.addPublicTransport(new ObjectMapper().readValue(input, PublicTransport.class));
            return Response.status(Response.Status.OK).entity("{ \"message\": \"Success\" }").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{ \"message\": \"Unique index violation or zero identifier\" }").build();
        } catch (IOException e) {
            log.error("Error: ", e);
            return Response.status(Response.Status.BAD_REQUEST).entity("{ \"message\": \"Syntax error\" }").build();
        }
    }

    @Path("/update")
    @PUT
    @Produces("application/json")
    public Response updatePublicTransport(String input) {
        try {
            transportService.updatePublicTransport(new ObjectMapper().readValue(input, PublicTransport.class));
            return Response.status(Response.Status.OK).entity("{ \"message\": \"Success\" }").build();
        } catch (SQLException e) {
            if (e.getMessage().equals("Same values"))
                return Response.status(Response.Status.BAD_REQUEST).entity("{ \"message\": \"" + e.getMessage() + "\" }").build();
            else
                return Response.status(Response.Status.NOT_FOUND).entity("{ \"message\": \"" + e.getMessage() + "\" }").build();
        } catch (IOException e) {
            log.error("Error: ", e);
            return Response.status(Response.Status.BAD_REQUEST).entity("{ \"message\": \"Syntax error\" }").build();
        }
    }

    @Path("/{transportNumber}")
    @DELETE
    @Produces("application/json")
    public Response removePublicTransport(@PathParam("transportNumber") int transportNumber) {
        try {
            transportService.removePublicTransport(transportNumber);
            return Response.status(Response.Status.OK).entity("{ \"message\": \"Success\" }").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{ \"message\": \"" + e.getMessage() + "\" }").build();
        }
    }

    @Path("/{transportNumber}")
    @GET
    @Produces("application/json")
    public Response getPublicTransport(@PathParam("transportNumber") int transportNumber) {
        try {
            String jsonTransport = new ObjectMapper().writeValueAsString(transportService.getPublicTransport(transportNumber));
            return Response.status(Response.Status.OK).entity(jsonTransport).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{ \"message\": \"" + e.getMessage() + "\" }").build();
        } catch (JsonProcessingException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{ \"message\": \"Syntax error\" }").build();
        }
    }
}
