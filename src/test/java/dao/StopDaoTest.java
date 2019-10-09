package dao;

import entity.Stop;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class StopDaoTest {
    private StopDao stopDao = new StopDao();
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
        assertEquals(new Stop(), stopDao.getStop(""));
    }

    @Test
    void addStopWithDefaultValues() throws SQLException {
        Stop defaultStop = new Stop();
        stopDao.addStop(defaultStop);
        assertEquals(defaultStop, stopDao.getStop("default"));
    }

    @Test
    void addStopWithUsedValue() {
        assertEquals(stop, stopDao.getStop(stop.getStopName()));
        assertThrows(SQLException.class, () -> stopDao.addStop(stop));
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
        assertNotEquals(stop, stopDao.getStop(stop.getStopName()));
    }

    @AfterEach
    void remove() {
        stopDao.removeStop(stop.getStopName());
        stopDao.removeStop("default");
    }
}