package dao;

import entity.Routes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoutesDaoTest {
    private RoutesDao routeDao = new RoutesDao();

    @Test
    void routeTest() {
        Executable testInsert = () -> {
            Routes route = new Routes();
            routeDao.addRoute(route);
            assertEquals(route.toString(), routeDao.getRoute(0).toString());
            route.setRouteNumber(1);
            routeDao.addRoute(route);
            assertEquals(route.toString(), routeDao.getRoute(1).toString());
            routeDao.addRoute(route);
        };
        assertThrows(SQLException.class, testInsert);
    }

    @AfterEach
    void remove() {
        routeDao.removeRoute(0);
        routeDao.removeRoute(1);
    }
}