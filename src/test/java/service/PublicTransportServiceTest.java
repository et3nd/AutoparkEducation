package service;

import dao.PublicTransportDao;
import entity.PublicTransport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = PublicTransportService.class)
class PublicTransportServiceTest {

    @Autowired
    private PublicTransportService publicTransportService;
    private PublicTransport transport = new PublicTransport();

    @MockBean
    private PublicTransportDao publicTransportDao;

    @BeforeEach
    void setTransportNumber() {
        transport.setTransportNumber(1);
    }

    @Test
    void getPublicTransport() {
        doReturn(transport).when(publicTransportDao).getPublicTransport(transport.getTransportNumber());
        assertEquals(transport, publicTransportService.getPublicTransport(transport.getTransportNumber()));
        verify(publicTransportDao).getPublicTransport(transport.getTransportNumber());
    }

    @Test
    void addPublicTransport() throws SQLException {
        doNothing().when(publicTransportDao).addPublicTransport(transport);
        publicTransportService.addPublicTransport(transport);
        verify(publicTransportDao).addPublicTransport(transport);
    }

    @Test
    void addTransportWithException() throws SQLException {
        doThrow(SQLException.class).when(publicTransportDao).addPublicTransport(transport);
        assertThrows(SQLException.class, () -> publicTransportService.addPublicTransport(transport));
        verify(publicTransportDao).addPublicTransport(transport);
    }

    @Test
    void updatePublicTransport() {
        PublicTransport outputTransport = new PublicTransport();
        outputTransport.setTransportNumber(1);
        outputTransport.setIssueYear(2000);
        doNothing().when(publicTransportDao).updatePublicTransport(outputTransport);
        doReturn(transport).when(publicTransportDao).getPublicTransport(transport.getTransportNumber());
        publicTransportService.updatePublicTransport(outputTransport);
        verify(publicTransportDao).updatePublicTransport(outputTransport);
    }

    @Test
    void removePublicTransport() {
        doNothing().when(publicTransportDao).removePublicTransport(transport.getTransportNumber());
        publicTransportService.removePublicTransport(transport.getTransportNumber());
        verify(publicTransportDao).removePublicTransport(transport.getTransportNumber());
    }
}