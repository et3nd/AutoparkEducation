package service;

import dao.DriverDao;
import entity.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class DriverService {
    private static final Logger log = LoggerFactory.getLogger(DriverService.class);
    private DriverDao driverDao = new DriverDao();

    void setDriverDao(DriverDao driverDao) {
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

    public void updateDriver(Driver inputDriver) throws SQLException {
        try {
            Driver outputDriver = driverDao.getDriver(inputDriver.getLicense());
            if (inputDriver.equals(outputDriver)) throw new SQLException("Same values");
            if (!(inputDriver.getFio().equals("default"))) outputDriver.setFio(inputDriver.getFio());
            if (!(inputDriver.getSalary() == 0)) outputDriver.setSalary(inputDriver.getSalary());
            if (!(inputDriver.getAddress().equals("default"))) outputDriver.setAddress(inputDriver.getAddress());
            if (!(inputDriver.getBirthDate().equals(Date.valueOf(LocalDate.of(1900, 1, 1)))))
                outputDriver.setBirthDate(inputDriver.getBirthDate());
            driverDao.updateDriver(outputDriver);
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }

    public void removeDriver(int license) throws SQLException {
        try {
            driverDao.getDriver(license);
            driverDao.removeDriver(license);
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }

    public Driver getDriver(int license) throws SQLException {
        try {
            return driverDao.getDriver(license);
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }
}
