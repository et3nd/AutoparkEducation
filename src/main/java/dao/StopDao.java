package dao;

import entity.Stop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class StopDao extends EntityDao {
    private static final Logger log = LoggerFactory.getLogger(StopDao.class);
    private static final String ADD_STOP_SCRIPT = "/db/add-stop-script.sql";
    private static final String UPDATE_STOP_SCRIPT = "/db/update-stop-script.sql";
    private static final String REMOVE_STOP_SCRIPT = "/db/remove-stop-script.sql";
    private static final String GET_STOP_SCRIPT = "/db/get-stop-script.sql";

    public void addStop(Stop stop) throws SQLException {
        String script = getInitializationScript(StopDao.class.getResourceAsStream(ADD_STOP_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script)) {
            log.info("Connection to the database was successful");
            preparedStatement.setString(1, stop.getStopName());
            preparedStatement.setString(2, stop.getDirection());
            preparedStatement.setTime(3, stop.getArrivalTimeOnStop());
            preparedStatement.execute();
            log.info("Stop add was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException();
        }
    }

    public void updateStop(Stop stop) {
        String script = getInitializationScript(StopDao.class.getResourceAsStream(UPDATE_STOP_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script)) {
            log.info("Connection to the database was successful");
            preparedStatement.setString(1, stop.getDirection());
            preparedStatement.execute();
            log.info("Stop update was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public void removeStop(String stopName) {
        String script = getInitializationScript(StopDao.class.getResourceAsStream(REMOVE_STOP_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script)) {
            log.info("Connection to the database was successful");
            preparedStatement.setString(1, stopName);
            preparedStatement.execute();
            log.info("Stop remove was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public Stop getStop(String stopName) {
        Stop stop = new Stop();
        String script = getInitializationScript(StopDao.class.getResourceAsStream(GET_STOP_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script)) {
            log.info("Connection to the database was successful");
            preparedStatement.setString(1, stopName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    stop.setStopName(resultSet.getString("stop_name"));
                    stop.setDirection(resultSet.getString("direction"));
                    stop.setArrivalTimeOnStop(Time.valueOf(String.valueOf(resultSet.getTime("arrival_time_on_stop"))));
                    log.info("Read: " + stop);
                }
                log.info("Stop read was successful");
            }
            if (stop.getStopName().equals("default"))
                throw new SQLException("Default stop");
            return stop;
        } catch (SQLException e) {
            log.error("Error: ", e);
            return null;
        }
    }
}
