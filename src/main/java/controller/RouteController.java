package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.RouteService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;

@Path("/route")
public class RouteController {
    private RouteService routeService = new RouteService();
    private static final Logger log = LoggerFactory.getLogger(RouteController.class);

    void setRouteService(RouteService routeService) {
        this.routeService = routeService;
    }

    @Path("/add")
    @POST
    @Produces("application/json")
    public Response addRoute(String input) {
        try {
            routeService.addRoute(new ObjectMapper().readValue(input, Route.class));
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
    public Response updateRoute(String input) {
        try {
            routeService.updateRoute(new ObjectMapper().readValue(input, Route.class));
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

    @Path("/{routeNumber}")
    @DELETE
    @Produces("application/json")
    public Response removeRoute(@PathParam("routeNumber") int routeNumber) {
        try {
            routeService.removeRoute(routeNumber);
            return Response.status(Response.Status.OK).entity("{ \"message\": \"Success\" }").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{ \"message\": \"" + e.getMessage() + "\" }").build();
        }
    }

    @Path("/{routeNumber}")
    @GET
    @Produces("application/json")
    public Response getRoute(@PathParam("routeNumber") int routeNumber) {
        try {
            String jsonRoute = new ObjectMapper().writeValueAsString(routeService.getRoute(routeNumber));
            return Response.status(Response.Status.OK).entity(jsonRoute).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{ \"message\": \"" + e.getMessage() + "\" }").build();
        } catch (JsonProcessingException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{ \"message\": \"Syntax error\" }").build();
        }
    }
}
