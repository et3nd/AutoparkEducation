package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ScheduleDao;
import entity.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
    void getSchedule() throws SQLException, JsonProcessingException {
        doReturn(schedule).when(scheduleDao).getSchedule(schedule.getId());
        assertEquals(new ObjectMapper().setDateFormat(df).writeValueAsString(schedule), scheduleService.getSchedule(schedule.getId()));
        verify(scheduleDao).getSchedule(schedule.getId());
    }

    @Test
    void addSchedule() throws SQLException, JsonProcessingException {
        doNothing().when(scheduleDao).addSchedule(schedule);
        scheduleService.addSchedule(new ObjectMapper().setDateFormat(df).writeValueAsString(schedule));
        verify(scheduleDao).addSchedule(schedule);
    }

    @Test
    void addScheduleWithException() throws SQLException, JsonProcessingException {
        doThrow(SQLException.class).when(scheduleDao).addSchedule(schedule);
        scheduleService.addSchedule(new ObjectMapper().setDateFormat(df).writeValueAsString(schedule));
        verify(scheduleDao).addSchedule(schedule);
    }

    @Test
    void updateSchedule() throws SQLException, JsonProcessingException {
        Schedule outputSchedule = new Schedule();
        outputSchedule.setId(1);
        outputSchedule.setDepartureTime(Time.valueOf(LocalTime.of(4, 2, 5)));
        doNothing().when(scheduleDao).updateSchedule(outputSchedule);
        doReturn(schedule).when(scheduleDao).getSchedule(schedule.getId());
        scheduleService.updateSchedule(new ObjectMapper().setDateFormat(df).writeValueAsString(outputSchedule));
        verify(scheduleDao).updateSchedule(outputSchedule);
    }

    @Test
    void removeSchedule() throws SQLException {
        doNothing().when(scheduleDao).removeSchedule(schedule.getId());
        scheduleService.removeSchedule(schedule.getId());
        verify(scheduleDao).removeSchedule(schedule.getId());
    }
}