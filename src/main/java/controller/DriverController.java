package controller;

import dao.DriverDao;
import service.DriverService;

import javax.ws.rs.*;

@Path("/driver")
public class DriverController {
    private DriverService driverService = new DriverService();
    private DriverDao driverDao = new DriverDao();

    void setDriverService(DriverService driverService) {
        this.driverService = driverService;
    }

    @Path("get/{license}")
    @GET
    @Produces("application/json")
    public String getDriver(@PathParam("license") int license) {
        driverService.setDriverDao(driverDao);
        return driverService.getDriver(license);
    }

    @Path("remove/{license}")
    @DELETE
    @Produces("application/text")
    public String removeDriver(@PathParam("license") int license) {
        driverService.setDriverDao(driverDao);
        return driverService.removeDriver(license);
    }

    @Path("add")
    @POST
    @Produces("application/text")
    public String addDriver(String input) {
        driverService.setDriverDao(driverDao);
        return driverService.addDriver(input);
    }

    @Path("update")
    @PUT
    @Produces("application/text")
    public String updateDriver(String input) {
        driverService.setDriverDao(driverDao);
        return driverService.updateDriver(input);
    }
}
