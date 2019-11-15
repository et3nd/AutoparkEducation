package controller;

import entity.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import service.DriverService;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = DriverController.class)
class DriverControllerTest {

    @Autowired
    private DriverController driverController;
    private Driver driver = new Driver();

    @MockBean
    private DriverService driverService;

    @BeforeEach
    void setLicense() {
        driver.setLicense(1);
    }

    @Test
    void getDriver() {
        doReturn(driver).when(driverService).getDriver(driver.getLicense());
        driverController.getDriver(driver.getLicense());
        verify(driverService).getDriver(driver.getLicense());
    }

    @Test
    void removeDriver() {
        doNothing().when(driverService).removeDriver(driver.getLicense());
        driverController.removeDriver(driver.getLicense());
        verify(driverService).removeDriver(driver.getLicense());
    }

    @Test
    void addDriver() throws SQLException {
        doNothing().when(driverService).addDriver(driver);
        driverController.addDriver(driver);
        verify(driverService).addDriver(driver);
    }

    @Test
    void addDriverWithException() throws SQLException {
        doThrow(SQLException.class).when(driverService).addDriver(driver);
        driverController.addDriver(driver);
        verify(driverService).addDriver(driver);
    }

    @Test
    void updateDriver() {
        doNothing().when(driverService).updateDriver(driver);
        driverController.updateDriver(driver);
        verify(driverService).updateDriver(driver);
    }
}