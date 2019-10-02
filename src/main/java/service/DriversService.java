package service;

import dao.DriversDao;
import entity.Drivers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class DriversService {
    private static final Logger log = LoggerFactory.getLogger(DriversService.class);
    private DriversDao driversDao;

    public void setDriversDao(DriversDao driversDao) {
        this.driversDao = driversDao;
    }

    public void addDriver(Drivers driver) {
        try {
            driversDao.addDriver(driver);
        } catch (SQLException e) {
            log.error("This value of license is used");
        }
    }

    public void updateDriver(Drivers driver) {
        driversDao.updateDriver(driver);
    }

    public void removeDriver(int license) {
        driversDao.removeDriver(license);
    }

    public Drivers getDriver(int license) {
        return driversDao.getDriver(license);
    }
}
