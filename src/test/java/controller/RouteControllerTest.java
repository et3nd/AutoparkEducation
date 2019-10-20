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

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RouteControllerTest {
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private RouteController routeController = new RouteController();
    private Route route = new Route();

    @Mock
    private RouteService routeService;

    @BeforeEach
    void set() {
        routeController.setRouteService(routeService);
        route.setRouteNumber(1);
    }

    @Test
    void getRoute() throws JsonProcessingException {
        doReturn(route).when(routeService).getRoute(route.getRouteNumber());
        assertEquals(new ObjectMapper().setDateFormat(df).writeValueAsString(route), routeController.getRoute(route.getRouteNumber()));
        verify(routeService).getRoute(route.getRouteNumber());
    }

    @Test
    void removeRoute() {
        doNothing().when(routeService).removeRoute(route.getRouteNumber());
        routeController.removeRoute(route.getRouteNumber());
        verify(routeService).removeRoute(route.getRouteNumber());
    }

    @Test
    void addRoute() throws JsonProcessingException {
        doNothing().when(routeService).addRoute(route);
        doReturn(null, route).when(routeService).getRoute(route.getRouteNumber());
        routeController.addRoute(new ObjectMapper().setDateFormat(df).writeValueAsString(route));
        verify(routeService).addRoute(route);
    }

    @Test
    void addRouteWithException() {
        routeController.addRoute("Input");
    }

    @Test
    void updateRoute() throws JsonProcessingException {
        doNothing().when(routeService).updateRoute(route);
        doReturn(route).when(routeService).getRoute(route.getRouteNumber());
        routeController.updateRoute(new ObjectMapper().setDateFormat(df).writeValueAsString(route));
        verify(routeService).updateRoute(route);
    }

    @Test
    void updateDriverWithException() {
        routeController.updateRoute("Input");
    }
}