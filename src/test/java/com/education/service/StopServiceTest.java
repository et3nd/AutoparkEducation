package com.education.service;

import com.education.dao.StopDao;
import com.education.entity.Stop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class StopServiceTest {

    @Autowired
    private StopService stopService;
    private Stop stop = new Stop();

    @MockBean
    private StopDao stopDao;

    @BeforeEach
    void setStopName() {
        stop.setStopName("Stop");
    }

    @Test
    void getStop() {
        doReturn(stop).when(stopDao).getStop(stop.getStopName());
        assertEquals(stop, stopService.getStop(stop.getStopName()));
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
    void updateStop() {
        Stop outputStop = new Stop();
        outputStop.setStopName("Stop");
        outputStop.setDirection("Reverse");
        doNothing().when(stopDao).updateStop(outputStop);
        doReturn(stop).when(stopDao).getStop(stop.getStopName());
        stopService.updateStop(outputStop);
        verify(stopDao).updateStop(outputStop);
    }

    @Test
    void removeStop() {
        doNothing().when(stopDao).removeStop(stop.getStopName());
        stopService.removeStop(stop.getStopName());
        verify(stopDao).removeStop(stop.getStopName());
    }
}