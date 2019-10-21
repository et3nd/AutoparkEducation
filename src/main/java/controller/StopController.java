package controller;

import dao.StopDao;
import service.StopService;

import javax.ws.rs.*;

@Path("/stop")
public class StopController {
    private StopService stopService = new StopService();
    private StopDao stopDao = new StopDao();

    void setStopService(StopService stopService) {
        this.stopService = stopService;
    }

    @Path("get/{stopName}")
    @GET
    @Produces("application/json")
    public String getStop(@PathParam("stopName") String stopName) {
        stopService.setStopDao(stopDao);
        return stopService.getStop(stopName);
    }

    @Path("remove/{stopName}")
    @DELETE
    @Produces("application/text")
    public String removeStop(@PathParam("stopName") String stopName) {
        stopService.setStopDao(stopDao);
        return stopService.removeStop(stopName);
    }

    @Path("add")
    @POST
    @Produces("application/text")
    public String addStop(String input) {
        stopService.setStopDao(stopDao);
        return stopService.addStop(input);
    }

    @Path("update")
    @PUT
    @Produces("application/text")
    public String updateStop(String input) {
        stopService.setStopDao(stopDao);
        return stopService.updateStop(input);
    }
}
