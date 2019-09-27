package dao;

import entity.Routes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class RoutesDao extends DAO {
    private static final Logger log = LoggerFactory.getLogger(RoutesDao.class);

    public void addRoute(Routes route) {
        final String script = "/db/add-route-script.sql";
        String addScript = getInitializationScript(RoutesDao.class.getResourceAsStream(script));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(addScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, route.getRouteNumber());
            preparedStatement.setString(2, route.getStartStation());
            preparedStatement.setString(3, route.getEndStation());
            preparedStatement.setString(4, route.getStops());
            preparedStatement.setInt(5, route.getDistance());
            preparedStatement.execute();
            log.info("Route add was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public void updateRoute(Routes route) {
        final String script = "/db/update-route-script.sql";
        String updateScript = getInitializationScript(RoutesDao.class.getResourceAsStream(script));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setString(1, route.getStops());
            preparedStatement.execute();
            log.info("Route update was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public void removeRoute(int id) {
        final String script = "/db/remove-route-script.sql";
        String updateScript = getInitializationScript(RoutesDao.class.getResourceAsStream(script));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            log.info("Route remove was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public void getRoute(int id) {
        final String script = "/db/get-route-script.sql";
        String updateScript = getInitializationScript(RoutesDao.class.getResourceAsStream(script));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("stops"));
            }
            log.info("Route read was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }
}
