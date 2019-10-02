package service;

import dao.StopsDao;
import entity.Stops;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StopsServiceTest {
    private StopsService stopService = new StopsService();
    private StopsDao stopDao = mock(StopsDao.class);

    @Test
    void detStop() {
        stopService.setStopsDao(stopDao);
        when(stopDao.getStop("default")).thenReturn(new Stops());
        assertEquals(new Stops().toString(), stopService.getStop("default").toString());
    }
}