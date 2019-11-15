package dao;

import configuration.JdbcConfiguration;
import entity.Driver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {DriverDao.class, JdbcConfiguration.class})
class DriverDaoTest {

    @Autowired
    private DriverDao driverDao;
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
    }

    @Test
    void addDriverWithDefaultValues() throws SQLException {
        Driver defaultDriver = new Driver();
        defaultDriver.setLicense(9000);
        driverDao.addDriver(defaultDriver);
        assertEquals(defaultDriver, driverDao.getDriver(defaultDriver.getLicense()));
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
        assertThrows(Exception.class, () -> driverDao.getDriver(driver.getLicense()));
    }

    @AfterEach
    void remove() {
        driverDao.removeDriver(driver.getLicense());
        driverDao.removeDriver(9000);
    }
}