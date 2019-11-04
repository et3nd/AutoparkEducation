package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.RouteService;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RouteControllerTest {
    private RouteController routeController = new RouteController();
    private Route route = new Route();

    @Mock
    private RouteService routeService;

    @BeforeEach
    void setRouteService() {
        routeController.setRouteService(routeService);
        route.setRouteNumber(1);
    }

    @Test
    void getRoute() throws SQLException {
        doReturn(route).when(routeService).getRoute(route.getRouteNumber());
        routeController.getRoute(route.getRouteNumber());
        verify(routeService).getRoute(route.getRouteNumber());
    }

    @Test
    void getRouteWithException() throws SQLException {
        doThrow(SQLException.class).when(routeService).getRoute(route.getRouteNumber());
        routeController.getRoute(route.getRouteNumber());
        verify(routeService).getRoute(route.getRouteNumber());
    }

    @Test
    void removeRoute() throws SQLException {
        doNothing().when(routeService).removeRoute(route.getRouteNumber());
        routeController.removeRoute(route.getRouteNumber());
        verify(routeService).removeRoute(route.getRouteNumber());
    }

    @Test
    void removeRouteWithException() throws SQLException {
        doThrow(SQLException.class).when(routeService).removeRoute(route.getRouteNumber());
        routeController.removeRoute(route.getRouteNumber());
        verify(routeService).removeRoute(route.getRouteNumber());
    }

    @Test
    void addRoute() throws JsonProcessingException, SQLException {
        doNothing().when(routeService).addRoute(route);
        routeController.addRoute(new ObjectMapper().writeValueAsString(route));
        verify(routeService).addRoute(route);
    }

    @Test
    void addRouteWithException() throws SQLException, JsonProcessingException {
        doThrow(SQLException.class).when(routeService).addRoute(route);
        routeController.addRoute(new ObjectMapper().writeValueAsString(route));
        verify(routeService).addRoute(route);
    }

    @Test
    void updateRoute() throws JsonProcessingException, SQLException {
        doNothing().when(routeService).updateRoute(route);
        routeController.updateRoute(new ObjectMapper().writeValueAsString(route));
        verify(routeService).updateRoute(route);
    }

    @Test
    void updateRouteWithException() throws JsonProcessingException, SQLException {
        doThrow(new SQLException("Same values")).when(routeService).updateRoute(route);
        routeController.updateRoute(new ObjectMapper().writeValueAsString(route));
        verify(routeService).updateRoute(route);
    }
}