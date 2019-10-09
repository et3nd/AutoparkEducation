package dao;

import entity.PublicTransport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class PublicTransportDaoTest {
    private PublicTransportDao publicTransportDao = new PublicTransportDao();
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
        assertEquals(new PublicTransport(), publicTransportDao.getPublicTransport(2));
    }

    @Test
    void addTransportWithDefaultValues() throws SQLException {
        PublicTransport defaultTransport = new PublicTransport();
        publicTransportDao.addPublicTransport(defaultTransport);
        assertEquals(defaultTransport, publicTransportDao.getPublicTransport(0));
    }

    @Test
    void addTransportWithUsedValue() {
        assertEquals(transport, publicTransportDao.getPublicTransport(transport.getTransportNumber()));
        assertThrows(SQLException.class, () -> publicTransportDao.addPublicTransport(transport));
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
        assertNotEquals(transport, publicTransportDao.getPublicTransport(transport.getTransportNumber()));
    }

    @AfterEach
    void remove() {
        publicTransportDao.removePublicTransport(transport.getTransportNumber());
        publicTransportDao.removePublicTransport(0);
    }
}