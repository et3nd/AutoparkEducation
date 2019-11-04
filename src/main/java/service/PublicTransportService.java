package service;

import dao.PublicTransportDao;
import entity.PublicTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class PublicTransportService {
    private static final Logger log = LoggerFactory.getLogger(PublicTransportService.class);
    private PublicTransportDao publicTransportDao = new PublicTransportDao();

    void setPublicTransportDao(PublicTransportDao publicTransportDao) {
        this.publicTransportDao = publicTransportDao;
    }

    public void addPublicTransport(PublicTransport transport) throws SQLException {
        try {
            publicTransportDao.addPublicTransport(transport);
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }

    public void updatePublicTransport(PublicTransport inputTransport) throws SQLException {
        try {
            PublicTransport outputTransport = publicTransportDao.getPublicTransport(inputTransport.getTransportNumber());
            if (inputTransport.equals(outputTransport)) throw new SQLException("Same values");
            if (!(inputTransport.getBusBrand().equals("default")))
                outputTransport.setBusBrand(inputTransport.getBusBrand());
            if (!(inputTransport.getCapacity() == 0)) outputTransport.setCapacity(inputTransport.getCapacity());
            if (!(inputTransport.getIssueYear() == 0)) outputTransport.setIssueYear(inputTransport.getIssueYear());
            publicTransportDao.updatePublicTransport(outputTransport);
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }

    public void removePublicTransport(int transportNumber) throws SQLException {
        try {
            publicTransportDao.getPublicTransport(transportNumber);
            publicTransportDao.removePublicTransport(transportNumber);
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }

    public PublicTransport getPublicTransport(int transportNumber) throws SQLException {
        try {
            return publicTransportDao.getPublicTransport(transportNumber);
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }
}
