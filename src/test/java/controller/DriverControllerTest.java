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
import static org.mockito.Mockito.*;

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
        doReturn(driver).when(driverService).getDriver(driver.getLicense());
        assertEquals(new ObjectMapper().setDateFormat(df).writeValueAsString(driver), driverController.getDriver(driver.getLicense()));
        verify(driverService).getDriver(driver.getLicense());
    }

    @Test
    void removeDriver() {
        doNothing().when(driverService).removeDriver(driver.getLicense());
        driverController.removeDriver(driver.getLicense());
        verify(driverService).removeDriver(driver.getLicense());
    }

    @Test
    void addDriver() throws JsonProcessingException {
        doNothing().when(driverService).addDriver(driver);
        doReturn(null, driver).when(driverService).getDriver(driver.getLicense());
        driverController.addDriver(new ObjectMapper().setDateFormat(df).writeValueAsString(driver));
        verify(driverService).addDriver(driver);
    }

    @Test
    void addDriverWithException() {
        driverController.addDriver("Input");
    }

    @Test
    void updateDriver() throws JsonProcessingException {
        doNothing().when(driverService).updateDriver(driver);
        doReturn(driver).when(driverService).getDriver(driver.getLicense());
        driverController.updateDriver(new ObjectMapper().setDateFormat(df).writeValueAsString(driver));
        verify(driverService).updateDriver(driver);
    }

    @Test
    void updateDriverWithException() {
        driverController.updateDriver("Input");
    }
}