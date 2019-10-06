package service;

import dao.StopsDao;
import entity.Stops;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StopsServiceTest {
    private StopsService stopsService = new StopsService();

    @Mock
    private StopsDao stopsDao;

    @BeforeEach
    void setStopsDao() {
        stopsService.setStopsDao(stopsDao);
    }

    @Test
    void getStop() {
        when(stopsDao.getStop("default")).thenReturn(new Stops());
        assertEquals(new Stops(), stopsService.getStop("default"));
    }

    @Test
    void addStop() throws SQLException {
        doNothing().when(stopsDao).addStop(new Stops());
        stopsService.addStop(new Stops());
    }

    @Test
    void addStopWithException() throws SQLException {
        doThrow(SQLException.class).when(stopsDao).addStop(new Stops());
        stopsService.addStop(new Stops());
    }

    @Test
    void updateStop() {
        doNothing().when(stopsDao).updateStop(new Stops());
        stopsService.updateStop(new Stops());
    }

    @Test
    void removeStop() {
        doNothing().when(stopsDao).removeStop("default");
        stopsService.removeStop("default");
    }
}