package dao;

import entity.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DriverDao extends EntityDao {
    private static final Logger log = LoggerFactory.getLogger(DriverDao.class);
    private static final String ADD_DRIVER_SCRIPT = "/db/add-driver-script.sql";
    private static final String UPDATE_DRIVER_SCRIPT = "/db/update-driver-script.sql";
    private static final String REMOVE_DRIVER_SCRIPT = "/db/remove-driver-script.sql";
    private static final String GET_DRIVER_SCRIPT = "/db/get-driver-script.sql";

    public void addDriver(Driver driver) throws SQLException {
        String script = getInitializationScript(DriverDao.class.getResourceAsStream(ADD_DRIVER_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script)) {
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
            throw new SQLException();
        }
    }

    public void updateDriver(Driver driver) {
        String script = getInitializationScript(DriverDao.class.getResourceAsStream(UPDATE_DRIVER_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(5, driver.getLicense());
            preparedStatement.setString(1, driver.getFio());
            preparedStatement.setInt(2, driver.getSalary());
            preparedStatement.setString(3, driver.getAddress());
            preparedStatement.setDate(4, driver.getBirthDate());
            preparedStatement.execute();
            log.info("Driver update was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public void removeDriver(int license) {
        String script = getInitializationScript(DriverDao.class.getResourceAsStream(REMOVE_DRIVER_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, license);
            preparedStatement.execute();
            log.info("Driver remove was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public Driver getDriver(int license) {
        Driver driver = new Driver();
        String script = getInitializationScript(DriverDao.class.getResourceAsStream(GET_DRIVER_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, license);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    driver.setAddress(resultSet.getString("address"));
                    driver.setFio(resultSet.getString("fio"));
                    driver.setSalary(resultSet.getInt("salary"));
                    driver.setLicense(resultSet.getInt("license"));
                    driver.setBirthDate(Date.valueOf(String.valueOf(resultSet.getDate("birth_date"))));
                    log.info("Read: \n" + driver.toString());
                }
                log.info("Driver read was successful");
            }
            if (driver.getLicense() == 0)
                throw new SQLException("Not found");
            return driver;
        } catch (SQLException e) {
            log.error("Error: ", e);
            return null;
        }
    }
}
