package service;

import dao.PublicTransportDao;
import entity.PublicTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class PublicTransportService {
    private static final Logger log = LoggerFactory.getLogger(PublicTransportService.class);
    private PublicTransportDao publicTransportDao;

    public void setPublicTransportDao(PublicTransportDao publicTransportDao) {
        this.publicTransportDao = publicTransportDao;
    }

    public void addPublicTransport(PublicTransport transport) {
        try {
            publicTransportDao.addPublicTransport(transport);
        } catch (SQLException e) {
            log.error("This value of transport number is used");
        }
    }

    public void updatePublicTransport(PublicTransport transport) {
        publicTransportDao.updatePublicTransport(transport);
    }

    public void removePublicTransport(int id) {
        publicTransportDao.removePublicTransport(id);
    }

    public PublicTransport getPublicTransport(int id) {
        return publicTransportDao.getPublicTransport(id);
    }
}
