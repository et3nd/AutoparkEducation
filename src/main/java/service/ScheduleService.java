package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ScheduleDao;
import entity.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

public class ScheduleService {
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger log = LoggerFactory.getLogger(ScheduleService.class);
    private ScheduleDao scheduleDao;

    public void setScheduleDao(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    public String addSchedule(String input) {
        try {
            Schedule schedule = new ObjectMapper().setDateFormat(df).readValue(input, Schedule.class);
            scheduleDao.addSchedule(schedule);
            return "Success";
        } catch (SQLException e) {
            return e.getMessage();
        } catch (IOException e) {
            log.error("Error: ", e);
            return e.getMessage();
        }
    }

    public String updateSchedule(String input) {
        try {
            Schedule inputSchedule = new ObjectMapper().setDateFormat(df).readValue(input, Schedule.class);
            Schedule outputSchedule = scheduleDao.getSchedule(inputSchedule.getId());
            if (inputSchedule.equals(outputSchedule)) throw new SQLException("Same values");
            if (!(inputSchedule.getArrivalTime().equals(Time.valueOf(LocalTime.of(0, 1, 1)))))
                outputSchedule.setArrivalTime(inputSchedule.getArrivalTime());
            if (!(inputSchedule.getDepartureTime().equals(Time.valueOf(LocalTime.of(0, 1, 1)))))
                outputSchedule.setDepartureTime(inputSchedule.getDepartureTime());
            scheduleDao.updateSchedule(outputSchedule);
            return "Success";
        } catch (SQLException e) {
            return e.getMessage();
        } catch (IOException e) {
            log.error("Error: ", e);
            return e.getMessage();
        }
    }

    public String removeSchedule(int id) {
        try {
            scheduleDao.getSchedule(id);
            scheduleDao.removeSchedule(id);
            return "Success";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String getSchedule(int id) {
        try {
            Schedule schedule = scheduleDao.getSchedule(id);
            return new ObjectMapper().setDateFormat(df).writeValueAsString(schedule);
        } catch (SQLException e) {
            return e.getMessage();
        } catch (JsonProcessingException e) {
            log.error("Error: ", e);
            return e.getMessage();
        }
    }
}
