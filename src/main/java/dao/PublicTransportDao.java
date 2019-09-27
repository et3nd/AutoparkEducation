package dao;

import entity.PublicTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class PublicTransportDao extends DAO {
    private static final Logger log = LoggerFactory.getLogger(PublicTransportDao.class);

    public void addPublicTransport(PublicTransport transport) {
        final String script = "/db/add-transport-script.sql";
        String addScript = getInitializationScript(PublicTransportDao.class.getResourceAsStream(script));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(addScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, transport.getTransportNumber());
            preparedStatement.setString(2, transport.getBusBrand());
            preparedStatement.setInt(3, transport.getCapacity());
            preparedStatement.setInt(4, transport.getIssueYear());
            preparedStatement.execute();
            log.info("Transport add was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public void updatePublicTransport(PublicTransport transport) {
        final String script = "/db/update-transport-script.sql";
        String updateScript = getInitializationScript(PublicTransportDao.class.getResourceAsStream(script));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, transport.getCapacity());
            preparedStatement.execute();
            log.info("Transport update was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public void removePublicTransport(int id) {
        final String script = "/db/remove-transport-script.sql";
        String updateScript = getInitializationScript(PublicTransportDao.class.getResourceAsStream(script));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            log.info("Transport remove was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public void getPublicTransport(int id) {
        final String script = "/db/get-transport-script.sql";
        String updateScript = getInitializationScript(PublicTransportDao.class.getResourceAsStream(script));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("bus_brand"));
            }
            log.info("Transport read was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }
}
