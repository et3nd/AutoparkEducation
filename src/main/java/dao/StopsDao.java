package dao;

import entity.Stops;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class StopsDao extends DAO {
    private static final Logger log = LoggerFactory.getLogger(StopsDao.class);

    public void addStop(Stops stop) {
        final String script = "/db/add-stop-script.sql";
        String addScript = getInitializationScript(StopsDao.class.getResourceAsStream(script));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(addScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setString(1, stop.getStopName());
            preparedStatement.setString(2, stop.getDirection());
            preparedStatement.setTime(3, stop.getArrivalTimeOnStop());
            preparedStatement.execute();
            log.info("Stop add was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public void updateStop(Stops stop) {
        final String script = "/db/update-stop-script.sql";
        String updateScript = getInitializationScript(StopsDao.class.getResourceAsStream(script));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setString(1, stop.getDirection());
            preparedStatement.execute();
            log.info("Stop update was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public void removeStop(String id) {
        final String script = "/db/remove-stop-script.sql";
        String updateScript = getInitializationScript(StopsDao.class.getResourceAsStream(script));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            log.info("Stop remove was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public void getStop(String id) {
        final String script = "/db/get-stop-script.sql";
        String updateScript = getInitializationScript(StopsDao.class.getResourceAsStream(script));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("direction"));
            }
            log.info("Stop read was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }
}
