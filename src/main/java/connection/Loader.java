package connection;

import dao.DriversDao;
import entity.Drivers;
import service.DriversService;

public class Loader {
    public static void main(String[] args) {
        DriversService driversService = new DriversService();
        driversService.setDriversDao(new DriversDao());
        driversService.getDriver(9902);
    }
}
