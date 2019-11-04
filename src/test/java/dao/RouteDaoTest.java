package dao;

import entity.Route;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void getRoute() throws SQLException {
        assertEquals(route, routeDao.getRoute(route.getRouteNumber()));
        assertThrows(SQLException.class, () -> routeDao.getRoute(2));
    }

    @Test
    void addRouteWithDefaultValues() throws SQLException {
        Route defaultRoute = new Route();
        defaultRoute.setRouteNumber(10);
        routeDao.addRoute(defaultRoute);
        assertEquals(defaultRoute, routeDao.getRoute(defaultRoute.getRouteNumber()));
    }

    @Test
    void addRouteWithUsedValue() throws SQLException {
        assertEquals(route, routeDao.getRoute(route.getRouteNumber()));
        assertThrows(SQLException.class, () -> routeDao.addRoute(route));
    }

    @Test
    void updateRoute() throws SQLException {
        assertEquals(route, routeDao.getRoute(route.getRouteNumber()));
        route.setStops("New");
        routeDao.updateRoute(route);
        assertEquals(route, routeDao.getRoute(route.getRouteNumber()));
    }

    @Test
    void removeRoute() throws SQLException {
        assertEquals(route, routeDao.getRoute(route.getRouteNumber()));
        routeDao.removeRoute(route.getRouteNumber());
        assertThrows(SQLException.class, () -> routeDao.getRoute(route.getRouteNumber()));
    }

    @AfterEach
    void remove() throws SQLException {
        routeDao.removeRoute(route.getRouteNumber());
        routeDao.removeRoute(10);
    }
}