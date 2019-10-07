package dao;

import entity.PublicTransport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertEquals(transport, publicTransportDao.getPublicTransport(1));
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
        assertThrows(SQLException.class, () -> publicTransportDao.addPublicTransport(transport));
    }

    @Test
    void updatePublicTransport() {
        transport.setCapacity(99);
        publicTransportDao.updatePublicTransport(transport);
        assertEquals(transport, publicTransportDao.getPublicTransport(1));
    }

    @Test
    void removePublicTransport() {
        publicTransportDao.removePublicTransport(1);
    }

    @AfterEach
    void remove() {
        publicTransportDao.removePublicTransport(1);
        publicTransportDao.removePublicTransport(0);
    }
}