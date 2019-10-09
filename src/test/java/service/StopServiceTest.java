package service;

import dao.StopDao;
import entity.Stop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StopServiceTest {
    private StopService stopService = new StopService();

    @Spy
    private StopDao stopDao;

    @BeforeEach
    void setStopsDao() {
        stopService.setStopDao(stopDao);
    }

    @Test
    void getStop() {
        when(stopDao.getStop("default")).thenReturn(new Stop());
        assertEquals(new Stop(), stopService.getStop("default"));
    }

    @Test
    void addStop() throws SQLException {
        doNothing().when(stopDao).addStop(new Stop());
        stopService.addStop(new Stop());
    }

    @Test
    void addStopWithException() throws SQLException {
        doThrow(SQLException.class).when(stopDao).addStop(new Stop());
        stopService.addStop(new Stop());
    }

    @Test
    void updateStop() {
        doNothing().when(stopDao).updateStop(new Stop());
        stopService.updateStop(new Stop());
    }

    @Test
    void removeStop() {
        doNothing().when(stopDao).removeStop("default");
        stopService.removeStop("default");
    }
}