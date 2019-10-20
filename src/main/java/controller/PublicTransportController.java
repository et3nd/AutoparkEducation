package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.PublicTransportDao;
import entity.PublicTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.PublicTransportService;

import javax.ws.rs.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Path("/transport")
public class PublicTransportController {
    private static final Logger log = LoggerFactory.getLogger(PublicTransportController.class);
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
        PublicTransport publicTransport = transportService.getPublicTransport(transportNumber);
        try {
            if (publicTransport != null)
                return new ObjectMapper().setDateFormat(df).writeValueAsString(publicTransport);
            else return "Not found";
        } catch (JsonProcessingException e) {
            log.error("Error: ", e);
            return "Failure";
        }
    }

    @Path("remove/{transportNumber}")
    @DELETE
    @Produces("application/text")
    public String removePublicTransport(@PathParam("transportNumber") int transportNumber) {
        transportService.setPublicTransportDao(transportDao);
        transportService.removePublicTransport(transportNumber);
        if (transportService.getPublicTransport(transportNumber) == null) return "Success";
        else return "Failure";
    }

    @Path("add")
    @POST
    @Produces("application/text")
    public String addPublicTransport(String input) {
        transportService.setPublicTransportDao(transportDao);
        try {
            PublicTransport transport = new ObjectMapper().setDateFormat(df).readValue(input, PublicTransport.class);
            if (transportService.getPublicTransport(transport.getTransportNumber()) != null || transport.getTransportNumber() == 0)
                return "Failure";
            transportService.addPublicTransport(transport);
            if (transportService.getPublicTransport(transport.getTransportNumber()).equals(transport)) return "Success";
            else return "Failure";
        } catch (IOException e) {
            log.error("Error: ", e);
            return "Invalid input form";
        }
    }

    @Path("update")
    @PUT
    @Produces("application/text")
    public String updatePublicTransport(String input) {
        transportService.setPublicTransportDao(transportDao);
        try {
            PublicTransport inputTransport = new ObjectMapper().setDateFormat(df).readValue(input, PublicTransport.class);
            PublicTransport outputTransport = transportService.getPublicTransport(inputTransport.getTransportNumber());
            if (outputTransport == null) return "Failure";
            if (!(inputTransport.getBusBrand().equals("default")))
                outputTransport.setBusBrand(inputTransport.getBusBrand());
            if (!(inputTransport.getCapacity() == 0)) outputTransport.setCapacity(inputTransport.getCapacity());
            if (!(inputTransport.getIssueYear() == 0)) outputTransport.setIssueYear(inputTransport.getIssueYear());
            transportService.updatePublicTransport(outputTransport);
            if (transportService.getPublicTransport(outputTransport.getTransportNumber()).equals(outputTransport))
                return "Success";
            else return "Failure";
        } catch (IOException e) {
            log.error("Error: ", e);
            return "Invalid input form";
        }
    }
}
