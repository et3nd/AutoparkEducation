package service;

import dao.DriversDao;
import entity.Drivers;

public class DriversService {
    private DriversDao driversDao;

    public void setDriversDao(DriversDao driversDao) {
        this.driversDao = driversDao;
    }

    public void addDriver(Drivers driver) {
        driversDao.addDriver(driver);
    }

    public void updateDriver(Drivers driver) {
        driversDao.updateDriver(driver);
    }

    public void removeDriver(int id) {
        driversDao.removeDriver(id);
    }

    public void getDriver(int id) {
        driversDao.getDriver(id);
    }
}
