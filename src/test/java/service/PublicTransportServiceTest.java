package service;

import dao.PublicTransportDao;
import entity.PublicTransport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PublicTransportServiceTest {

    @Mock
    PublicTransportDao publicTransportDao;
    private PublicTransportService publicTransportService = new PublicTransportService();

    @Test
    void publicTransportTest() {
        publicTransportService.setPublicTransportDao(publicTransportDao);
        when(publicTransportDao.getPublicTransport(0)).thenReturn(new PublicTransport());
        assertEquals(new PublicTransport().toString(), publicTransportService.getPublicTransport(0).toString());
    }
}