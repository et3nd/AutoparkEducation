package service;

import dao.RouteDao;
import entity.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class RouteService {
    private static final Logger log = LoggerFactory.getLogger(RouteService.class);
    private RouteDao routeDao = new RouteDao();

    void setRouteDao(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    public void addRoute(Route route) throws SQLException {
        try {
            routeDao.addRoute(route);
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }

    public void updateRoute(Route inputRoute) throws SQLException {
        try {
            Route outputRoute = routeDao.getRoute(inputRoute.getRouteNumber());
            if (inputRoute.equals(outputRoute)) throw new SQLException("Same values");
            if (!(inputRoute.getStartStation().equals("default")))
                outputRoute.setStartStation(inputRoute.getStartStation());
            if (!(inputRoute.getEndStation().equals("default"))) outputRoute.setEndStation(inputRoute.getEndStation());
            if (!(inputRoute.getStops().equals("default"))) outputRoute.setStops(inputRoute.getStops());
            if (!(inputRoute.getDistance() == 0)) outputRoute.setDistance(inputRoute.getDistance());
            routeDao.updateRoute(outputRoute);
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }

    public void removeRoute(int routeNumber) throws SQLException {
        try {
            routeDao.getRoute(routeNumber);
            routeDao.removeRoute(routeNumber);
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }

    public Route getRoute(int routeNumber) throws SQLException {
        try {
            return routeDao.getRoute(routeNumber);
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }
}
