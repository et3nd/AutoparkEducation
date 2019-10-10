package service;

import dao.StopDao;
import entity.Stop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

class StopService {
    private static final Logger log = LoggerFactory.getLogger(StopService.class);
    private StopDao stopDao;

    void setStopDao(StopDao stopDao) {
        this.stopDao = stopDao;
    }

    void addStop(Stop stop) {
        try {
            stopDao.addStop(stop);
        } catch (SQLException e) {
            log.error("This value of stop name is used");
        }
    }

    void updateStop(Stop stop) {
        stopDao.updateStop(stop);
    }

    void removeStop(String stopName) {
        stopDao.removeStop(stopName);
    }

    Stop getStop(String stopName) {
        return stopDao.getStop(stopName);
    }
}
