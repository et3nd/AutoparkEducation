package dao;

import entity.Drivers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DriversDao extends EntityDao {
    private static final Logger log = LoggerFactory.getLogger(DriversDao.class);
    private static final String ADD_DRIVER_SCRIPT = "/db/add-driver-script.sql";
    private static final String UPDATE_DRIVER_SCRIPT = "/db/update-driver-script.sql";
    private static final String REMOVE_DRIVER_SCRIPT = "/db/remove-driver-script.sql";
    private static final String GET_DRIVER_SCRIPT = "/db/get-driver-script.sql";

    public void addDriver(Drivers driver) {
        String addScript = getInitializationScript(DriversDao.class.getResourceAsStream(ADD_DRIVER_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(addScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, driver.getLicense());
            preparedStatement.setString(2, driver.getFio());
            preparedStatement.setInt(3, driver.getSalary());
            preparedStatement.setString(4, driver.getAddress());
            preparedStatement.setDate(5, driver.getBirthDate());
            preparedStatement.execute();
            log.info("Driver add was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public void updateDriver(Drivers driver) {
        String updateScript = getInitializationScript(DriversDao.class.getResourceAsStream(UPDATE_DRIVER_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setString(1, driver.getFio());
            preparedStatement.execute();
            log.info("Driver update was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public void removeDriver(int id) {
        String updateScript = getInitializationScript(DriversDao.class.getResourceAsStream(REMOVE_DRIVER_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            log.info("Driver remove was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public Drivers getDriver(int id) {
        Drivers driver = new Drivers();
        String updateScript = getInitializationScript(DriversDao.class.getResourceAsStream(GET_DRIVER_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    driver.setAddress(resultSet.getString("address"));
                    driver.setFio(resultSet.getString("fio"));
                    driver.setSalary(resultSet.getInt("salary"));
                    driver.setLicense(resultSet.getInt("license"));
                    driver.setBirthDate(Date.valueOf(String.valueOf(resultSet.getDate("birth_date"))));
                    log.info("Read: " + driver.toString());
                }
                log.info("Driver read was successful");
            }
            return driver;
        } catch (SQLException e) {
            log.error("Error: ", e);
            return driver;
        }
    }
}
