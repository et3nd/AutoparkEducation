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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

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
        doReturn(new ObjectMapper().setDateFormat(df).writeValueAsString(schedule)).when(scheduleService).getSchedule(schedule.getId());
        assertEquals(new ObjectMapper().setDateFormat(df).writeValueAsString(schedule), scheduleController.getSchedule(schedule.getId()));
        verify(scheduleService).getSchedule(schedule.getId());
    }

    @Test
    void removeSchedule() {
        doReturn("Success").when(scheduleService).removeSchedule(schedule.getId());
        scheduleController.removeSchedule(schedule.getId());
        verify(scheduleService).removeSchedule(schedule.getId());
    }

    @Test
    void addSchedule() throws JsonProcessingException {
        doReturn("Success").when(scheduleService)
                .addSchedule(new ObjectMapper().setDateFormat(df).writeValueAsString(schedule));
        scheduleController.addSchedule(new ObjectMapper().setDateFormat(df).writeValueAsString(schedule));
        verify(scheduleService).addSchedule(new ObjectMapper().setDateFormat(df).writeValueAsString(schedule));
    }

    @Test
    void updateSchedule() throws JsonProcessingException {
        doReturn("Success").when(scheduleService)
                .updateSchedule(new ObjectMapper().setDateFormat(df).writeValueAsString(schedule));
        scheduleController.updateSchedule(new ObjectMapper().setDateFormat(df).writeValueAsString(schedule));
        verify(scheduleService).updateSchedule(new ObjectMapper().setDateFormat(df).writeValueAsString(schedule));
    }
}