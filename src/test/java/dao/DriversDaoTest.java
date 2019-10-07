package dao;

import entity.Drivers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DriversDaoTest {
    private DriversDao driverDao = new DriversDao();
    private Drivers driver = new Drivers();

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
        assertEquals(driver, driverDao.getDriver(9999));
        assertEquals(new Drivers(), driverDao.getDriver(1));
    }

    @Test
    void addDriverWithDefaultValues() throws SQLException {
        Drivers defaultDriver = new Drivers();
        driverDao.addDriver(defaultDriver);
        assertEquals(defaultDriver, driverDao.getDriver(111111));
    }

    @Test
    void addDriverWithUsedValue() {
        assertThrows(SQLException.class, () -> driverDao.addDriver(driver));
    }

    @Test
    void updateDriver() {
        driver.setFio("FIO");
        driverDao.updateDriver(driver);
        assertEquals(driver, driverDao.getDriver(9999));
    }

    @Test
    void removeDriver() {
        driverDao.removeDriver(9999);
    }

    @AfterEach
    void remove() {
        driverDao.removeDriver(9999);
        driverDao.removeDriver(111111);
    }
}