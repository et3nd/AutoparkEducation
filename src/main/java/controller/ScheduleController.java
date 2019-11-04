package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ScheduleService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;

@Path("/schedule")
public class ScheduleController {
    private ScheduleService scheduleService = new ScheduleService();
    private static final Logger log = LoggerFactory.getLogger(ScheduleController.class);

    void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Path("/add")
    @POST
    @Produces("application/json")
    public Response addSchedule(String input) {
        try {
            scheduleService.addSchedule(new ObjectMapper().readValue(input, Schedule.class));
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
    public Response updateSchedule(String input) {
        try {
            scheduleService.updateSchedule(new ObjectMapper().readValue(input, Schedule.class));
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

    @Path("/{id}")
    @DELETE
    @Produces("application/json")
    public Response removeSchedule(@PathParam("id") int id) {
        try {
            scheduleService.removeSchedule(id);
            return Response.status(Response.Status.OK).entity("{ \"message\": \"Success\" }").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{ \"message\": \"" + e.getMessage() + "\" }").build();
        }
    }

    @Path("/{id}")
    @GET
    @Produces("application/json")
    public Response getSchedule(@PathParam("id") int id) {
        try {
            String jsonSchedule = new ObjectMapper().writeValueAsString(scheduleService.getSchedule(id));
            return Response.status(Response.Status.OK).entity(jsonSchedule).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{ \"message\": \"" + e.getMessage() + "\" }").build();
        } catch (JsonProcessingException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{ \"message\": \"Syntax error\" }").build();
        }
    }
}
