package service;

import dao.PublicTransportDao;
import entity.PublicTransport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublicTransportServiceTest {
    private PublicTransportService publicTransportService = new PublicTransportService();

    @Mock
    private PublicTransportDao publicTransportDao;

    @BeforeEach
    void setPublicTransportDao() {
        publicTransportService.setPublicTransportDao(publicTransportDao);
    }

    @Test
    void getPublicTransport() {
        doReturn(new PublicTransport()).when(publicTransportDao).getPublicTransport(0);
        assertEquals(new PublicTransport(), publicTransportService.getPublicTransport(0));
        verify(publicTransportDao).getPublicTransport(0);
    }

    @Test
    void addPublicTransport() throws SQLException {
        doNothing().when(publicTransportDao).addPublicTransport(new PublicTransport());
        publicTransportService.addPublicTransport(new PublicTransport());
        verify(publicTransportDao).addPublicTransport(new PublicTransport());
    }

    @Test
    void addTransportWithException() throws SQLException {
        doThrow(SQLException.class).when(publicTransportDao).addPublicTransport(new PublicTransport());
        publicTransportService.addPublicTransport(new PublicTransport());
        verify(publicTransportDao).addPublicTransport(new PublicTransport());
    }

    @Test
    void updatePublicTransport() {
        doNothing().when(publicTransportDao).updatePublicTransport(new PublicTransport());
        publicTransportService.updatePublicTransport(new PublicTransport());
        verify(publicTransportDao).updatePublicTransport(new PublicTransport());
    }

    @Test
    void removePublicTransport() {
        doNothing().when(publicTransportDao).removePublicTransport(0);
        publicTransportService.removePublicTransport(0);
        verify(publicTransportDao).removePublicTransport(0);
    }
}