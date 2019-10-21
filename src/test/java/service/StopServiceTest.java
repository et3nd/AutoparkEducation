package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.StopDao;
import entity.Stop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StopServiceTest {
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
    void getStop() throws SQLException, JsonProcessingException {
        doReturn(stop).when(stopDao).getStop(stop.getStopName());
        assertEquals(new ObjectMapper().setDateFormat(df).writeValueAsString(stop), stopService.getStop(stop.getStopName()));
        verify(stopDao).getStop(stop.getStopName());
    }

    @Test
    void addStop() throws SQLException, JsonProcessingException {
        doNothing().when(stopDao).addStop(stop);
        stopService.addStop(new ObjectMapper().setDateFormat(df).writeValueAsString(stop));
        verify(stopDao).addStop(stop);
    }

    @Test
    void addStopWithException() throws SQLException, JsonProcessingException {
        doThrow(SQLException.class).when(stopDao).addStop(stop);
        stopService.addStop(new ObjectMapper().setDateFormat(df).writeValueAsString(stop));
        verify(stopDao).addStop(stop);
    }

    @Test
    void updateStop() throws SQLException, JsonProcessingException {
        Stop outputStop = new Stop();
        outputStop.setStopName("Stop");
        outputStop.setDirection("Reverse");
        doNothing().when(stopDao).updateStop(outputStop);
        doReturn(stop).when(stopDao).getStop(stop.getStopName());
        stopService.updateStop(new ObjectMapper().setDateFormat(df).writeValueAsString(outputStop));
        verify(stopDao).updateStop(outputStop);
    }

    @Test
    void removeStop() throws SQLException {
        doNothing().when(stopDao).removeStop(stop.getStopName());
        stopService.removeStop(stop.getStopName());
        verify(stopDao).removeStop(stop.getStopName());
    }
}