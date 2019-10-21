package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DriverDao;
import entity.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DriverService {
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger log = LoggerFactory.getLogger(DriverService.class);
    private DriverDao driverDao;

    public void setDriverDao(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    public String addDriver(String input) {
        try {
            Driver driver = new ObjectMapper().setDateFormat(df).readValue(input, Driver.class);
            driverDao.addDriver(driver);
            return "Success";
        } catch (SQLException e) {
            return e.getMessage();
        } catch (IOException e) {
            log.error("Error: ", e);
            return e.getMessage();
        }
    }

    public String updateDriver(String input) {
        try {
            Driver inputDriver = new ObjectMapper().setDateFormat(df).readValue(input, Driver.class);
            Driver outputDriver = driverDao.getDriver(inputDriver.getLicense());
            if (inputDriver.equals(outputDriver)) throw new SQLException("Same values");
            if (!(inputDriver.getFio().equals("default"))) outputDriver.setFio(inputDriver.getFio());
            if (!(inputDriver.getSalary() == 0)) outputDriver.setSalary(inputDriver.getSalary());
            if (!(inputDriver.getAddress().equals("default"))) outputDriver.setAddress(inputDriver.getAddress());
            if (!(inputDriver.getBirthDate().equals(Date.valueOf(LocalDate.of(1900, 1, 1)))))
                outputDriver.setBirthDate(inputDriver.getBirthDate());
            driverDao.updateDriver(outputDriver);
            return "Success";
        } catch (SQLException e) {
            return e.getMessage();
        } catch (IOException e) {
            log.error("Error: ", e);
            return e.getMessage();
        }
    }

    public String removeDriver(int license) {
        try {
            driverDao.getDriver(license);
            driverDao.removeDriver(license);
            return "Success";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String getDriver(int license) {
        try {
            Driver driver = driverDao.getDriver(license);
            return new ObjectMapper().setDateFormat(df).writeValueAsString(driver);
        } catch (SQLException e) {
            return e.getMessage();
        } catch (JsonProcessingException e) {
            log.error("Error: ", e);
            return e.getMessage();
        }
    }
}
