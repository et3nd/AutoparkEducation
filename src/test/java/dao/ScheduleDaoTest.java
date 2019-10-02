package dao;

import entity.Schedule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScheduleDaoTest {
    private ScheduleDao scheduleDao = new ScheduleDao();

    @Test
    void scheduleTest() {
        Executable testInsert = () -> {
            Schedule schedule = new Schedule();
            scheduleDao.addSchedule(schedule);
            assertEquals(schedule.toString(), scheduleDao.getSchedule(0).toString());
            schedule.setId(1);
            scheduleDao.addSchedule(schedule);
            assertEquals(schedule.toString(), scheduleDao.getSchedule(1).toString());
            scheduleDao.addSchedule(schedule);
        };
        assertThrows(SQLException.class, testInsert);
    }

    @AfterEach
    void remove() {
        scheduleDao.removeSchedule(0);
        scheduleDao.removeSchedule(1);
    }
}