package com.education.web.controller;

import com.education.entity.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.education.service.ScheduleService;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

@SpringBootTest
class ScheduleControllerTest {

    @Autowired
    private ScheduleController scheduleController;
    private Schedule schedule = new Schedule();

    @MockBean
    private ScheduleService scheduleService;

    @BeforeEach
    void setId() {
        schedule.setId(1);
    }

    @Test
    void getSchedule() {
        doReturn(schedule).when(scheduleService).getSchedule(schedule.getId());
        scheduleController.getSchedule(schedule.getId());
        verify(scheduleService).getSchedule(schedule.getId());
    }

    @Test
    void removeSchedule() {
        doNothing().when(scheduleService).removeSchedule(schedule.getId());
        scheduleController.removeSchedule(schedule.getId());
        verify(scheduleService).removeSchedule(schedule.getId());
    }

    @Test
    void addSchedule() throws SQLException {
        doNothing().when(scheduleService).addSchedule(schedule);
        scheduleController.addSchedule(schedule);
        verify(scheduleService).addSchedule(schedule);
    }

    @Test
    void addScheduleWithException() throws SQLException {
        doThrow(SQLException.class).when(scheduleService).addSchedule(schedule);
        scheduleController.addSchedule(schedule);
        verify(scheduleService).addSchedule(schedule);
    }

    @Test
    void updateSchedule() {
        doNothing().when(scheduleService).updateSchedule(schedule);
        scheduleController.updateSchedule(schedule);
        verify(scheduleService).updateSchedule(schedule);
    }
}