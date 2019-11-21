package com.education.service;

import com.education.dao.RouteDao;
import com.education.entity.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@Slf4j
public class RouteService {
    private final RouteDao routeDao;

    @Autowired
    public RouteService(RouteDao routeDao) {
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

    public void updateRoute(Route inputRoute) {
        Route outputRoute = routeDao.getRoute(inputRoute.getRouteNumber());
        if (!(inputRoute.getStartStation().equals("default")))
            outputRoute.setStartStation(inputRoute.getStartStation());
        if (!(inputRoute.getEndStation().equals("default"))) outputRoute.setEndStation(inputRoute.getEndStation());
        if (!(inputRoute.getStops().equals("default"))) outputRoute.setStops(inputRoute.getStops());
        if (!(inputRoute.getDistance() == 0)) outputRoute.setDistance(inputRoute.getDistance());
        routeDao.updateRoute(outputRoute);
    }

    public void removeRoute(int routeNumber) {
        routeDao.getRoute(routeNumber);
        routeDao.removeRoute(routeNumber);
    }

    public Route getRoute(int routeNumber) {
        return routeDao.getRoute(routeNumber);

    }
}
