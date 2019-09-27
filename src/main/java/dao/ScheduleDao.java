package dao;

import entity.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class ScheduleDao extends DAO {
    private static final Logger log = LoggerFactory.getLogger(ScheduleDao.class);

    public void addSchedule(Schedule schedule) {
        final String script = "/db/add-schedule-script.sql";
        String addScript = getInitializationScript(ScheduleDao.class.getResourceAsStream(script));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(addScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, schedule.getId());
            preparedStatement.setTime(2, schedule.getDepartureTime());
            preparedStatement.setTime(3, schedule.getArrivalTime());
            preparedStatement.execute();
            log.info("Schedule add was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public void updateSchedule(Schedule schedule) {
        final String script = "/db/update-schedule-script.sql";
        String updateScript = getInitializationScript(ScheduleDao.class.getResourceAsStream(script));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setTime(1, schedule.getArrivalTime());
            preparedStatement.execute();
            log.info("Schedule update was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public void removeSchedule(int id) {
        final String script = "/db/remove-schedule-script.sql";
        String updateScript = getInitializationScript(ScheduleDao.class.getResourceAsStream(script));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            log.info("Schedule remove was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public void getSchedule(int id) {
        final String script = "/db/get-schedule-script.sql";
        String updateScript = getInitializationScript(ScheduleDao.class.getResourceAsStream(script));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
            }
            log.info("Schedule read was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }
}
