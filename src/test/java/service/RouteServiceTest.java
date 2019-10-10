package service;

import dao.RouteDao;
import entity.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RouteServiceTest {
    private RouteService routeService = new RouteService();

    @Mock
    private RouteDao routeDao;

    @BeforeEach
    void setRouteDao() {
        routeService.setRouteDao(routeDao);
    }

    @Test
    void getRoute() {
        doReturn(new Route()).when(routeDao).getRoute(0);
        assertEquals(new Route(), routeService.getRoute(0));
        verify(routeDao).getRoute(0);
    }

    @Test
    void addRoute() throws SQLException {
        doNothing().when(routeDao).addRoute(new Route());
        routeService.addRoute(new Route());
        verify(routeDao).addRoute(new Route());
    }

    @Test
    void addRouteWithException() throws SQLException {
        doThrow(SQLException.class).when(routeDao).addRoute(new Route());
        routeService.addRoute(new Route());
        verify(routeDao).addRoute(new Route());
    }

    @Test
    void updateRoute() {
        doNothing().when(routeDao).updateRoute(new Route());
        routeService.updateRoute(new Route());
        verify(routeDao).updateRoute(new Route());
    }

    @Test
    void removeRoute() {
        doNothing().when(routeDao).removeRoute(0);
        routeService.removeRoute(0);
        verify(routeDao).removeRoute(0);
    }
}