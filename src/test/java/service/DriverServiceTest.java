package service;

import dao.DriverDao;
import entity.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverServiceTest {
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
    void getDriver() throws SQLException {
        doReturn(driver).when(driverDao).getDriver(driver.getLicense());
        assertEquals(driver, driverService.getDriver(driver.getLicense()));
        verify(driverDao).getDriver(driver.getLicense());
    }

    @Test
    void getDriverWithException() throws SQLException {
        doThrow(SQLException.class).when(driverDao).getDriver(driver.getLicense());
        assertThrows(SQLException.class, () -> driverService.getDriver(driver.getLicense()));
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
    void updateDriver() throws SQLException {
        Driver outputDriver = new Driver();
        outputDriver.setLicense(1);
        outputDriver.setSalary(25000);
        doNothing().when(driverDao).updateDriver(outputDriver);
        doReturn(driver).when(driverDao).getDriver(driver.getLicense());
        driverService.updateDriver(outputDriver);
        verify(driverDao).updateDriver(outputDriver);
    }

    @Test
    void removeDriver() throws SQLException {
        doNothing().when(driverDao).removeDriver(driver.getLicense());
        driverService.removeDriver(driver.getLicense());
        verify(driverDao).removeDriver(driver.getLicense());
    }
}