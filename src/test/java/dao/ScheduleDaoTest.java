package dao;

import entity.Schedule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void getSchedule() throws SQLException {
        assertEquals(schedule, scheduleDao.getSchedule(schedule.getId()));
        assertThrows(SQLException.class, () -> scheduleDao.getSchedule(2));
    }

    @Test
    void addScheduleWithDefaultValues() throws SQLException {
        Schedule defaultSchedule = new Schedule();
        defaultSchedule.setId(10);
        scheduleDao.addSchedule(defaultSchedule);
        assertEquals(defaultSchedule, scheduleDao.getSchedule(defaultSchedule.getId()));
    }

    @Test
    void addScheduleWithUsedValue() throws SQLException {
        assertEquals(schedule, scheduleDao.getSchedule(schedule.getId()));
        assertThrows(SQLException.class, () -> scheduleDao.addSchedule(schedule));
    }

    @Test
    void updateSchedule() throws SQLException {
        assertEquals(schedule, scheduleDao.getSchedule(schedule.getId()));
        schedule.setArrivalTime(Time.valueOf(LocalTime.of(9, 15, 0)));
        scheduleDao.updateSchedule(schedule);
        assertEquals(schedule, scheduleDao.getSchedule(schedule.getId()));
    }

    @Test
    void removeSchedule() throws SQLException {
        assertEquals(schedule, scheduleDao.getSchedule(schedule.getId()));
        scheduleDao.removeSchedule(schedule.getId());
        assertThrows(SQLException.class, () -> scheduleDao.getSchedule(schedule.getId()));
    }

    @AfterEach
    void remove() throws SQLException {
        scheduleDao.removeSchedule(schedule.getId());
        scheduleDao.removeSchedule(10);
    }
}