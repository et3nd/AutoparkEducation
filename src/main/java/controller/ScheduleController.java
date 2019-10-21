package controller;

import dao.ScheduleDao;
import service.ScheduleService;

import javax.ws.rs.*;

@Path("/schedule")
public class ScheduleController {
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
        return scheduleService.getSchedule(id);
    }

    @Path("remove/{id}")
    @DELETE
    @Produces("application/text")
    public String removeSchedule(@PathParam("id") int id) {
        scheduleService.setScheduleDao(scheduleDao);
        return scheduleService.removeSchedule(id);
    }

    @Path("add")
    @POST
    @Produces("application/text")
    public String addSchedule(String input) {
        scheduleService.setScheduleDao(scheduleDao);
        return scheduleService.addSchedule(input);
    }

    @Path("update")
    @PUT
    @Produces("application/text")
    public String updateSchedule(String input) {
        scheduleService.setScheduleDao(scheduleDao);
        return scheduleService.updateSchedule(input);
    }
}
