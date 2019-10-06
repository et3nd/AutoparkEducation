package dao;

import entity.Schedule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScheduleDaoTest {
    private ScheduleDao scheduleDao = new ScheduleDao();
    private Schedule schedule = new Schedule();

    @BeforeEach
    void addSchedule() throws SQLException {
        schedule.setId(1);
        schedule.setArrivalTime(Time.valueOf(LocalTime.of(22, 46, 0)));
        schedule.setDepartureTime(Time.valueOf(LocalTime.of(11, 23, 0)));
        scheduleDao.addSchedule(schedule);
    }

    @Test
    void getSchedule() {
        assertEquals(schedule, scheduleDao.getSchedule(1));
        assertEquals(new Schedule(), scheduleDao.getSchedule(11));
    }

    @Test
    void addScheduleWithDefaultValues() throws SQLException {
        Schedule defaultSchedule = new Schedule();
        scheduleDao.addSchedule(defaultSchedule);
        assertEquals(defaultSchedule, scheduleDao.getSchedule(0));
    }

    @Test
    void addScheduleWithUsedValue() {
        Executable testInsert = () -> scheduleDao.addSchedule(schedule);
        assertThrows(SQLException.class, testInsert);
    }

    @Test
    void updateSchedule() {
        schedule.setArrivalTime(Time.valueOf(LocalTime.of(9, 15, 0)));
        scheduleDao.updateSchedule(schedule);
        assertEquals(schedule, scheduleDao.getSchedule(1));
    }

    @Test
    void removeSchedule() {
        scheduleDao.removeSchedule(2);
    }

    @AfterEach
    void remove() {
        scheduleDao.removeSchedule(1);
        scheduleDao.removeSchedule(0);
    }
}