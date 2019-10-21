package dao;

import entity.Stop;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void getStop() throws SQLException {
        assertEquals(stop, stopDao.getStop(stop.getStopName()));
        assertThrows(SQLException.class, () -> stopDao.getStop(""));
    }

    @Test
    void addStopWithDefaultValues() throws SQLException {
        Stop defaultStop = new Stop();
        defaultStop.setStopName("default stop");
        stopDao.addStop(defaultStop);
        assertEquals(defaultStop, stopDao.getStop(defaultStop.getStopName()));
    }

    @Test
    void addStopWithUsedValue() throws SQLException {
        assertEquals(stop, stopDao.getStop(stop.getStopName()));
        assertThrows(SQLException.class, () -> stopDao.addStop(stop));
    }

    @Test
    void updateStop() throws SQLException {
        assertEquals(stop, stopDao.getStop(stop.getStopName()));
        stop.setDirection("New");
        stopDao.updateStop(stop);
        assertEquals(stop, stopDao.getStop(stop.getStopName()));
    }

    @Test
    void removeStop() throws SQLException {
        assertEquals(stop, stopDao.getStop(stop.getStopName()));
        stopDao.removeStop(stop.getStopName());
        assertThrows(SQLException.class, () -> stopDao.getStop(stop.getStopName()));
    }

    @AfterEach
    void remove() throws SQLException {
        stopDao.removeStop(stop.getStopName());
        stopDao.removeStop("default stop");
    }
}