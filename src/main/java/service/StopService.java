package service;

import dao.StopDao;
import entity.Stop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class StopService {
    private static final Logger log = LoggerFactory.getLogger(StopService.class);
    private StopDao stopDao;

    public void setStopDao(StopDao stopDao) {
        this.stopDao = stopDao;
    }

    public void addStop(Stop stop) {
        try {
            stopDao.addStop(stop);
        } catch (SQLException e) {
            log.error("This value of stop name is used");
        }
    }

    public void updateStop(Stop stop) {
        stopDao.updateStop(stop);
    }

    public void removeStop(String stopName) {
        stopDao.removeStop(stopName);
    }

    public Stop getStop(String stopName) {
        return stopDao.getStop(stopName);
    }
}
