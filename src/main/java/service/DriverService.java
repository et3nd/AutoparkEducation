package service;

import dao.DriverDao;
import entity.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

class DriverService {
    private static final Logger log = LoggerFactory.getLogger(DriverService.class);
    private DriverDao driverDao;

    void setDriverDao(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    void addDriver(Driver driver) {
        try {
            driverDao.addDriver(driver);
        } catch (SQLException e) {
            log.error("This value of license is used");
        }
    }

    void updateDriver(Driver driver) {
        driverDao.updateDriver(driver);
    }

    void removeDriver(int license) {
        driverDao.removeDriver(license);
    }

    Driver getDriver(int license) {
        return driverDao.getDriver(license);
    }
}
