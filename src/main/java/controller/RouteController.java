package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.RouteDao;
import entity.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.RouteService;

import javax.ws.rs.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Path("/route")
public class RouteController {
    private static final Logger log = LoggerFactory.getLogger(RouteController.class);
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private RouteService routeService = new RouteService();
    private RouteDao routeDao = new RouteDao();

    void setRouteService(RouteService routeService) {
        this.routeService = routeService;
    }

    @Path("get/{routeNumber}")
    @GET
    @Produces("application/json")
    public String getRoute(@PathParam("routeNumber") int routeNumber) {
        routeService.setRouteDao(routeDao);
        Route route = routeService.getRoute(routeNumber);
        try {
            if (route != null) return new ObjectMapper().setDateFormat(df).writeValueAsString(route);
            else return "Not found";
        } catch (JsonProcessingException e) {
            log.error("Error: ", e);
            return "Failure";
        }
    }

    @Path("remove/{routeNumber}")
    @DELETE
    @Produces("application/text")
    public String removeRoute(@PathParam("routeNumber") int routeNumber) {
        routeService.setRouteDao(routeDao);
        routeService.removeRoute(routeNumber);
        if (routeService.getRoute(routeNumber) == null) return "Success";
        else return "Failure";
    }

    @Path("add")
    @POST
    @Produces("application/text")
    public String addRoute(String input) {
        routeService.setRouteDao(routeDao);
        try {
            Route route = new ObjectMapper().setDateFormat(df).readValue(input, Route.class);
            if (routeService.getRoute(route.getRouteNumber()) != null || route.getRouteNumber() == 0) return "Failure";
            routeService.addRoute(route);
            if (routeService.getRoute(route.getRouteNumber()).equals(route)) return "Success";
            else return "Failure";
        } catch (IOException e) {
            log.error("Error: ", e);
            return "Invalid input form";
        }
    }

    @Path("update")
    @PUT
    @Produces("application/text")
    public String updateRoute(String input) {
        routeService.setRouteDao(routeDao);
        try {
            Route inputRoute = new ObjectMapper().setDateFormat(df).readValue(input, Route.class);
            Route outputRoute = routeService.getRoute(inputRoute.getRouteNumber());
            if (outputRoute == null) return "Failure";
            if (!(inputRoute.getStartStation().equals("default")))
                outputRoute.setStartStation(inputRoute.getStartStation());
            if (!(inputRoute.getEndStation().equals("default"))) outputRoute.setEndStation(inputRoute.getEndStation());
            if (!(inputRoute.getStops().equals("default"))) outputRoute.setStops(inputRoute.getStops());
            if (!(inputRoute.getDistance() == 0)) outputRoute.setDistance(inputRoute.getDistance());
            routeService.updateRoute(outputRoute);
            if (routeService.getRoute(outputRoute.getRouteNumber()).equals(outputRoute)) return "Success";
            else return "Failure";
        } catch (IOException e) {
            log.error("Error: ", e);
            return "Invalid input form";
        }
    }
}
