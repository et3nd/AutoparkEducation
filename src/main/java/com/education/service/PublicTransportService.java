package com.education.service;

import com.education.dao.PublicTransportDao;
import com.education.entity.PublicTransport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@Slf4j
public class PublicTransportService {
    private final PublicTransportDao publicTransportDao;

    @Autowired
    public PublicTransportService(PublicTransportDao publicTransportDao) {
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

    public void updatePublicTransport(PublicTransport inputTransport) {
        PublicTransport outputTransport = publicTransportDao.getPublicTransport(inputTransport.getTransportNumber());
        if (!(inputTransport.getBusBrand().equals("default")))
            outputTransport.setBusBrand(inputTransport.getBusBrand());
        if (!(inputTransport.getCapacity() == 0)) outputTransport.setCapacity(inputTransport.getCapacity());
        if (!(inputTransport.getIssueYear() == 0)) outputTransport.setIssueYear(inputTransport.getIssueYear());
        publicTransportDao.updatePublicTransport(outputTransport);
    }

    public void removePublicTransport(int transportNumber) {
        publicTransportDao.getPublicTransport(transportNumber);
        publicTransportDao.removePublicTransport(transportNumber);
    }

    public PublicTransport getPublicTransport(int transportNumber) {
        return publicTransportDao.getPublicTransport(transportNumber);
    }
}
