package service;

import dao.RoutesDao;
import entity.Routes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class RoutesService {
    private static final Logger log = LoggerFactory.getLogger(RoutesService.class);
    private RoutesDao routesDao;

    public void setRoutesDao(RoutesDao routesDao) {
        this.routesDao = routesDao;
    }

    public void addRoute(Routes route) {
        try {
            routesDao.addRoute(route);
        } catch (SQLException e) {
            log.error("This value of route number is used");
        }
    }

    public void updateRoute(Routes route) {
        routesDao.updateRoute(route);
    }

    public void removeRoute(int id) {
        routesDao.removeRoute(id);
    }

    public Routes getRoute(int id) {
        return routesDao.getRoute(id);
    }
}
