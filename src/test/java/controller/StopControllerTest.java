package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Stop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.StopService;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StopControllerTest {
    private StopController stopController = new StopController();
    private Stop stop = new Stop();

    @Mock
    private StopService stopService;

    @BeforeEach
    void set() {
        stopController.setStopService(stopService);
        stop.setStopName("Stop");
    }

    @Test
    void getStop() throws SQLException {
        doReturn(stop).when(stopService).getStop(stop.getStopName());
        stopController.getStop(stop.getStopName());
        verify(stopService).getStop(stop.getStopName());
    }

    @Test
    void getStopWithException() throws SQLException {
        doThrow(SQLException.class).when(stopService).getStop(stop.getStopName());
        stopController.getStop(stop.getStopName());
        verify(stopService).getStop(stop.getStopName());
    }

    @Test
    void removeStop() throws SQLException {
        doNothing().when(stopService).removeStop(stop.getStopName());
        stopController.removeStop(stop.getStopName());
        verify(stopService).removeStop(stop.getStopName());
    }

    @Test
    void removeStopWithException() throws SQLException {
        doThrow(SQLException.class).when(stopService).removeStop(stop.getStopName());
        stopController.removeStop(stop.getStopName());
        verify(stopService).removeStop(stop.getStopName());
    }

    @Test
    void addStop() throws JsonProcessingException, SQLException {
        doNothing().when(stopService).addStop(stop);
        stopController.addStop(new ObjectMapper().writeValueAsString(stop));
        verify(stopService).addStop(stop);
    }

    @Test
    void addStopWithException() throws SQLException, JsonProcessingException {
        doThrow(SQLException.class).when(stopService).addStop(stop);
        stopController.addStop(new ObjectMapper().writeValueAsString(stop));
        verify(stopService).addStop(stop);
    }

    @Test
    void updateStop() throws JsonProcessingException, SQLException {
        doNothing().when(stopService).updateStop(stop);
        stopController.updateStop(new ObjectMapper().writeValueAsString(stop));
        verify(stopService).updateStop(stop);
    }

    @Test
    void updateStopWithException() throws JsonProcessingException, SQLException {
        doThrow(new SQLException("Same values")).when(stopService).updateStop(stop);
        stopController.updateStop(new ObjectMapper().writeValueAsString(stop));
        verify(stopService).updateStop(stop);
    }
}