package service;

import dao.RoutesDao;
import entity.Routes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoutesServiceTest {
    private RoutesService routesService = new RoutesService();

    @Mock
    private RoutesDao routesDao;

    @BeforeEach
    void setRouteDao() {
        routesService.setRoutesDao(routesDao);
    }

    @Test
    void getRoute() {
        when(routesDao.getRoute(0)).thenReturn(new Routes());
        assertEquals(new Routes(), routesService.getRoute(0));
    }

    @Test
    void addRoute() throws SQLException {
        doNothing().when(routesDao).addRoute(new Routes());
        routesService.addRoute(new Routes());
    }

    @Test
    void addRouteWithException() throws SQLException {
        doThrow(SQLException.class).when(routesDao).addRoute(new Routes());
        routesService.addRoute(new Routes());
    }

    @Test
    void updateRoute() {
        doNothing().when(routesDao).updateRoute(new Routes());
        routesService.updateRoute(new Routes());
    }

    @Test
    void removeRoute() {
        doNothing().when(routesDao).removeRoute(0);
        routesService.removeRoute(0);
    }
}