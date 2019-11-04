package dao;

import entity.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class ScheduleDao extends EntityDao {
    private static final Logger log = LoggerFactory.getLogger(ScheduleDao.class);
    private static final String ADD_SCHEDULE_SCRIPT = "/db/add-schedule-script.sql";
    private static final String UPDATE_SCHEDULE_SCRIPT = "/db/update-schedule-script.sql";
    private static final String REMOVE_SCHEDULE_SCRIPT = "/db/remove-schedule-script.sql";
    private static final String GET_SCHEDULE_SCRIPT = "/db/get-schedule-script.sql";

    public void addSchedule(Schedule schedule) throws SQLException {
        String script = getInitializationScript(ScheduleDao.class.getResourceAsStream(ADD_SCHEDULE_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script)) {
            log.info("Connection to the database was successful");
            if (schedule.getId() == 0) throw new SQLException("Zero identifier");
            preparedStatement.setInt(1, schedule.getId());
            preparedStatement.setTime(2, schedule.getDepartureTime());
            preparedStatement.setTime(3, schedule.getArrivalTime());
            preparedStatement.execute();
            log.info("Schedule add was successful");
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void updateSchedule(Schedule schedule) throws SQLException {
        String script = getInitializationScript(ScheduleDao.class.getResourceAsStream(UPDATE_SCHEDULE_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(3, schedule.getId());
            preparedStatement.setTime(1, schedule.getDepartureTime());
            preparedStatement.setTime(2, schedule.getArrivalTime());
            preparedStatement.execute();
            log.info("Schedule update was successful");
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void removeSchedule(int id) throws SQLException {
        String script = getInitializationScript(ScheduleDao.class.getResourceAsStream(REMOVE_SCHEDULE_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            log.info("Schedule remove was successful");
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public Schedule getSchedule(int id) throws SQLException {
        Schedule schedule = new Schedule();
        String script = getInitializationScript(ScheduleDao.class.getResourceAsStream(GET_SCHEDULE_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    schedule.setId(resultSet.getInt("id"));
                    schedule.setDepartureTime(Time.valueOf(String.valueOf(resultSet.getTime("departure_time"))));
                    schedule.setArrivalTime(Time.valueOf(String.valueOf(resultSet.getTime("arrival_time"))));
                    log.info("Read: \n" + schedule);
                }
                log.info("Schedule read was successful");
            }
            if (schedule.getId() == 0) throw new SQLException("Schedule does not exist");
            return schedule;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
