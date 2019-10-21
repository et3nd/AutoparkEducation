package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.PublicTransportDao;
import entity.PublicTransport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublicTransportServiceTest {
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private PublicTransportService publicTransportService = new PublicTransportService();
    private PublicTransport transport = new PublicTransport();

    @Mock
    private PublicTransportDao publicTransportDao;

    @BeforeEach
    void setPublicTransportDao() {
        publicTransportService.setPublicTransportDao(publicTransportDao);
        transport.setTransportNumber(1);
    }

    @Test
    void getPublicTransport() throws JsonProcessingException, SQLException {
        doReturn(transport).when(publicTransportDao).getPublicTransport(transport.getTransportNumber());
        assertEquals(new ObjectMapper().setDateFormat(df)
                .writeValueAsString(transport), publicTransportService.getPublicTransport(transport.getTransportNumber()));
        verify(publicTransportDao).getPublicTransport(transport.getTransportNumber());
    }

    @Test
    void addPublicTransport() throws SQLException, JsonProcessingException {
        doNothing().when(publicTransportDao).addPublicTransport(transport);
        publicTransportService.addPublicTransport(new ObjectMapper().setDateFormat(df).writeValueAsString(transport));
        verify(publicTransportDao).addPublicTransport(transport);
    }

    @Test
    void addTransportWithException() throws SQLException, JsonProcessingException {
        doThrow(SQLException.class).when(publicTransportDao).addPublicTransport(transport);
        publicTransportService.addPublicTransport(new ObjectMapper().setDateFormat(df).writeValueAsString(transport));
        verify(publicTransportDao).addPublicTransport(transport);
    }

    @Test
    void updatePublicTransport() throws SQLException, JsonProcessingException {
        PublicTransport outputTransport = new PublicTransport();
        outputTransport.setTransportNumber(1);
        outputTransport.setIssueYear(2000);
        doNothing().when(publicTransportDao).updatePublicTransport(outputTransport);
        doReturn(transport).when(publicTransportDao).getPublicTransport(transport.getTransportNumber());
        publicTransportService.updatePublicTransport(new ObjectMapper().setDateFormat(df).writeValueAsString(outputTransport));
        verify(publicTransportDao).updatePublicTransport(outputTransport);
    }

    @Test
    void removePublicTransport() throws SQLException {
        doNothing().when(publicTransportDao).removePublicTransport(transport.getTransportNumber());
        publicTransportService.removePublicTransport(transport.getTransportNumber());
        verify(publicTransportDao).removePublicTransport(transport.getTransportNumber());
    }
}