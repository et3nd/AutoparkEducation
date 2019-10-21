package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.DriverService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DriverControllerTest {
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private DriverController driverController = new DriverController();
    private Driver driver = new Driver();

    @Mock
    private DriverService driverService;

    @BeforeEach
    void set() {
        driverController.setDriverService(driverService);
        driver.setLicense(1);
    }

    @Test
    void getDriver() throws JsonProcessingException {
        doReturn(new ObjectMapper().setDateFormat(df).writeValueAsString(driver)).when(driverService).getDriver(driver.getLicense());
        assertEquals(new ObjectMapper().setDateFormat(df).writeValueAsString(driver), driverController.getDriver(driver.getLicense()));
        verify(driverService).getDriver(driver.getLicense());
    }

    @Test
    void removeDriver() {
        doReturn("Success").when(driverService).removeDriver(driver.getLicense());
        driverController.removeDriver(driver.getLicense());
        verify(driverService).removeDriver(driver.getLicense());
    }

    @Test
    void addDriver() throws JsonProcessingException {
        doReturn("Success").when(driverService).addDriver(new ObjectMapper().setDateFormat(df).writeValueAsString(driver));
        driverController.addDriver(new ObjectMapper().setDateFormat(df).writeValueAsString(driver));
        verify(driverService).addDriver(new ObjectMapper().setDateFormat(df).writeValueAsString(driver));
    }

    @Test
    void updateDriver() throws JsonProcessingException {
        doReturn("Success").when(driverService).updateDriver(new ObjectMapper().setDateFormat(df).writeValueAsString(driver));
        driverController.updateDriver(new ObjectMapper().setDateFormat(df).writeValueAsString(driver));
        verify(driverService).updateDriver(new ObjectMapper().setDateFormat(df).writeValueAsString(driver));
    }
}