package com.education.service;

import com.education.dao.DriverDao;
import com.education.entity.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class DriverServiceTest {

    @Autowired
    private DriverService driverService;
    private Driver driver = new Driver();

    @MockBean
    private DriverDao driverDao;

    @BeforeEach
    void setLicense() {
        driver.setLicense(1);
    }

    @Test
    void getDriver() {
        doReturn(driver).when(driverDao).getDriver(driver.getLicense());
        assertEquals(driver, driverService.getDriver(driver.getLicense()));
        verify(driverDao).getDriver(driver.getLicense());
    }

    @Test
    void addDriver() throws SQLException {
        doNothing().when(driverDao).addDriver(driver);
        driverService.addDriver(driver);
        verify(driverDao).addDriver(driver);
    }

    @Test
    void addDriverWithException() throws SQLException {
        doThrow(SQLException.class).when(driverDao).addDriver(driver);
        assertThrows(SQLException.class, () -> driverService.addDriver(driver));
        verify(driverDao).addDriver(driver);
    }

    @Test
    void updateDriver() {
        Driver outputDriver = new Driver();
        outputDriver.setLicense(1);
        outputDriver.setSalary(25000);
        doNothing().when(driverDao).updateDriver(outputDriver);
        doReturn(driver).when(driverDao).getDriver(driver.getLicense());
        driverService.updateDriver(outputDriver);
        verify(driverDao).updateDriver(outputDriver);
    }

    @Test
    void removeDriver() {
        doNothing().when(driverDao).removeDriver(driver.getLicense());
        driverService.removeDriver(driver.getLicense());
        verify(driverDao).removeDriver(driver.getLicense());
    }
}