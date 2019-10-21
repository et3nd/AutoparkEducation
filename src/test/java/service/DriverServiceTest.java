package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DriverDao;
import entity.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverServiceTest {
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private DriverService driverService = new DriverService();
    private Driver driver = new Driver();

    @Mock
    private DriverDao driverDao;

    @BeforeEach
    void setDriverDao() {
        driverService.setDriverDao(driverDao);
        driver.setLicense(1);
    }

    @Test
    void getDriver() throws SQLException, JsonProcessingException {
        doReturn(driver).when(driverDao).getDriver(driver.getLicense());
        assertEquals(new ObjectMapper().setDateFormat(df).writeValueAsString(driver), driverService.getDriver(driver.getLicense()));
        verify(driverDao).getDriver(driver.getLicense());
    }

    @Test
    void addDriver() throws SQLException, JsonProcessingException {
        doNothing().when(driverDao).addDriver(driver);
        driverService.addDriver(new ObjectMapper().setDateFormat(df).writeValueAsString(driver));
        verify(driverDao).addDriver(driver);
    }

    @Test
    void addDriverWithException() throws SQLException, JsonProcessingException {
        doThrow(SQLException.class).when(driverDao).addDriver(driver);
        driverService.addDriver(new ObjectMapper().setDateFormat(df).writeValueAsString(driver));
        verify(driverDao).addDriver(driver);
    }

    @Test
    void updateDriver() throws SQLException, JsonProcessingException {
        Driver outputDriver = new Driver();
        outputDriver.setLicense(1);
        outputDriver.setSalary(25000);
        doNothing().when(driverDao).updateDriver(outputDriver);
        doReturn(driver).when(driverDao).getDriver(driver.getLicense());
        driverService.updateDriver(new ObjectMapper().setDateFormat(df).writeValueAsString(outputDriver));
        verify(driverDao).updateDriver(outputDriver);
    }

    @Test
    void removeDriver() throws SQLException {
        doNothing().when(driverDao).removeDriver(driver.getLicense());
        driverService.removeDriver(driver.getLicense());
        verify(driverDao).removeDriver(driver.getLicense());
    }
}