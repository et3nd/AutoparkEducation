package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.RouteDao;
import entity.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class RouteService {
    private static final Logger log = LoggerFactory.getLogger(RouteService.class);
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private RouteDao routeDao;

    public void setRouteDao(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    public String addRoute(String input) {
        try {
            Route route = new ObjectMapper().setDateFormat(df).readValue(input, Route.class);
            routeDao.addRoute(route);
            return "Success";
        } catch (SQLException e) {
            return e.getMessage();
        } catch (IOException e) {
            log.error("Error: ", e);
            return e.getMessage();
        }
    }

    public String updateRoute(String input) {
        try {
            Route inputRoute = new ObjectMapper().setDateFormat(df).readValue(input, Route.class);
            Route outputRoute = routeDao.getRoute(inputRoute.getRouteNumber());
            if (inputRoute.equals(outputRoute)) throw new SQLException("Same values");
            if (!(inputRoute.getStartStation().equals("default")))
                outputRoute.setStartStation(inputRoute.getStartStation());
            if (!(inputRoute.getEndStation().equals("default"))) outputRoute.setEndStation(inputRoute.getEndStation());
            if (!(inputRoute.getStops().equals("default"))) outputRoute.setStops(inputRoute.getStops());
            if (!(inputRoute.getDistance() == 0)) outputRoute.setDistance(inputRoute.getDistance());
            routeDao.updateRoute(outputRoute);
            return "Success";
        } catch (SQLException e) {
            return e.getMessage();
        } catch (IOException e) {
            log.error("Error: ", e);
            return e.getMessage();
        }
    }

    public String removeRoute(int routeNumber) {
        try {
            routeDao.getRoute(routeNumber);
            routeDao.removeRoute(routeNumber);
            return "Success";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String getRoute(int routeNumber) {
        try {
            Route route = routeDao.getRoute(routeNumber);
            return new ObjectMapper().setDateFormat(df).writeValueAsString(route);
        } catch (SQLException e) {
            return e.getMessage();
        } catch (JsonProcessingException e) {
            log.error("Error: ", e);
            return e.getMessage();
        }
    }
}
