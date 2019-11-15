package controller;

import entity.PublicTransport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import service.PublicTransportService;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = PublicTransportController.class)
class PublicTransportControllerTest {

    @Autowired
    private PublicTransportController transportController;
    private PublicTransport transport = new PublicTransport();

    @MockBean
    private PublicTransportService transportService;

    @BeforeEach
    void setTransportNumber() {
        transport.setTransportNumber(1);
    }

    @Test
    void getPublicTransport() {
        doReturn(transport).when(transportService).getPublicTransport(transport.getTransportNumber());
        transportController.getPublicTransport(transport.getTransportNumber());
        verify(transportService).getPublicTransport(transport.getTransportNumber());
    }

    @Test
    void removePublicTransport() {
        doNothing().when(transportService).removePublicTransport(transport.getTransportNumber());
        transportController.removePublicTransport(transport.getTransportNumber());
        verify(transportService).removePublicTransport(transport.getTransportNumber());
    }

    @Test
    void addPublicTransport() throws SQLException {
        doNothing().when(transportService).addPublicTransport(transport);
        transportController.addPublicTransport(transport);
        verify(transportService).addPublicTransport(transport);
    }

    @Test
    void addTransportWithException() throws SQLException {
        doThrow(SQLException.class).when(transportService).addPublicTransport(transport);
        transportController.addPublicTransport(transport);
        verify(transportService).addPublicTransport(transport);
    }

    @Test
    void updatePublicTransport() {
        doNothing().when(transportService).updatePublicTransport(transport);
        transportController.updatePublicTransport(transport);
        verify(transportService).updatePublicTransport(transport);
    }
}