package com.education.dao;

import com.education.entity.Stop;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class StopDaoTest {

    @Autowired
    private StopDao stopDao;
    private Stop stop = new Stop();

    @BeforeEach
    void addDriver() throws SQLException {
        stop.setStopName("Stop");
        stop.setDirection("Direction");
        stop.setArrivalTimeOnStop(Time.valueOf(LocalTime.of(0, 2, 30)));
        stopDao.addStop(stop);
    }

    @Test
    void getStop() {
        assertEquals(stop, stopDao.getStop(stop.getStopName()));
    }

    @Test
    void addStopWithDefaultValues() throws SQLException {
        Stop defaultStop = new Stop();
        defaultStop.setStopName("default stop");
        stopDao.addStop(defaultStop);
        assertEquals(defaultStop, stopDao.getStop(defaultStop.getStopName()));
    }

    @Test
    void updateStop() {
        assertEquals(stop, stopDao.getStop(stop.getStopName()));
        stop.setDirection("New");
        stopDao.updateStop(stop);
        assertEquals(stop, stopDao.getStop(stop.getStopName()));
    }

    @Test
    void removeStop() {
        assertEquals(stop, stopDao.getStop(stop.getStopName()));
        stopDao.removeStop(stop.getStopName());
        assertThrows(Exception.class, () -> stopDao.getStop(stop.getStopName()));
    }

    @AfterEach
    void remove() {
        stopDao.removeStop(stop.getStopName());
        stopDao.removeStop("default stop");
    }
}