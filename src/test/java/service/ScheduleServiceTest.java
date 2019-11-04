package service;

import dao.ScheduleDao;
import entity.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {
    private ScheduleService scheduleService = new ScheduleService();
    private Schedule schedule = new Schedule();

    @Mock
    private ScheduleDao scheduleDao;

    @BeforeEach
    void setScheduleDao() {
        scheduleService.setScheduleDao(scheduleDao);
        schedule.setId(1);
    }

    @Test
    void getSchedule() throws SQLException {
        doReturn(schedule).when(scheduleDao).getSchedule(schedule.getId());
        assertEquals(schedule, scheduleService.getSchedule(schedule.getId()));
        verify(scheduleDao).getSchedule(schedule.getId());
    }

    @Test
    void getScheduleWithException() throws SQLException {
        doThrow(SQLException.class).when(scheduleDao).getSchedule(schedule.getId());
        assertThrows(SQLException.class, () -> scheduleService.getSchedule(schedule.getId()));
        verify(scheduleDao).getSchedule(schedule.getId());
    }

    @Test
    void addSchedule() throws SQLException {
        doNothing().when(scheduleDao).addSchedule(schedule);
        scheduleService.addSchedule(schedule);
        verify(scheduleDao).addSchedule(schedule);
    }

    @Test
    void addScheduleWithException() throws SQLException {
        doThrow(SQLException.class).when(scheduleDao).addSchedule(schedule);
        assertThrows(SQLException.class, () -> scheduleService.addSchedule(schedule));
        verify(scheduleDao).addSchedule(schedule);
    }

    @Test
    void updateSchedule() throws SQLException {
        Schedule outputSchedule = new Schedule();
        outputSchedule.setId(1);
        outputSchedule.setDepartureTime(Time.valueOf(LocalTime.of(4, 2, 5)));
        doNothing().when(scheduleDao).updateSchedule(outputSchedule);
        doReturn(schedule).when(scheduleDao).getSchedule(schedule.getId());
        scheduleService.updateSchedule(outputSchedule);
        verify(scheduleDao).updateSchedule(outputSchedule);
    }

    @Test
    void removeSchedule() throws SQLException {
        doNothing().when(scheduleDao).removeSchedule(schedule.getId());
        scheduleService.removeSchedule(schedule.getId());
        verify(scheduleDao).removeSchedule(schedule.getId());
    }
}