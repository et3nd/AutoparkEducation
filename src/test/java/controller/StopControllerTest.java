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

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StopControllerTest {
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
    void getStop() throws JsonProcessingException {
        doReturn(new ObjectMapper().setDateFormat(df).writeValueAsString(stop)).when(stopService).getStop(stop.getStopName());
        assertEquals(new ObjectMapper().setDateFormat(df).writeValueAsString(stop), stopController.getStop(stop.getStopName()));
        verify(stopService).getStop(stop.getStopName());
    }

    @Test
    void removeStop() {
        doReturn("Success").when(stopService).removeStop(stop.getStopName());
        stopController.removeStop(stop.getStopName());
        verify(stopService).removeStop(stop.getStopName());
    }

    @Test
    void addStop() throws JsonProcessingException {
        doReturn("Success").when(stopService).addStop(new ObjectMapper().setDateFormat(df).writeValueAsString(stop));
        stopController.addStop(new ObjectMapper().setDateFormat(df).writeValueAsString(stop));
        verify(stopService).addStop(new ObjectMapper().setDateFormat(df).writeValueAsString(stop));
    }

    @Test
    void updateStop() throws JsonProcessingException {
        doReturn("Success").when(stopService).updateStop(new ObjectMapper().setDateFormat(df).writeValueAsString(stop));
        stopController.updateStop(new ObjectMapper().setDateFormat(df).writeValueAsString(stop));
        verify(stopService).updateStop(new ObjectMapper().setDateFormat(df).writeValueAsString(stop));
    }
}