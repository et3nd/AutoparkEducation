package service;

import dao.StopDao;
import entity.Stop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StopServiceTest {
    private StopService stopService = new StopService();
    private Stop stop = new Stop();

    @Mock
    private StopDao stopDao;

    @BeforeEach
    void setStopsDao() {
        stopService.setStopDao(stopDao);
        stop.setStopName("Stop");
    }

    @Test
    void getStop() throws SQLException {
        doReturn(stop).when(stopDao).getStop(stop.getStopName());
        assertEquals(stop, stopService.getStop(stop.getStopName()));
        verify(stopDao).getStop(stop.getStopName());
    }

    @Test
    void getStopWithException() throws SQLException {
        doThrow(SQLException.class).when(stopDao).getStop(stop.getStopName());
        assertThrows(SQLException.class, () -> stopService.getStop(stop.getStopName()));
        verify(stopDao).getStop(stop.getStopName());
    }

    @Test
    void addStop() throws SQLException {
        doNothing().when(stopDao).addStop(stop);
        stopService.addStop(stop);
        verify(stopDao).addStop(stop);
    }

    @Test
    void addStopWithException() throws SQLException {
        doThrow(SQLException.class).when(stopDao).addStop(stop);
        assertThrows(SQLException.class, () -> stopService.addStop(stop));
        verify(stopDao).addStop(stop);
    }

    @Test
    void updateStop() throws SQLException {
        Stop outputStop = new Stop();
        outputStop.setStopName("Stop");
        outputStop.setDirection("Reverse");
        doNothing().when(stopDao).updateStop(outputStop);
        doReturn(stop).when(stopDao).getStop(stop.getStopName());
        stopService.updateStop(outputStop);
        verify(stopDao).updateStop(outputStop);
    }

    @Test
    void removeStop() throws SQLException {
        doNothing().when(stopDao).removeStop(stop.getStopName());
        stopService.removeStop(stop.getStopName());
        verify(stopDao).removeStop(stop.getStopName());
    }
}