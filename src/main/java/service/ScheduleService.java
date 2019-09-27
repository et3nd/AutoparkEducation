package service;

import dao.ScheduleDao;
import entity.Schedule;

public class ScheduleService {
    private ScheduleDao scheduleDao;

    public void setScheduleDao(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    public void addSchedule(Schedule schedule) {
        scheduleDao.addSchedule(schedule);
    }

    public void updateSchedule(Schedule schedule) {
        scheduleDao.updateSchedule(schedule);
    }

    public void removeSchedule(int id) {
        scheduleDao.removeSchedule(id);
    }

    public void getSchedule(int id) {
        scheduleDao.getSchedule(id);
    }
}
