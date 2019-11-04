package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.DriverService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Path("/driver")
public class DriverController {
    private DriverService driverService = new DriverService();
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger log = LoggerFactory.getLogger(DriverController.class);

    void setDriverService(DriverService driverService) {
        this.driverService = driverService;
    }

    @Path("/add")
    @POST
    @Produces("application/json")
    public Response addDriver(String input) {
        try {
            driverService.addDriver(new ObjectMapper().setDateFormat(DATE_FORMAT).readValue(input, Driver.class));
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
    public Response updateDriver(String input) {
        try {
            driverService.updateDriver(new ObjectMapper().setDateFormat(DATE_FORMAT).readValue(input, Driver.class));
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

    @Path("/{license}")
    @DELETE
    @Produces("application/json")
    public Response removeDriver(@PathParam("license") int license) {
        try {
            driverService.removeDriver(license);
            return Response.status(Response.Status.OK).entity("{ \"message\": \"Success\" }").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{ \"message\": \"" + e.getMessage() + "\" }").build();
        }
    }

    @Path("/{license}")
    @GET
    @Produces("application/json")
    public Response getDriver(@PathParam("license") int license) {
        try {
            String jsonDriver = new ObjectMapper().setDateFormat(DATE_FORMAT).writeValueAsString(driverService.getDriver(license));
            return Response.status(Response.Status.OK).entity(jsonDriver).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{ \"message\": \"" + e.getMessage() + "\" }").build();
        } catch (JsonProcessingException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{ \"message\": \"Syntax error\" }").build();
        }
    }
}
