package service;

import dao.StopsDao;
import entity.Stops;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StopsServiceTest {

    @Mock
    StopsDao stopDao;
    private StopsService stopService = new StopsService();

    @Test
    void detStop() {
        stopService.setStopsDao(stopDao);
        when(stopDao.getStop("default")).thenReturn(new Stops());
        assertEquals(new Stops().toString(), stopService.getStop("default").toString());
    }
}