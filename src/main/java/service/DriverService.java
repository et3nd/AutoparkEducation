package service;

import dao.DriverDao;
import entity.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class DriverService {
    private static final Logger log = LoggerFactory.getLogger(DriverService.class);
    private DriverDao driverDao;

    public void setDriverDao(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    public void addDriver(Driver driver) {
        try {
            driverDao.addDriver(driver);
        } catch (SQLException e) {
            log.error("This value of license is used");
        }
    }

    public void updateDriver(Driver driver) {
        driverDao.updateDriver(driver);
    }

    public void removeDriver(int license) {
        driverDao.removeDriver(license);
    }

    public Driver getDriver(int license) {
        return driverDao.getDriver(license);
    }
}
