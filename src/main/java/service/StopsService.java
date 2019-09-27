package service;

import dao.StopsDao;
import entity.Stops;

public class StopsService {
    private StopsDao stopsDao;

    public void setStopsDao(StopsDao stopsDao) {
        this.stopsDao = stopsDao;
    }

    public void addStop(Stops stop) {
        stopsDao.addStop(stop);
    }

    public void updateStop(Stops stop) {
        stopsDao.updateStop(stop);
    }

    public void removeStop(String id) {
        stopsDao.removeStop(id);
    }

    public void detStop(String id) {
        stopsDao.getStop(id);
    }
}
