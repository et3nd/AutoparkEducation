package dao;

import entity.Route;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class RouteDaoTest {
    private RouteDao routeDao = new RouteDao();
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
        assertEquals(new Route(), routeDao.getRoute(11));
    }

    @Test
    void addRouteWithDefaultValues() throws SQLException {
        Route defaultRoute = new Route();
        routeDao.addRoute(defaultRoute);
        assertEquals(defaultRoute, routeDao.getRoute(0));
    }

    @Test
    void addRouteWithUsedValue() {
        assertEquals(route, routeDao.getRoute(route.getRouteNumber()));
        assertThrows(SQLException.class, () -> routeDao.addRoute(route));
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
        assertNotEquals(route, routeDao.getRoute(route.getRouteNumber()));
    }

    @AfterEach
    void remove() {
        routeDao.removeRoute(route.getRouteNumber());
        routeDao.removeRoute(0);
    }
}