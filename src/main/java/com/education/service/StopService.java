package com.education.service;

import com.education.dao.StopDao;
import com.education.entity.Stop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

@Service
@Slf4j
public class StopService {
    private final StopDao stopDao;

    @Autowired
    public StopService(StopDao stopDao) {
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

    public void updateStop(Stop inputStop) {
        Stop outputStop = stopDao.getStop(inputStop.getStopName());
        if (!(inputStop.getDirection().equals("default"))) outputStop.setDirection(inputStop.getDirection());
        if (!(inputStop.getArrivalTimeOnStop().equals(Time.valueOf(LocalTime.of(0, 0, 0)))))
            outputStop.setArrivalTimeOnStop(inputStop.getArrivalTimeOnStop());
        stopDao.updateStop(outputStop);
    }

    public void removeStop(String stopName) {
        stopDao.getStop(stopName);
        stopDao.removeStop(stopName);
    }

    public Stop getStop(String stopName) {
        return stopDao.getStop(stopName);
    }
}
