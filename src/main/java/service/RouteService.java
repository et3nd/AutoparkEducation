package service;

import dao.RouteDao;
import entity.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

class RouteService {
    private static final Logger log = LoggerFactory.getLogger(RouteService.class);
    private RouteDao routeDao;

    void setRouteDao(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    void addRoute(Route route) {
        try {
            routeDao.addRoute(route);
        } catch (SQLException e) {
            log.error("This value of route number is used");
        }
    }

    void updateRoute(Route route) {
        routeDao.updateRoute(route);
    }

    void removeRoute(int routeNumber) {
        routeDao.removeRoute(routeNumber);
    }

    Route getRoute(int routeNumber) {
        return routeDao.getRoute(routeNumber);
    }
}
