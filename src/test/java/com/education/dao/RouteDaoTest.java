package com.education.dao;

import com.education.entity.Route;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class RouteDaoTest {

    @Autowired
    private RouteDao routeDao;
    private Route route = new Route();

    @BeforeEach
    void addRoute() throws SQLException {
        route.setRouteNumber(1);
        route.setStops("Stops");
        route.setDistance(10);
        route.setStartStation("Start");
        route.setEndStation("End");
        routeDao.addRoute(route);
    }

    @Test
    void getRoute() {
        assertEquals(route, routeDao.getRoute(route.getRouteNumber()));
    }

    @Test
    void addRouteWithDefaultValues() throws SQLException {
        Route defaultRoute = new Route();
        defaultRoute.setRouteNumber(10);
        routeDao.addRoute(defaultRoute);
        assertEquals(defaultRoute, routeDao.getRoute(defaultRoute.getRouteNumber()));
    }

    @Test
    void updateRoute() {
        assertEquals(route, routeDao.getRoute(route.getRouteNumber()));
        route.setStops("New");
        routeDao.updateRoute(route);
        assertEquals(route, routeDao.getRoute(route.getRouteNumber()));
    }

    @Test
    void removeRoute() {
        assertEquals(route, routeDao.getRoute(route.getRouteNumber()));
        routeDao.removeRoute(route.getRouteNumber());
        assertThrows(Exception.class, () -> routeDao.getRoute(route.getRouteNumber()));
    }

    @AfterEach
    void remove() {
        routeDao.removeRoute(route.getRouteNumber());
        routeDao.removeRoute(10);
    }
}