package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ScheduleDao;
import entity.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ScheduleService;

import javax.ws.rs.*;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

@Path("/schedule")
public class ScheduleController {
    private static final Logger log = LoggerFactory.getLogger(ScheduleController.class);
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private ScheduleService scheduleService = new ScheduleService();
    private ScheduleDao scheduleDao = new ScheduleDao();

    void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Path("get/{id}")
    @GET
    @Produces("application/json")
    public String getSchedule(@PathParam("id") int id) {
        scheduleService.setScheduleDao(scheduleDao);
        Schedule schedule = scheduleService.getSchedule(id);
        try {
            if (schedule != null) return new ObjectMapper().setDateFormat(df).writeValueAsString(schedule);
            else return "Not found";
        } catch (JsonProcessingException e) {
            log.error("Error: ", e);
            return "Failure";
        }
    }

    @Path("remove/{id}")
    @DELETE
    @Produces("application/text")
    public String removeSchedule(@PathParam("id") int id) {
        scheduleService.setScheduleDao(scheduleDao);
        scheduleService.removeSchedule(id);
        if (scheduleService.getSchedule(id) == null) return "Success";
        else return "Failure";
    }

    @Path("add")
    @POST
    @Produces("application/text")
    public String addSchedule(String input) {
        scheduleService.setScheduleDao(scheduleDao);
        try {
            Schedule schedule = new ObjectMapper().setDateFormat(df).readValue(input, Schedule.class);
            if (scheduleService.getSchedule(schedule.getId()) != null || schedule.getId() == 0) return "Failure";
            scheduleService.addSchedule(schedule);
            if (scheduleService.getSchedule(schedule.getId()).equals(schedule)) return "Success";
            else return "Failure";
        } catch (IOException e) {
            log.error("Error: ", e);
            return "Invalid input form";
        }
    }

    @Path("update")
    @PUT
    @Produces("application/text")
    public String updateSchedule(String input) {
        scheduleService.setScheduleDao(scheduleDao);
        try {
            Schedule inputSchedule = new ObjectMapper().setDateFormat(df).readValue(input, Schedule.class);
            Schedule outputSchedule = scheduleService.getSchedule(inputSchedule.getId());
            if (outputSchedule == null) return "Failure";
            if (!(inputSchedule.getArrivalTime().equals(Time.valueOf(LocalTime.of(0, 1, 1)))))
                outputSchedule.setArrivalTime(inputSchedule.getArrivalTime());
            if (!(inputSchedule.getDepartureTime().equals(Time.valueOf(LocalTime.of(0, 1, 1)))))
                outputSchedule.setDepartureTime(inputSchedule.getDepartureTime());
            scheduleService.updateSchedule(outputSchedule);
            if (scheduleService.getSchedule(outputSchedule.getId()).equals(outputSchedule)) return "Success";
            else return "Failure";
        } catch (IOException e) {
            log.error("Error: ", e);
            return "Invalid input form";
        }
    }
}
