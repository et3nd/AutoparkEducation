package dao;

import entity.Routes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoutesDaoTest {
    private RoutesDao routesDao = new RoutesDao();
    private Routes route = new Routes();

    @BeforeEach
    void addRoute() throws SQLException {
        route.setRouteNumber(1);
        route.setStops("Stops");
        route.setDistance(10);
        route.setStartStation("Start");
        route.setEndStation("End");
        routesDao.addRoute(route);
    }

    @Test
    void getRoute() {
        assertEquals(route, routesDao.getRoute(1));
        assertEquals(new Routes(), routesDao.getRoute(11));
    }

    @Test
    void addRouteWithDefaultValues() throws SQLException {
        Routes defaultRoute = new Routes();
        routesDao.addRoute(defaultRoute);
        assertEquals(defaultRoute, routesDao.getRoute(0));
    }

    @Test
    void addRouteWithUsedValue() {
        assertThrows(SQLException.class, () -> routesDao.addRoute(route));
    }

    @Test
    void updateRoute() {
        route.setStops("New");
        routesDao.updateRoute(route);
        assertEquals(route, routesDao.getRoute(1));
    }

    @Test
    void removeRoute() {
        routesDao.removeRoute(1);
    }

    @AfterEach
    void remove() {
        routesDao.removeRoute(1);
        routesDao.removeRoute(0);
    }
}