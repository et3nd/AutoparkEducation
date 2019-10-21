package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.StopDao;
import entity.Stop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

public class StopService {
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger log = LoggerFactory.getLogger(StopService.class);
    private StopDao stopDao;

    public void setStopDao(StopDao stopDao) {
        this.stopDao = stopDao;
    }

    public String addStop(String input) {
        try {
            Stop stop = new ObjectMapper().setDateFormat(df).readValue(input, Stop.class);
            stopDao.addStop(stop);
            return "Success";
        } catch (SQLException e) {
            return e.getMessage();
        } catch (IOException e) {
            log.error("Error: ", e);
            return e.getMessage();
        }
    }

    public String updateStop(String input) {
        try {
            Stop inputStop = new ObjectMapper().setDateFormat(df).readValue(input, Stop.class);
            Stop outputStop = stopDao.getStop(inputStop.getStopName());
            if (inputStop.equals(outputStop)) throw new SQLException("Same values");
            if (!(inputStop.getDirection().equals("default"))) outputStop.setDirection(inputStop.getDirection());
            if (!(inputStop.getArrivalTimeOnStop().equals(Time.valueOf(LocalTime.of(0, 0, 0)))))
                outputStop.setArrivalTimeOnStop(inputStop.getArrivalTimeOnStop());
            stopDao.updateStop(outputStop);
            return "Success";
        } catch (SQLException e) {
            return e.getMessage();
        } catch (IOException e) {
            log.error("Error: ", e);
            return e.getMessage();
        }
    }

    public String removeStop(String stopName) {
        try {
            stopDao.getStop(stopName);
            stopDao.removeStop(stopName);
            return "Success";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String getStop(String stopName) {
        try {
            Stop stop = stopDao.getStop(stopName);
            return new ObjectMapper().setDateFormat(df).writeValueAsString(stop);
        } catch (SQLException e) {
            return e.getMessage();
        } catch (JsonProcessingException e) {
            log.error("Error: ", e);
            return e.getMessage();
        }
    }
}
