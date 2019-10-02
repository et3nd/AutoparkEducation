package service;

import dao.ScheduleDao;
import entity.Schedule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {

    @Mock
    ScheduleDao scheduleDao;
    private ScheduleService scheduleService = new ScheduleService();

    @Test
    void scheduleTest() {
        scheduleService.setScheduleDao(scheduleDao);
        when(scheduleDao.getSchedule(0)).thenReturn(new Schedule());
        assertEquals(new Schedule().toString(), scheduleService.getSchedule(0).toString());
    }
}