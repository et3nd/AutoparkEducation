package dao;

import entity.Stops;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StopsDaoTest {
    private StopsDao stopDao = new StopsDao();

    @Test
    void getStop() {
        Executable testInsert = () -> {
            Stops stop = new Stops();
            stopDao.addStop(stop);
            assertEquals(stop.toString(), stopDao.getStop("default").toString());
            stop.setStopName("Пл. Ленина");
            stopDao.addStop(stop);
            assertEquals(stop.toString(), stopDao.getStop("Пл. Ленина").toString());
            stopDao.addStop(stop);
        };
        assertThrows(SQLException.class, testInsert);
    }

    @AfterEach
    void remove() {
        stopDao.removeStop("default");
        stopDao.removeStop("Пл. Ленина");
    }
}