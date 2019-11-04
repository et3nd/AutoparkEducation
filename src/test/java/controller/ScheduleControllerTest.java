package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.ScheduleService;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduleControllerTest {
    private ScheduleController scheduleController = new ScheduleController();
    private Schedule schedule = new Schedule();

    @Mock
    private ScheduleService scheduleService;

    @BeforeEach
    void setScheduleService() {
        scheduleController.setScheduleService(scheduleService);
        schedule.setId(1);
    }

    @Test
    void getSchedule() throws SQLException {
        doReturn(schedule).when(scheduleService).getSchedule(schedule.getId());
        scheduleController.getSchedule(schedule.getId());
        verify(scheduleService).getSchedule(schedule.getId());
    }

    @Test
    void getScheduleWithException() throws SQLException {
        doThrow(SQLException.class).when(scheduleService).getSchedule(schedule.getId());
        scheduleController.getSchedule(schedule.getId());
        verify(scheduleService).getSchedule(schedule.getId());
    }

    @Test
    void removeSchedule() throws SQLException {
        doNothing().when(scheduleService).removeSchedule(schedule.getId());
        scheduleController.removeSchedule(schedule.getId());
        verify(scheduleService).removeSchedule(schedule.getId());
    }

    @Test
    void removeScheduleWithException() throws SQLException {
        doThrow(SQLException.class).when(scheduleService).removeSchedule(schedule.getId());
        scheduleController.removeSchedule(schedule.getId());
        verify(scheduleService).removeSchedule(schedule.getId());
    }

    @Test
    void addSchedule() throws JsonProcessingException, SQLException {
        doNothing().when(scheduleService).addSchedule(schedule);
        scheduleController.addSchedule(new ObjectMapper().writeValueAsString(schedule));
        verify(scheduleService).addSchedule(schedule);
    }

    @Test
    void addScheduleWithException() throws SQLException, JsonProcessingException {
        doThrow(SQLException.class).when(scheduleService).addSchedule(schedule);
        scheduleController.addSchedule(new ObjectMapper().writeValueAsString(schedule));
        verify(scheduleService).addSchedule(schedule);
    }

    @Test
    void updateSchedule() throws JsonProcessingException, SQLException {
        doNothing().when(scheduleService).updateSchedule(schedule);
        scheduleController.updateSchedule(new ObjectMapper().writeValueAsString(schedule));
        verify(scheduleService).updateSchedule(schedule);
    }

    @Test
    void updateScheduleWithException() throws JsonProcessingException, SQLException {
        doThrow(new SQLException("Same values")).when(scheduleService).updateSchedule(schedule);
        scheduleController.updateSchedule(new ObjectMapper().writeValueAsString(schedule));
        verify(scheduleService).updateSchedule(schedule);
    }
}