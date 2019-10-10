package dao;

import entity.Driver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DriverDaoTest {
    private DriverDao driverDao = new DriverDao();
    private Driver driver = new Driver();

    @BeforeEach
    void addDriver() throws SQLException {
        driver.setLicense(9999);
        driver.setAddress("Address");
        driver.setBirthDate(Date.valueOf(LocalDate.of(1900, 1, 1)));
        driver.setSalary(50000);
        driver.setFio("Name");
        driverDao.addDriver(driver);
    }

    @Test
    void getDriver() {
        assertEquals(driver, driverDao.getDriver(driver.getLicense()));
        assertNull(driverDao.getDriver(1));
    }

    @Test
    void addDriverWithDefaultValues() throws SQLException {
        Driver defaultDriver = new Driver();
        defaultDriver.setLicense(9000);
        driverDao.addDriver(defaultDriver);
        assertEquals(defaultDriver, driverDao.getDriver(defaultDriver.getLicense()));
    }

    @Test
    void addDriverWithUsedValue() {
        assertEquals(driver, driverDao.getDriver(driver.getLicense()));
        assertThrows(SQLException.class, () -> driverDao.addDriver(driver));
    }

    @Test
    void updateDriver() {
        assertEquals(driver, driverDao.getDriver(driver.getLicense()));
        driver.setFio("FIO");
        driverDao.updateDriver(driver);
        assertEquals(driver, driverDao.getDriver(driver.getLicense()));
    }

    @Test
    void removeDriver() {
        assertEquals(driver, driverDao.getDriver(driver.getLicense()));
        driverDao.removeDriver(driver.getLicense());
        assertNull(driverDao.getDriver(driver.getLicense()));
    }

    @AfterEach
    void remove() {
        driverDao.removeDriver(driver.getLicense());
        driverDao.removeDriver(9000);
    }
}