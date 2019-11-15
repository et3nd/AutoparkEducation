package service;

import dao.ScheduleDao;
import entity.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

@Service
public class ScheduleService {
    private static final Logger log = LoggerFactory.getLogger(ScheduleService.class);
    private ScheduleDao scheduleDao;

    @Autowired
    public ScheduleService(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    public void addSchedule(Schedule schedule) throws SQLException {
        try {
            scheduleDao.addSchedule(schedule);
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }

    public void updateSchedule(Schedule inputSchedule) {
        Schedule outputSchedule = scheduleDao.getSchedule(inputSchedule.getId());
        if (!(inputSchedule.getArrivalTime().equals(Time.valueOf(LocalTime.of(0, 1, 1)))))
            outputSchedule.setArrivalTime(inputSchedule.getArrivalTime());
        if (!(inputSchedule.getDepartureTime().equals(Time.valueOf(LocalTime.of(0, 1, 1)))))
            outputSchedule.setDepartureTime(inputSchedule.getDepartureTime());
        scheduleDao.updateSchedule(outputSchedule);
    }

    public void removeSchedule(int id) {
        scheduleDao.getSchedule(id);
        scheduleDao.removeSchedule(id);
    }

    public Schedule getSchedule(int id) {
        return scheduleDao.getSchedule(id);
    }
}
