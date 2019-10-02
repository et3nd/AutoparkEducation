package service;

import dao.StopsDao;
import entity.Stops;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class StopsService {
    private static final Logger log = LoggerFactory.getLogger(StopsService.class);
    private StopsDao stopsDao;

    public void setStopsDao(StopsDao stopsDao) {
        this.stopsDao = stopsDao;
    }

    public void addStop(Stops stop) {
        try {
            stopsDao.addStop(stop);
        } catch (SQLException e) {
            log.error("This value of stop name is used");
        }
    }

    public void updateStop(Stops stop) {
        stopsDao.updateStop(stop);
    }

    public void removeStop(String stopName) {
        stopsDao.removeStop(stopName);
    }

    public Stops getStop(String stopName) {
        return stopsDao.getStop(stopName);
    }
}
