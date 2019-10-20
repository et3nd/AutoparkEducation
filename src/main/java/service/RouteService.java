package service;

import dao.RouteDao;
import entity.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class RouteService {
    private static final Logger log = LoggerFactory.getLogger(RouteService.class);
    private RouteDao routeDao;

    public void setRouteDao(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    public void addRoute(Route route) {
        try {
            routeDao.addRoute(route);
        } catch (SQLException e) {
            log.error("This value of route number is used");
        }
    }

    public void updateRoute(Route route) {
        routeDao.updateRoute(route);
    }

    public void removeRoute(int routeNumber) {
        routeDao.removeRoute(routeNumber);
    }

    public Route getRoute(int routeNumber) {
        return routeDao.getRoute(routeNumber);
    }
}
