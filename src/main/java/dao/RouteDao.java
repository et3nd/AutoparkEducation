package dao;

import entity.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class RouteDao extends EntityDao {
    private static final Logger log = LoggerFactory.getLogger(RouteDao.class);
    private static final String ADD_ROUTE_SCRIPT = "/db/add-route-script.sql";
    private static final String UPDATE_ROUTE_SCRIPT = "/db/update-route-script.sql";
    private static final String REMOVE_ROUTE_SCRIPT = "/db/remove-route-script.sql";
    private static final String GET_ROUTE_SCRIPT = "/db/get-route-script.sql";

    public void addRoute(Route route) throws SQLException {
        String script = getInitializationScript(RouteDao.class.getResourceAsStream(ADD_ROUTE_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script)) {
            log.info("Connection to the database was successful");
            if (route.getRouteNumber() == 0) throw new SQLException("Zero identifier");
            preparedStatement.setInt(1, route.getRouteNumber());
            preparedStatement.setString(2, route.getStartStation());
            preparedStatement.setString(3, route.getEndStation());
            preparedStatement.setString(4, route.getStops());
            preparedStatement.setInt(5, route.getDistance());
            preparedStatement.execute();
            log.info("Route add was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }

    public void updateRoute(Route route) throws SQLException {
        String script = getInitializationScript(RouteDao.class.getResourceAsStream(UPDATE_ROUTE_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(5, route.getRouteNumber());
            preparedStatement.setString(1, route.getStartStation());
            preparedStatement.setString(2, route.getEndStation());
            preparedStatement.setString(3, route.getStops());
            preparedStatement.setInt(4, route.getDistance());
            preparedStatement.execute();
            log.info("Route update was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }

    public void removeRoute(int routeNumber) throws SQLException {
        String script = getInitializationScript(RouteDao.class.getResourceAsStream(REMOVE_ROUTE_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, routeNumber);
            preparedStatement.execute();
            log.info("Route remove was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }

    public Route getRoute(int routeNumber) throws SQLException {
        Route route = new Route();
        String script = getInitializationScript(RouteDao.class.getResourceAsStream(GET_ROUTE_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, routeNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    route.setRouteNumber(resultSet.getInt("route_number"));
                    route.setStartStation(resultSet.getString("start_station"));
                    route.setEndStation(resultSet.getString("end_station"));
                    route.setStops(resultSet.getString("stops"));
                    route.setDistance(resultSet.getInt("distance"));
                    log.info("Read: \n" + route);
                }
                log.info("Route read was successful");
            }
            if (route.getRouteNumber() == 0) throw new SQLException("Route does not exist");
            return route;
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }
}
