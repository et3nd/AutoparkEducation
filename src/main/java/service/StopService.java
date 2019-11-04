package service;

import dao.StopDao;
import entity.Stop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

public class StopService {
    private static final Logger log = LoggerFactory.getLogger(StopService.class);
    private StopDao stopDao = new StopDao();

    void setStopDao(StopDao stopDao) {
        this.stopDao = stopDao;
    }

    public void addStop(Stop stop) throws SQLException {
        try {
            stopDao.addStop(stop);
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }

    public void updateStop(Stop inputStop) throws SQLException {
        try {
            Stop outputStop = stopDao.getStop(inputStop.getStopName());
            if (inputStop.equals(outputStop)) throw new SQLException("Same values");
            if (!(inputStop.getDirection().equals("default"))) outputStop.setDirection(inputStop.getDirection());
            if (!(inputStop.getArrivalTimeOnStop().equals(Time.valueOf(LocalTime.of(0, 0, 0)))))
                outputStop.setArrivalTimeOnStop(inputStop.getArrivalTimeOnStop());
            stopDao.updateStop(outputStop);
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }

    public void removeStop(String stopName) throws SQLException {
        try {
            stopDao.getStop(stopName);
            stopDao.removeStop(stopName);
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }

    public Stop getStop(String stopName) throws SQLException {
        try {
            return stopDao.getStop(stopName);
        } catch (SQLException e) {
            log.error("Error: ", e);
            throw new SQLException(e.getMessage());
        }
    }
}
