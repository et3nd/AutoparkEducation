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

import java.sql.SQLException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverControllerTest {
    private DriverController driverController = new DriverController();
    private Driver driver = new Driver();

    @Mock
    private DriverService driverService;

    @BeforeEach
    void setDriverService() {
        driverController.setDriverService(driverService);
        driver.setLicense(1);
    }

    @Test
    void getDriver() throws SQLException {
        doReturn(driver).when(driverService).getDriver(driver.getLicense());
        driverController.getDriver(driver.getLicense());
        verify(driverService).getDriver(driver.getLicense());
    }

    @Test
    void getDriverWithException() throws SQLException {
        doThrow(SQLException.class).when(driverService).getDriver(driver.getLicense());
        driverController.getDriver(driver.getLicense());
        verify(driverService).getDriver(driver.getLicense());
    }

    @Test
    void removeDriver() throws SQLException {
        doNothing().when(driverService).removeDriver(driver.getLicense());
        driverController.removeDriver(driver.getLicense());
        verify(driverService).removeDriver(driver.getLicense());
    }

    @Test
    void removeDriverWithException() throws SQLException {
        doThrow(SQLException.class).when(driverService).removeDriver(driver.getLicense());
        driverController.removeDriver(driver.getLicense());
        verify(driverService).removeDriver(driver.getLicense());
    }

    @Test
    void addDriver() throws SQLException, JsonProcessingException {
        doNothing().when(driverService).addDriver(driver);
        driverController.addDriver(new ObjectMapper().writeValueAsString(driver));
        verify(driverService).addDriver(driver);
    }

    @Test
    void addDriverWithException() throws SQLException, JsonProcessingException {
        doThrow(SQLException.class).when(driverService).addDriver(driver);
        driverController.addDriver(new ObjectMapper().writeValueAsString(driver));
        verify(driverService).addDriver(driver);
    }

    @Test
    void updateDriver() throws JsonProcessingException, SQLException {
        doNothing().when(driverService).updateDriver(driver);
        driverController.updateDriver(new ObjectMapper().writeValueAsString(driver));
        verify(driverService).updateDriver(driver);
    }

    @Test
    void updateDriverWithException() throws JsonProcessingException, SQLException {
        doThrow(new SQLException("Same values")).when(driverService).updateDriver(driver);
        driverController.updateDriver(new ObjectMapper().writeValueAsString(driver));
        verify(driverService).updateDriver(driver);
    }
}