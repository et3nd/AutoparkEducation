package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Stop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.StopService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;

@Path("/stop")
public class StopController {
    private StopService stopService = new StopService();
    private static final Logger log = LoggerFactory.getLogger(StopController.class);

    void setStopService(StopService stopService) {
        this.stopService = stopService;
    }

    @Path("/add")
    @POST
    @Produces("application/json")
    public Response addStop(String input) {
        try {
            stopService.addStop(new ObjectMapper().readValue(input, Stop.class));
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
    public Response updateStop(String input) {
        try {
            stopService.updateStop(new ObjectMapper().readValue(input, Stop.class));
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

    @Path("/{stopName}")
    @DELETE
    @Produces("application/json")
    public Response removeStop(@PathParam("stopName") String stopName) {
        try {
            stopService.removeStop(stopName);
            return Response.status(Response.Status.OK).entity("{ \"message\": \"Success\" }").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{ \"message\": \"" + e.getMessage() + "\" }").build();
        }
    }

    @Path("/{stopName}")
    @GET
    @Produces("application/json")
    public Response getStop(@PathParam("stopName") String stopName) {
        try {
            String jsonStop = new ObjectMapper().writeValueAsString(stopService.getStop(stopName));
            return Response.status(Response.Status.OK).entity(jsonStop).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{ \"message\": \"" + e.getMessage() + "\" }").build();
        } catch (JsonProcessingException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{ \"message\": \"Syntax error\" }").build();
        }
    }
}
