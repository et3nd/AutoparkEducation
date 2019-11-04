package service;

import dao.ScheduleDao;
import entity.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

public class ScheduleService {
    private static final Logger log = LoggerFactory.getLogger(ScheduleService.class);
    private ScheduleDao scheduleDao = new ScheduleDao();

    void setScheduleDao(ScheduleDao scheduleDao) {
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

    public void updateSchedule(Schedule inputSchedule) throws SQLException {
        try {
            Schedule outputSchedule = scheduleDao.getSchedule(inputSchedule.getId());
            if (inputSchedule.equals(outputSchedule)) throw new SQLException("Same values");
            if (!(inputSchedule.getArrivalTime().equals(Time.valueOf(LocalTime.of(0, 1, 1)))))
                outputSchedule.setArrivalTime(inputSchedule.getArrivalTime());
            if (!(inputSchedule.getDepartureTime().equals(Time.valueOf(LocalTime.of(0, 1, 1)))))
                outputSchedule.setDepartureTime(inputSchedule.getDepartureTime());
            scheduleDao.updateSchedule(outputSchedule);
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }

    public void removeSchedule(int id) throws SQLException {
        try {
            scheduleDao.getSchedule(id);
            scheduleDao.removeSchedule(id);
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }

    public Schedule getSchedule(int id) throws SQLException {
        try {
            return scheduleDao.getSchedule(id);
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }
}
