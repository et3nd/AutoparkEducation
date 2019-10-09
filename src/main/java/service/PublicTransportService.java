package service;

import dao.PublicTransportDao;
import entity.PublicTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

class PublicTransportService {
    private static final Logger log = LoggerFactory.getLogger(PublicTransportService.class);
    private PublicTransportDao publicTransportDao;

    void setPublicTransportDao(PublicTransportDao publicTransportDao) {
        this.publicTransportDao = publicTransportDao;
    }

    void addPublicTransport(PublicTransport transport) {
        try {
            publicTransportDao.addPublicTransport(transport);
        } catch (SQLException e) {
            log.error("This value of transport number is used");
        }
    }

    void updatePublicTransport(PublicTransport transport) {
        publicTransportDao.updatePublicTransport(transport);
    }

    void removePublicTransport(int transportNumber) {
        publicTransportDao.removePublicTransport(transportNumber);
    }

    PublicTransport getPublicTransport(int transportNumber) {
        return publicTransportDao.getPublicTransport(transportNumber);
    }
}
