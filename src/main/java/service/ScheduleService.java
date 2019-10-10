package service;

import dao.ScheduleDao;
import entity.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

class ScheduleService {
    private static final Logger log = LoggerFactory.getLogger(ScheduleService.class);
    private ScheduleDao scheduleDao;

    void setScheduleDao(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    void addSchedule(Schedule schedule) {
        try {
            scheduleDao.addSchedule(schedule);
        } catch (SQLException e) {
            log.error("This value of id is used");
        }
    }

    void updateSchedule(Schedule schedule) {
        scheduleDao.updateSchedule(schedule);
    }

    void removeSchedule(int id) {
        scheduleDao.removeSchedule(id);
    }

    Schedule getSchedule(int id) {
        return scheduleDao.getSchedule(id);
    }
}
