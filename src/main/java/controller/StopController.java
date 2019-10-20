package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.StopDao;
import entity.Stop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.StopService;

import javax.ws.rs.*;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

@Path("/stop")
public class StopController {
    private static final Logger log = LoggerFactory.getLogger(StopController.class);
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
        Stop stop = stopService.getStop(stopName);
        try {
            if (stop != null) return new ObjectMapper().setDateFormat(df).writeValueAsString(stop);
            else return "Not found";
        } catch (JsonProcessingException e) {
            log.error("Error: ", e);
            return "Failure";
        }
    }

    @Path("remove/{stopName}")
    @DELETE
    @Produces("application/text")
    public String removeStop(@PathParam("stopName") String stopName) {
        stopService.setStopDao(stopDao);
        stopService.removeStop(stopName);
        if (stopService.getStop(stopName) == null) return "Success";
        else return "Failure";
    }

    @Path("add")
    @POST
    @Produces("application/text")
    public String addStop(String input) {
        stopService.setStopDao(stopDao);
        try {
            Stop stop = new ObjectMapper().setDateFormat(df).readValue(input, Stop.class);
            if (stopService.getStop(stop.getStopName()) != null || stop.getStopName().equals("default"))
                return "Failure";
            stopService.addStop(stop);
            if (stopService.getStop(stop.getStopName()).equals(stop)) return "Success";
            else return "Failure";
        } catch (IOException e) {
            log.error("Error: ", e);
            return "Invalid input form";
        }
    }

    @Path("update")
    @PUT
    @Produces("application/text")
    public String updateStop(String input) {
        stopService.setStopDao(stopDao);
        try {
            Stop inputStop = new ObjectMapper().setDateFormat(df).readValue(input, Stop.class);
            Stop outputStop = stopService.getStop(inputStop.getStopName());
            if (outputStop == null) return "Failure";
            if (!(inputStop.getDirection().equals("default"))) outputStop.setDirection(inputStop.getDirection());
            if (!(inputStop.getArrivalTimeOnStop().equals(Time.valueOf(LocalTime.of(0, 0, 0)))))
                outputStop.setArrivalTimeOnStop(inputStop.getArrivalTimeOnStop());
            stopService.updateStop(outputStop);
            if (stopService.getStop(outputStop.getStopName()).equals(outputStop)) return "Success";
            else return "Failure";
        } catch (IOException e) {
            log.error("Error: ", e);
            return "Invalid input form";
        }
    }
}
