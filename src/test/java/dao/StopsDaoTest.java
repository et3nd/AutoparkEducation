package dao;

import entity.Stops;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StopsDaoTest {
    private StopsDao stopsDao = new StopsDao();
    private Stops stop = new Stops();

    @BeforeEach
    void addDriver() throws SQLException {
        stop.setStopName("Stop");
        stop.setDirection("Direction");
        stop.setArrivalTimeOnStop(Time.valueOf(LocalTime.of(0, 2, 30)));
        stopsDao.addStop(stop);
    }

    @Test
    void getStop() {
        assertEquals(stop, stopsDao.getStop("Stop"));
        assertEquals(new Stops(), stopsDao.getStop(""));
    }

    @Test
    void addStopWithDefaultValues() throws SQLException {
        Stops defaultStop = new Stops();
        stopsDao.addStop(defaultStop);
        assertEquals(defaultStop, stopsDao.getStop("default"));
    }

    @Test
    void addStopWithUsedValue() {
        assertThrows(SQLException.class, () -> stopsDao.addStop(stop));
    }

    @Test
    void updateStop() {
        stop.setDirection("New");
        stopsDao.updateStop(stop);
        assertEquals(stop, stopsDao.getStop("Stop"));
    }

    @Test
    void removeStop() {
        stopsDao.removeStop("Stop");
    }

    @AfterEach
    void remove() {
        stopsDao.removeStop("Stop");
        stopsDao.removeStop("default");
    }
}