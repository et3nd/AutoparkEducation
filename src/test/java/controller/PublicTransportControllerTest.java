package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.PublicTransport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.PublicTransportService;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublicTransportControllerTest {
    private PublicTransportController transportController = new PublicTransportController();
    private PublicTransport transport = new PublicTransport();

    @Mock
    private PublicTransportService transportService;

    @BeforeEach
    void setPublicTransportService() {
        transportController.setTransportService(transportService);
        transport.setTransportNumber(1);
    }

    @Test
    void getPublicTransport() throws SQLException {
        doReturn(transport).when(transportService).getPublicTransport(transport.getTransportNumber());
        transportController.getPublicTransport(transport.getTransportNumber());
        verify(transportService).getPublicTransport(transport.getTransportNumber());
    }

    @Test
    void getTransportWithException() throws SQLException {
        doThrow(SQLException.class).when(transportService).getPublicTransport(transport.getTransportNumber());
        transportController.getPublicTransport(transport.getTransportNumber());
        verify(transportService).getPublicTransport(transport.getTransportNumber());
    }

    @Test
    void removePublicTransport() throws SQLException {
        doNothing().when(transportService).removePublicTransport(transport.getTransportNumber());
        transportController.removePublicTransport(transport.getTransportNumber());
        verify(transportService).removePublicTransport(transport.getTransportNumber());
    }

    @Test
    void removeTransportWithException() throws SQLException {
        doThrow(SQLException.class).when(transportService).removePublicTransport(transport.getTransportNumber());
        transportController.removePublicTransport(transport.getTransportNumber());
        verify(transportService).removePublicTransport(transport.getTransportNumber());
    }

    @Test
    void addPublicTransport() throws JsonProcessingException, SQLException {
        doNothing().when(transportService).addPublicTransport(transport);
        transportController.addPublicTransport(new ObjectMapper().writeValueAsString(transport));
        verify(transportService).addPublicTransport(transport);
    }

    @Test
    void addTransportWithException() throws SQLException, JsonProcessingException {
        doThrow(SQLException.class).when(transportService).addPublicTransport(transport);
        transportController.addPublicTransport(new ObjectMapper().writeValueAsString(transport));
        verify(transportService).addPublicTransport(transport);
    }

    @Test
    void updatePublicTransport() throws JsonProcessingException, SQLException {
        doNothing().when(transportService).updatePublicTransport(transport);
        transportController.updatePublicTransport(new ObjectMapper().writeValueAsString(transport));
        verify(transportService).updatePublicTransport(transport);
    }

    @Test
    void updateTransportWithException() throws JsonProcessingException, SQLException {
        doThrow(new SQLException("Same values")).when(transportService).updatePublicTransport(transport);
        transportController.updatePublicTransport(new ObjectMapper().writeValueAsString(transport));
        verify(transportService).updatePublicTransport(transport);
    }
}