package com.education.service;

import com.education.dao.DriverDao;
import com.education.entity.Driver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

@Service
@Slf4j
public class DriverService {
    private final DriverDao driverDao;

    @Autowired
    public DriverService(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    public void addDriver(Driver driver) throws SQLException {
        try {
            driverDao.addDriver(driver);
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }

    public void updateDriver(Driver inputDriver) {
        Driver outputDriver = driverDao.getDriver(inputDriver.getLicense());
        if (!(inputDriver.getFio().equals("default"))) outputDriver.setFio(inputDriver.getFio());
        if (!(inputDriver.getSalary() == 0)) outputDriver.setSalary(inputDriver.getSalary());
        if (!(inputDriver.getAddress().equals("default"))) outputDriver.setAddress(inputDriver.getAddress());
        if (!(inputDriver.getBirthDate().equals(Date.valueOf(LocalDate.of(1900, 1, 1)))))
            outputDriver.setBirthDate(inputDriver.getBirthDate());
        driverDao.updateDriver(outputDriver);
    }

    public void removeDriver(int license) {
        driverDao.getDriver(license);
        driverDao.removeDriver(license);
    }

    public Driver getDriver(int license) {
        return driverDao.getDriver(license);
    }
}
