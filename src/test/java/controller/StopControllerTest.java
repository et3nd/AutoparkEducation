package controller;

import entity.Stop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import service.StopService;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = StopController.class)
class StopControllerTest {

    @Autowired
    private StopController stopController;
    private Stop stop = new Stop();

    @MockBean
    private StopService stopService;

    @BeforeEach
    void setStopName() {
        stop.setStopName("Stop");
    }

    @Test
    void getStop() {
        doReturn(stop).when(stopService).getStop(stop.getStopName());
        stopController.getStop(stop.getStopName());
        verify(stopService).getStop(stop.getStopName());
    }

    @Test
    void removeStop() {
        doNothing().when(stopService).removeStop(stop.getStopName());
        stopController.removeStop(stop.getStopName());
        verify(stopService).removeStop(stop.getStopName());
    }

    @Test
    void addStop() throws SQLException {
        doNothing().when(stopService).addStop(stop);
        stopController.addStop(stop);
        verify(stopService).addStop(stop);
    }

    @Test
    void addStopWithException() throws SQLException {
        doThrow(SQLException.class).when(stopService).addStop(stop);
        stopController.addStop(stop);
        verify(stopService).addStop(stop);
    }

    @Test
    void updateStop() {
        doNothing().when(stopService).updateStop(stop);
        stopController.updateStop(stop);
        verify(stopService).updateStop(stop);
    }
}