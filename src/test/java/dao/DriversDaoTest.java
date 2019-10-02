package dao;

import entity.Drivers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DriversDaoTest {
    private DriversDao driverDao = new DriversDao();

    @Test
    void driverTest() {
        Executable testInsert = () -> {
            Drivers driver = new Drivers();
            driverDao.addDriver(driver);
            assertEquals(driver.toString(), driverDao.getDriver(111111).toString());
            driver.setLicense(9902);
            driverDao.addDriver(driver);
            assertEquals(driver.toString(), driverDao.getDriver(9902).toString());
            driverDao.addDriver(driver);
        };
        assertThrows(SQLException.class, testInsert);
    }

    @AfterEach
    void remove() {
        driverDao.removeDriver(9902);
        driverDao.removeDriver(111111);
    }
}