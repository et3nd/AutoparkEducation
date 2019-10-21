package controller;

import dao.RouteDao;
import service.RouteService;

import javax.ws.rs.*;

@Path("/route")
public class RouteController {
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
        return routeService.getRoute(routeNumber);
    }

    @Path("remove/{routeNumber}")
    @DELETE
    @Produces("application/text")
    public String removeRoute(@PathParam("routeNumber") int routeNumber) {
        routeService.setRouteDao(routeDao);
        return routeService.removeRoute(routeNumber);
    }

    @Path("add")
    @POST
    @Produces("application/text")
    public String addRoute(String input) {
        routeService.setRouteDao(routeDao);
        return routeService.addRoute(input);
    }

    @Path("update")
    @PUT
    @Produces("application/text")
    public String updateRoute(String input) {
        routeService.setRouteDao(routeDao);
        return routeService.updateRoute(input);
    }
}
