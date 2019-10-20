package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DriverDao;
import entity.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.DriverService;

import javax.ws.rs.*;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Path("/driver")
public class DriverController {
    private static final Logger log = LoggerFactory.getLogger(DriverController.class);
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
        Driver driver = driverService.getDriver(license);
        try {
            if (driver != null) return new ObjectMapper().setDateFormat(df).writeValueAsString(driver);
            else return "Not found";
        } catch (JsonProcessingException e) {
            log.error("Error: ", e);
            return "Failure";
        }
    }

    @Path("remove/{license}")
    @DELETE
    @Produces("application/text")
    public String removeDriver(@PathParam("license") int license) {
        driverService.setDriverDao(driverDao);
        driverService.removeDriver(license);
        if (driverService.getDriver(license) == null) return "Success";
        else return "Failure";
    }

    @Path("add")
    @POST
    @Produces("application/text")
    public String addDriver(String input) {
        driverService.setDriverDao(driverDao);
        try {
            Driver driver = new ObjectMapper().setDateFormat(df).readValue(input, Driver.class);
            if (driverService.getDriver(driver.getLicense()) != null || driver.getLicense() == 0) return "Failure";
            driverService.addDriver(driver);
            if (driverService.getDriver(driver.getLicense()).equals(driver)) return "Success";
            else return "Failure";
        } catch (IOException e) {
            log.error("Error: ", e);
            return "Invalid input form";
        }
    }

    @Path("update")
    @PUT
    @Produces("application/text")
    public String updateDriver(String input) {
        driverService.setDriverDao(driverDao);
        try {
            Driver inputDriver = new ObjectMapper().setDateFormat(df).readValue(input, Driver.class);
            Driver outputDriver = driverService.getDriver(inputDriver.getLicense());
            if (outputDriver == null) return "Failure";
            if (!(inputDriver.getFio().equals("default"))) outputDriver.setFio(inputDriver.getFio());
            if (!(inputDriver.getSalary() == 0)) outputDriver.setSalary(inputDriver.getSalary());
            if (!(inputDriver.getAddress().equals("default"))) outputDriver.setAddress(inputDriver.getAddress());
            if (!(inputDriver.getBirthDate().equals(Date.valueOf(LocalDate.of(1900, 1, 1)))))
                outputDriver.setBirthDate(inputDriver.getBirthDate());
            driverService.updateDriver(outputDriver);
            if (driverService.getDriver(outputDriver.getLicense()).equals(outputDriver)) return "Success";
            else return "Failure";
        } catch (IOException e) {
            log.error("Error :", e);
            return "Invalid input form";
        }
    }
}
