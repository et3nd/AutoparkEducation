package dao;

import entity.PublicTransport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PublicTransportDaoTest {
    private PublicTransportDao publicTransportDao = new PublicTransportDao();

    @Test
    void publicTransportTest() {
        Executable testInsert = () -> {
            PublicTransport transport = new PublicTransport();
            publicTransportDao.addPublicTransport(transport);
            assertEquals(transport.toString(), publicTransportDao.getPublicTransport(0).toString());
            transport.setTransportNumber(1);
            publicTransportDao.addPublicTransport(transport);
            assertEquals(transport.toString(), publicTransportDao.getPublicTransport(1).toString());
            publicTransportDao.addPublicTransport(transport);
        };
        assertThrows(SQLException.class, testInsert);
    }

    @AfterEach
    void remove() {
        publicTransportDao.removePublicTransport(1);
        publicTransportDao.removePublicTransport(0);
    }
}