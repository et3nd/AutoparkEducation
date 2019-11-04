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
    void getPublicTransport() throws SQLException {
        assertEquals(transport, publicTransportDao.getPublicTransport(transport.getTransportNumber()));
        assertThrows(SQLException.class, () -> publicTransportDao.getPublicTransport(2));
    }

    @Test
    void addTransportWithDefaultValues() throws SQLException {
        PublicTransport defaultTransport = new PublicTransport();
        defaultTransport.setTransportNumber(10);
        publicTransportDao.addPublicTransport(defaultTransport);
        assertEquals(defaultTransport, publicTransportDao.getPublicTransport(defaultTransport.getTransportNumber()));
    }

    @Test
    void addTransportWithUsedValue() throws SQLException {
        assertEquals(transport, publicTransportDao.getPublicTransport(transport.getTransportNumber()));
        assertThrows(SQLException.class, () -> publicTransportDao.addPublicTransport(transport));
    }

    @Test
    void updatePublicTransport() throws SQLException {
        assertEquals(transport, publicTransportDao.getPublicTransport(transport.getTransportNumber()));
        transport.setCapacity(99);
        publicTransportDao.updatePublicTransport(transport);
        assertEquals(transport, publicTransportDao.getPublicTransport(transport.getTransportNumber()));
    }

    @Test
    void removePublicTransport() throws SQLException {
        assertEquals(transport, publicTransportDao.getPublicTransport(transport.getTransportNumber()));
        publicTransportDao.removePublicTransport(transport.getTransportNumber());
        assertThrows(SQLException.class, () -> publicTransportDao.getPublicTransport(transport.getTransportNumber()));
    }

    @AfterEach
    void remove() throws SQLException {
        publicTransportDao.removePublicTransport(transport.getTransportNumber());
        publicTransportDao.removePublicTransport(10);
    }
}