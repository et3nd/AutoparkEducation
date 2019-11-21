package com.education.service;

import com.education.dao.RouteDao;
import com.education.entity.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class RouteServiceTest {

    @Autowired
    private RouteService routeService;
    private Route route = new Route();

    @MockBean
    private RouteDao routeDao;

    @BeforeEach
    void setRouteNumber() {
        route.setRouteNumber(1);
    }

    @Test
    void getRoute() {
        doReturn(route).when(routeDao).getRoute(route.getRouteNumber());
        assertEquals(route, routeService.getRoute(route.getRouteNumber()));
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
    void updateRoute() {
        Route outputRoute = new Route();
        outputRoute.setRouteNumber(1);
        outputRoute.setDistance(500);
        doNothing().when(routeDao).updateRoute(outputRoute);
        doReturn(route).when(routeDao).getRoute(route.getRouteNumber());
        routeService.updateRoute(outputRoute);
        verify(routeDao).updateRoute(outputRoute);
    }

    @Test
    void removeRoute() {
        doNothing().when(routeDao).removeRoute(route.getRouteNumber());
        routeService.removeRoute(route.getRouteNumber());
        verify(routeDao).removeRoute(route.getRouteNumber());
    }
}