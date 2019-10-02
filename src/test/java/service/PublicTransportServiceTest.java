package service;

import dao.PublicTransportDao;
import entity.PublicTransport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PublicTransportServiceTest {
    private PublicTransportService publicTransportService = new PublicTransportService();
    private PublicTransportDao publicTransportDao = mock(PublicTransportDao.class);

    @Test
    void publicTransportTest() {
        publicTransportService.setPublicTransportDao(publicTransportDao);
        when(publicTransportDao.getPublicTransport(0)).thenReturn(new PublicTransport());
        assertEquals(new PublicTransport().toString(), publicTransportService.getPublicTransport(0).toString());
    }
}