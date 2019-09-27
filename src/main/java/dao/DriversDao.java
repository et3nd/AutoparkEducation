package dao;

import entity.Drivers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DriversDao extends DAO {
    private static final Logger log = LoggerFactory.getLogger(DriversDao.class);

    public void addDriver(Drivers driver) {
        final String script = "/db/add-driver-script.sql";
        String addScript = getInitializationScript(DriversDao.class.getResourceAsStream(script));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(addScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, driver.getLicense());
            preparedStatement.setString(2, driver.getFio());
            preparedStatement.setInt(3, driver.getSalary());
            preparedStatement.setString(4, driver.getAddress());
            preparedStatement.setString(5, driver.getBirthDate());
            preparedStatement.execute();
            log.info("Successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public void updateDriver(Drivers driver) {
        final String script = "/db/update-driver-script.sql";
        String updateScript = getInitializationScript(DriversDao.class.getResourceAsStream(script));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setString(1, driver.getFio());
            preparedStatement.execute();
            log.info("Successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public void removeDriver(int id) {
        final String script = "/db/remove-driver-script.sql";
        String updateScript = getInitializationScript(DriversDao.class.getResourceAsStream(script));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            log.info("Successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public void getDriver(int id) {
        final String script = "/db/get-driver-script.sql";
        String updateScript = getInitializationScript(DriversDao.class.getResourceAsStream(script));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("address"));
            }
            log.info("Successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }
}
