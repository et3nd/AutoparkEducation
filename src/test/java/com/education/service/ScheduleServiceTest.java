package com.education.service;

import com.education.dao.ScheduleDao;
import com.education.entity.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class ScheduleServiceTest {

    @Autowired
    private ScheduleService scheduleService;
    private Schedule schedule = new Schedule();

    @MockBean
    private ScheduleDao scheduleDao;

    @BeforeEach
    void setId() {
        schedule.setId(1);
    }

    @Test
    void getSchedule() {
        doReturn(schedule).when(scheduleDao).getSchedule(schedule.getId());
        assertEquals(schedule, scheduleService.getSchedule(schedule.getId()));
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
    void updateSchedule() {
        Schedule outputSchedule = new Schedule();
        outputSchedule.setId(1);
        outputSchedule.setDepartureTime(Time.valueOf(LocalTime.of(4, 2, 5)));
        doNothing().when(scheduleDao).updateSchedule(outputSchedule);
        doReturn(schedule).when(scheduleDao).getSchedule(schedule.getId());
        scheduleService.updateSchedule(outputSchedule);
        verify(scheduleDao).updateSchedule(outputSchedule);
    }

    @Test
    void removeSchedule() {
        doNothing().when(scheduleDao).removeSchedule(schedule.getId());
        scheduleService.removeSchedule(schedule.getId());
        verify(scheduleDao).removeSchedule(schedule.getId());
    }
}