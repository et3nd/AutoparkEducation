package dao;

import entity.Schedule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(schedule, scheduleDao.getSchedule(schedule.getId()));
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
        assertEquals(schedule, scheduleDao.getSchedule(schedule.getId()));
        assertThrows(SQLException.class, () -> scheduleDao.addSchedule(schedule));
    }

    @Test
    void updateSchedule() {
        assertEquals(schedule, scheduleDao.getSchedule(schedule.getId()));
        schedule.setArrivalTime(Time.valueOf(LocalTime.of(9, 15, 0)));
        scheduleDao.updateSchedule(schedule);
        assertEquals(schedule, scheduleDao.getSchedule(schedule.getId()));
    }

    @Test
    void removeSchedule() {
        assertEquals(schedule, scheduleDao.getSchedule(schedule.getId()));
        scheduleDao.removeSchedule(schedule.getId());
        assertNotEquals(schedule, scheduleDao.getSchedule(schedule.getId()));
    }

    @AfterEach
    void remove() {
        scheduleDao.removeSchedule(schedule.getId());
        scheduleDao.removeSchedule(0);
    }
}