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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RouteServiceTest {
    private RouteService routeService = new RouteService();
    private Route route = new Route();

    @Mock
    private RouteDao routeDao;

    @BeforeEach
    void setRouteDao() {
        routeService.setRouteDao(routeDao);
        route.setRouteNumber(1);
    }

    @Test
    void getRoute() throws SQLException {
        doReturn(route).when(routeDao).getRoute(route.getRouteNumber());
        assertEquals(route, routeService.getRoute(route.getRouteNumber()));
        verify(routeDao).getRoute(route.getRouteNumber());
    }

    @Test
    void getRouteWithException() throws SQLException {
        doThrow(SQLException.class).when(routeDao).getRoute(route.getRouteNumber());
        assertThrows(SQLException.class, () -> routeService.getRoute(route.getRouteNumber()));
        verify(routeDao).getRoute(route.getRouteNumber());
    }

    @Test
    void addRoute() throws SQLException {
        doNothing().when(routeDao).addRoute(route);
        routeService.addRoute(route);
        verify(routeDao).addRoute(route);
    }

    @Test
    void addRouteWithException() throws SQLException {
        doThrow(SQLException.class).when(routeDao).addRoute(route);
        assertThrows(SQLException.class, () -> routeService.addRoute(route));
        verify(routeDao).addRoute(route);
    }

    @Test
    void updateRoute() throws SQLException {
        Route outputRoute = new Route();
        outputRoute.setRouteNumber(1);
        outputRoute.setDistance(500);
        doNothing().when(routeDao).updateRoute(outputRoute);
        doReturn(route).when(routeDao).getRoute(route.getRouteNumber());
        routeService.updateRoute(outputRoute);
        verify(routeDao).updateRoute(outputRoute);
    }

    @Test
    void removeRoute() throws SQLException {
        doNothing().when(routeDao).removeRoute(route.getRouteNumber());
        routeService.removeRoute(route.getRouteNumber());
        verify(routeDao).removeRoute(route.getRouteNumber());
    }
}