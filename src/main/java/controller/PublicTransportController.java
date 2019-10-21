package controller;

import dao.PublicTransportDao;
import service.PublicTransportService;

import javax.ws.rs.*;

@Path("/transport")
public class PublicTransportController {
    private PublicTransportService transportService = new PublicTransportService();
    private PublicTransportDao transportDao = new PublicTransportDao();

    void setTransportService(PublicTransportService transportService) {
        this.transportService = transportService;
    }

    @Path("get/{transportNumber}")
    @GET
    @Produces("application/json")
    public String getPublicTransport(@PathParam("transportNumber") int transportNumber) {
        transportService.setPublicTransportDao(transportDao);
        return transportService.getPublicTransport(transportNumber);
    }

    @Path("remove/{transportNumber}")
    @DELETE
    @Produces("application/text")
    public String removePublicTransport(@PathParam("transportNumber") int transportNumber) {
        transportService.setPublicTransportDao(transportDao);
        return transportService.removePublicTransport(transportNumber);
    }

    @Path("add")
    @POST
    @Produces("application/text")
    public String addPublicTransport(String input) {
        transportService.setPublicTransportDao(transportDao);
        return transportService.addPublicTransport(input);
    }

    @Path("update")
    @PUT
    @Produces("application/text")
    public String updatePublicTransport(String input) {
        transportService.setPublicTransportDao(transportDao);
        return transportService.updatePublicTransport(input);
    }
}
