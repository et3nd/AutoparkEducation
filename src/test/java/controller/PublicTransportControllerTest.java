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

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublicTransportControllerTest {
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private PublicTransportController transportController = new PublicTransportController();
    private PublicTransport transport = new PublicTransport();

    @Mock
    private PublicTransportService transportService;

    @BeforeEach
    void set() {
        transportController.setTransportService(transportService);
        transport.setTransportNumber(1);
    }

    @Test
    void getPublicTransport() throws JsonProcessingException {
        doReturn(transport).when(transportService).getPublicTransport(transport.getTransportNumber());
        assertEquals(new ObjectMapper().setDateFormat(df).writeValueAsString(transport),
                transportController.getPublicTransport(transport.getTransportNumber()));
        verify(transportService).getPublicTransport(transport.getTransportNumber());
    }

    @Test
    void removePublicTransport() {
        doNothing().when(transportService).removePublicTransport(transport.getTransportNumber());
        transportController.removePublicTransport(transport.getTransportNumber());
        verify(transportService).removePublicTransport(transport.getTransportNumber());
    }

    @Test
    void addPublicTransport() throws JsonProcessingException {
        doNothing().when(transportService).addPublicTransport(transport);
        doReturn(null, transport).when(transportService).getPublicTransport(transport.getTransportNumber());
        transportController.addPublicTransport(new ObjectMapper().setDateFormat(df).writeValueAsString(transport));
        verify(transportService).addPublicTransport(transport);
    }

    @Test
    void addTransportWithException() {
        transportController.addPublicTransport("Input");
    }

    @Test
    void updatePublicTransport() throws JsonProcessingException {
        doNothing().when(transportService).updatePublicTransport(transport);
        doReturn(transport).when(transportService).getPublicTransport(transport.getTransportNumber());
        transportController.updatePublicTransport(new ObjectMapper().setDateFormat(df).writeValueAsString(transport));
        verify(transportService).updatePublicTransport(transport);
    }

    @Test
    void updateTransportWithException() {
        transportController.updatePublicTransport("Input");
    }
}