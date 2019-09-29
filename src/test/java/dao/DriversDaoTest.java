package dao;

import entity.Drivers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DriversDaoTest {

    private DriversDao driverDao = new DriversDao();

    @Test
    void driverTest() {
        Executable testInsert = () -> {
            Drivers driver = new Drivers();
            driver.setFio("Фамилия Имя Отчество");
            driver.setSalary(80000);
            driver.setLicense(9904);
            driver.setBirthDate(Date.valueOf(LocalDate.of(1998, 7, 25)));
            driverDao.addDriver(driver);
            driver.setLicense(9902);
            driver.setAddress("Адрес");
            driverDao.addDriver(driver);
            driverDao.addDriver(driver);
            assertEquals(driver.toString(), driverDao.getDriver(9902).toString());
        };
        assertThrows(SQLException.class, testInsert);
    }

    @AfterEach
    void remove() {
        driverDao.removeDriver(9902);
        driverDao.removeDriver(9904);
    }
}