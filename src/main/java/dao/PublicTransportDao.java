package dao;

import entity.PublicTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class PublicTransportDao extends EntityDao {
    private static final Logger log = LoggerFactory.getLogger(PublicTransportDao.class);
    private static final String ADD_TRANSPORT_SCRIPT = "/db/add-transport-script.sql";
    private static final String UPDATE_TRANSPORT_SCRIPT = "/db/update-transport-script.sql";
    private static final String REMOVE_TRANSPORT_SCRIPT = "/db/remove-transport-script.sql";
    private static final String GET_TRANSPORT_SCRIPT = "/db/get-transport-script.sql";

    public void addPublicTransport(PublicTransport transport) {
        String script = getInitializationScript(PublicTransportDao.class.getResourceAsStream(ADD_TRANSPORT_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script)) {
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
        String script = getInitializationScript(PublicTransportDao.class.getResourceAsStream(UPDATE_TRANSPORT_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, transport.getCapacity());
            preparedStatement.execute();
            log.info("Transport update was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public void removePublicTransport(int id) {
        String script = getInitializationScript(PublicTransportDao.class.getResourceAsStream(REMOVE_TRANSPORT_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            log.info("Transport remove was successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    public PublicTransport getPublicTransport(int id) {
        PublicTransport transport = new PublicTransport();
        String script = getInitializationScript(PublicTransportDao.class.getResourceAsStream(GET_TRANSPORT_SCRIPT));
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script)) {
            log.info("Connection to the database was successful");
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    transport.setTransportNumber(resultSet.getInt("transport_number"));
                    transport.setBusBrand(resultSet.getString("bus_brand"));
                    transport.setCapacity(resultSet.getInt("capacity"));
                    transport.setIssueYear(resultSet.getInt("issue_year"));
                    log.info("Read: " + transport);
                }
                log.info("Transport read was successful");
            }
            return transport;
        } catch (SQLException e) {
            log.error("Error: ", e);
            return transport;
        }
    }
}
