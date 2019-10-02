package service;

import dao.ScheduleDao;
import entity.Schedule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ScheduleServiceTest {
    private ScheduleService scheduleService = new ScheduleService();
    private ScheduleDao scheduleDao = mock(ScheduleDao.class);

    @Test
    void scheduleTest() {
        scheduleService.setScheduleDao(scheduleDao);
        when(scheduleDao.getSchedule(0)).thenReturn(new Schedule());
        assertEquals(new Schedule().toString(), scheduleService.getSchedule(0).toString());
    }
}