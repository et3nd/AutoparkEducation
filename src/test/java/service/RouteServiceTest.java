package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.RouteDao;
import entity.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RouteServiceTest {
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
    void getRoute() throws SQLException, JsonProcessingException {
        doReturn(route).when(routeDao).getRoute(route.getRouteNumber());
        assertEquals(new ObjectMapper().setDateFormat(df).writeValueAsString(route), routeService.getRoute(route.getRouteNumber()));
        verify(routeDao).getRoute(route.getRouteNumber());
    }

    @Test
    void addRoute() throws SQLException, JsonProcessingException {
        doNothing().when(routeDao).addRoute(route);
        routeService.addRoute(new ObjectMapper().setDateFormat(df).writeValueAsString(route));
        verify(routeDao).addRoute(route);
    }

    @Test
    void addRouteWithException() throws SQLException, JsonProcessingException {
        doThrow(SQLException.class).when(routeDao).addRoute(route);
        routeService.addRoute(new ObjectMapper().setDateFormat(df).writeValueAsString(route));
        verify(routeDao).addRoute(route);
    }

    @Test
    void updateRoute() throws SQLException, JsonProcessingException {
        Route outputRoute = new Route();
        outputRoute.setRouteNumber(1);
        outputRoute.setDistance(500);
        doNothing().when(routeDao).updateRoute(outputRoute);
        doReturn(route).when(routeDao).getRoute(route.getRouteNumber());
        routeService.updateRoute(new ObjectMapper().setDateFormat(df).writeValueAsString(outputRoute));
        verify(routeDao).updateRoute(outputRoute);
    }

    @Test
    void removeRoute() throws SQLException {
        doNothing().when(routeDao).removeRoute(route.getRouteNumber());
        routeService.removeRoute(route.getRouteNumber());
        verify(routeDao).removeRoute(route.getRouteNumber());
    }
}