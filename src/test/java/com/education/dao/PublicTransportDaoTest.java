package com.education.dao;

import com.education.entity.PublicTransport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PublicTransportDaoTest {

    @Autowired
    private PublicTransportDao publicTransportDao;
    private PublicTransport transport = new PublicTransport();

    @BeforeEach
    void addPublicTransport() throws SQLException {
        transport.setTransportNumber(1);
        transport.setIssueYear(1950);
        transport.setCapacity(60);
        transport.setBusBrand("Ikarus");
        publicTransportDao.addPublicTransport(transport);
    }

    @Test
    void getPublicTransport() {
        assertEquals(transport, publicTransportDao.getPublicTransport(transport.getTransportNumber()));
    }

    @Test
    void addTransportWithDefaultValues() throws SQLException {
        PublicTransport defaultTransport = new PublicTransport();
        defaultTransport.setTransportNumber(10);
        publicTransportDao.addPublicTransport(defaultTransport);
        assertEquals(defaultTransport, publicTransportDao.getPublicTransport(defaultTransport.getTransportNumber()));
    }

    @Test
    void updatePublicTransport() {
        assertEquals(transport, publicTransportDao.getPublicTransport(transport.getTransportNumber()));
        transport.setCapacity(99);
        publicTransportDao.updatePublicTransport(transport);
        assertEquals(transport, publicTransportDao.getPublicTransport(transport.getTransportNumber()));
    }

    @Test
    void removePublicTransport() {
        assertEquals(transport, publicTransportDao.getPublicTransport(transport.getTransportNumber()));
        publicTransportDao.removePublicTransport(transport.getTransportNumber());
        assertThrows(Exception.class, () -> publicTransportDao.getPublicTransport(transport.getTransportNumber()));
    }

    @AfterEach
    void remove() {
        publicTransportDao.removePublicTransport(transport.getTransportNumber());
        publicTransportDao.removePublicTransport(10);
    }
}