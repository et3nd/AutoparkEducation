package com.education.web.controller;

import com.education.entity.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.education.service.RouteService;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

@SpringBootTest
class RouteControllerTest {

    @Autowired
    private RouteController routeController;
    private Route route = new Route();

    @MockBean
    private RouteService routeService;

    @BeforeEach
    void setRouteNumber() {
        route.setRouteNumber(1);
    }

    @Test
    void getRoute() {
        doReturn(route).when(routeService).getRoute(route.getRouteNumber());
        routeController.getRoute(route.getRouteNumber());
        verify(routeService).getRoute(route.getRouteNumber());
    }

    @Test
    void removeRoute() {
        doNothing().when(routeService).removeRoute(route.getRouteNumber());
        routeController.removeRoute(route.getRouteNumber());
        verify(routeService).removeRoute(route.getRouteNumber());
    }

    @Test
    void addRoute() throws SQLException {
        doNothing().when(routeService).addRoute(route);
        routeController.addRoute(route);
        verify(routeService).addRoute(route);
    }

    @Test
    void addRouteWithException() throws SQLException {
        doThrow(SQLException.class).when(routeService).addRoute(route);
        routeController.addRoute(route);
        verify(routeService).addRoute(route);
    }

    @Test
    void updateRoute() {
        doNothing().when(routeService).updateRoute(route);
        routeController.updateRoute(route);
        verify(routeService).updateRoute(route);
    }
}