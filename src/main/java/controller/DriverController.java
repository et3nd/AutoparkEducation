package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DriverDao;
import entity.Driver;
import service.DriverService;

import javax.ws.rs.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Path("/driver")
public class DriverController {
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private DriverService driverService = new DriverService();
    private DriverDao driverDao = new DriverDao();

    @Path("get/{license}")
    @GET
    @Produces("application/json")
    public String getDriver(@PathParam("license") int license) throws JsonProcessingException {
        driverService.setDriverDao(driverDao);
        Driver driver = driverService.getDriver(license);
        if (driver != null)
            return new ObjectMapper().setDateFormat(df).writeValueAsString(driver);
        else return "Not found";
    }

    @Path("delete/{license}")
    @DELETE
    @Produces("application/json")
    public String deleteDriver(@PathParam("license") int license) {
        driverService.setDriverDao(driverDao);
        driverService.removeDriver(license);
        if (driverService.getDriver(license) == null)
            return "Success";
        else return "Failure";
    }

    @Path("add/{license}/{fio}/{salary}/{address}/{year}/{month}/{day}")
    @PUT
    @Produces("application/json")
    public String addDriver(@PathParam("license") int license,
                            @PathParam("fio") String fio,
                            @PathParam("salary") int salary,
                            @PathParam("address") String address,
                            @PathParam("year") int year,
                            @PathParam("month") int month,
                            @PathParam("day") int day) {
        driverService.setDriverDao(driverDao);
        if (driverService.getDriver(license) != null || license == 0)
            return "Failure";
        Driver driver = new Driver();
        driver.setLicense(license);
        driver.setFio(fio);
        driver.setSalary(salary);
        driver.setAddress(address);
        driver.setBirthDate(Date.valueOf(LocalDate.of(year, month, day)));
        driverService.addDriver(driver);
        if (driverService.getDriver(license) != null)
            return "Success";
        else return "Failure";
    }
}
