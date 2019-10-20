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

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduleControllerTest {
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private ScheduleController scheduleController = new ScheduleController();
    private Schedule schedule = new Schedule();

    @Mock
    private ScheduleService scheduleService;

    @BeforeEach
    void set() {
        scheduleController.setScheduleService(scheduleService);
        schedule.setId(1);
    }

    @Test
    void getSchedule() throws JsonProcessingException {
        doReturn(schedule).when(scheduleService).getSchedule(schedule.getId());
        assertEquals(new ObjectMapper().setDateFormat(df).writeValueAsString(schedule), scheduleController.getSchedule(schedule.getId()));
        verify(scheduleService).getSchedule(schedule.getId());
    }

    @Test
    void removeSchedule() {
        doNothing().when(scheduleService).removeSchedule(schedule.getId());
        scheduleController.removeSchedule(schedule.getId());
        verify(scheduleService).removeSchedule(schedule.getId());
    }

    @Test
    void addSchedule() throws JsonProcessingException {
        doNothing().when(scheduleService).addSchedule(schedule);
        doReturn(null, schedule).when(scheduleService).getSchedule(schedule.getId());
        scheduleController.addSchedule(new ObjectMapper().setDateFormat(df).writeValueAsString(schedule));
        verify(scheduleService).addSchedule(schedule);
    }

    @Test
    void addScheduleWithException() {
        scheduleController.addSchedule("Input");
    }

    @Test
    void updateSchedule() throws JsonProcessingException {
        doNothing().when(scheduleService).updateSchedule(schedule);
        doReturn(schedule).when(scheduleService).getSchedule(schedule.getId());
        scheduleController.updateSchedule(new ObjectMapper().setDateFormat(df).writeValueAsString(schedule));
        verify(scheduleService).updateSchedule(schedule);
    }

    @Test
    void updateScheduleWithException() {
        scheduleController.updateSchedule("Input");
    }
}