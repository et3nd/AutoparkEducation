package dao;

import configuration.JdbcConfiguration;
import entity.Schedule;
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

@SpringBootTest(classes = {ScheduleDao.class, JdbcConfiguration.class})
class ScheduleDaoTest {

    @Autowired
    private ScheduleDao scheduleDao;
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
    }

    @Test
    void addScheduleWithDefaultValues() throws SQLException {
        Schedule defaultSchedule = new Schedule();
        defaultSchedule.setId(10);
        scheduleDao.addSchedule(defaultSchedule);
        assertEquals(defaultSchedule, scheduleDao.getSchedule(defaultSchedule.getId()));
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
        assertThrows(Exception.class, () -> scheduleDao.getSchedule(schedule.getId()));
    }

    @AfterEach
    void remove() {
        scheduleDao.removeSchedule(schedule.getId());
        scheduleDao.removeSchedule(10);
    }
}