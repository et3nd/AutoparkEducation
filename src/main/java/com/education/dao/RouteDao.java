package com.education.dao;

import com.education.entity.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class RouteDao extends EntityDao {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger log = LoggerFactory.getLogger(RouteDao.class);
    private static final String ADD_ROUTE_SCRIPT = "/db/add-route-script.sql";
    private static final String UPDATE_ROUTE_SCRIPT = "/db/update-route-script.sql";
    private static final String REMOVE_ROUTE_SCRIPT = "/db/remove-route-script.sql";
    private static final String GET_ROUTE_SCRIPT = "/db/get-route-script.sql";

    @Autowired
    public RouteDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addRoute(Route route) throws SQLException {
        String script = getInitializationScript(RouteDao.class.getResourceAsStream(ADD_ROUTE_SCRIPT));
        try {
            log.info("Connection to the database was successful");
            if (route.getRouteNumber() == 0) throw new SQLException("Zero identifier");
            jdbcTemplate.update(script,
                    route.getRouteNumber(),
                    route.getStartStation(),
                    route.getEndStation(),
                    route.getStops(),
                    route.getDistance());
            log.info("Route add was successful");
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void updateRoute(Route route) {
        String script = getInitializationScript(RouteDao.class.getResourceAsStream(UPDATE_ROUTE_SCRIPT));
        log.info("Connection to the database was successful");
        jdbcTemplate.update(script,
                route.getStartStation(),
                route.getEndStation(),
                route.getStops(),
                route.getDistance(),
                route.getRouteNumber());
        log.info("Route update was successful");
    }

    public void removeRoute(int routeNumber) {
        String script = getInitializationScript(RouteDao.class.getResourceAsStream(REMOVE_ROUTE_SCRIPT));
        log.info("Connection to the database was successful");
        jdbcTemplate.update(script, routeNumber);
        log.info("Route remove was successful");
    }

    public Route getRoute(int routeNumber) {
        String script = getInitializationScript(RouteDao.class.getResourceAsStream(GET_ROUTE_SCRIPT));
        log.info("Connection to the database was successful");
        Route route = jdbcTemplate.queryForObject(script,
                new Integer[]{routeNumber},
                new BeanPropertyRowMapper<>(Route.class));
        log.info("Read: \n" + route);
        return route;
    }
}
