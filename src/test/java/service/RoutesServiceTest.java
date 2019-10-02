package service;

import dao.RoutesDao;
import entity.Routes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoutesServiceTest {

    @Mock
    RoutesDao routeDao;
    private RoutesService routeService = new RoutesService();

    @Test
    void routeTest() {
        routeService.setRoutesDao(routeDao);
        when(routeDao.getRoute(0)).thenReturn(new Routes());
        assertEquals(new Routes().toString(), routeService.getRoute(0).toString());
    }
}