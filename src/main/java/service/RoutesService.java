package service;

import dao.RoutesDao;
import entity.Routes;

public class RoutesService {
    private RoutesDao routesDao;

    public void setRoutesDao(RoutesDao routesDao) {
        this.routesDao = routesDao;
    }

    public void addRoute(Routes route) {
        routesDao.addRoute(route);
    }

    public void updateRoute(Routes route) {
        routesDao.updateRoute(route);
    }

    public void removeRoute(int id) {
        routesDao.removeRoute(id);
    }

    public void getRoute(int id) {
        routesDao.getRoute(id);
    }
}
