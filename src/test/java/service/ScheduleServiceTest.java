package service;

import dao.ScheduleDao;
import entity.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {
    private ScheduleService scheduleService = new ScheduleService();

    @Mock
    private ScheduleDao scheduleDao;

    @BeforeEach
    void setScheduleDao() {
        scheduleService.setScheduleDao(scheduleDao);
    }

    @Test
    void getSchedule() {
        doReturn(new Schedule()).when(scheduleDao).getSchedule(0);
        assertEquals(new Schedule(), scheduleService.getSchedule(0));
        verify(scheduleDao).getSchedule(0);
    }

    @Test
    void addSchedule() throws SQLException {
        doNothing().when(scheduleDao).addSchedule(new Schedule());
        scheduleService.addSchedule(new Schedule());
        verify(scheduleDao).addSchedule(new Schedule());
    }

    @Test
    void addScheduleWithException() throws SQLException {
        doThrow(SQLException.class).when(scheduleDao).addSchedule(new Schedule());
        scheduleService.addSchedule(new Schedule());
        verify(scheduleDao).addSchedule(new Schedule());
    }

    @Test
    void updateSchedule() {
        doNothing().when(scheduleDao).updateSchedule(new Schedule());
        scheduleService.updateSchedule(new Schedule());
        verify(scheduleDao).updateSchedule(new Schedule());
    }

    @Test
    void removeSchedule() {
        doNothing().when(scheduleDao).removeSchedule(0);
        scheduleService.removeSchedule(0);
        verify(scheduleDao).removeSchedule(0);
    }
}