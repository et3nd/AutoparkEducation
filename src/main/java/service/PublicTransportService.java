package service;

import dao.PublicTransportDao;
import entity.PublicTransport;

public class PublicTransportService {
    private PublicTransportDao publicTransportDao;

    public void setPublicTransportDao(PublicTransportDao publicTransportDao) {
        this.publicTransportDao = publicTransportDao;
    }

    public void addPublicTransport(PublicTransport transport) {
        publicTransportDao.addPublicTransport(transport);
    }

    public void updatePublicTransport(PublicTransport transport) {
        publicTransportDao.updatePublicTransport(transport);
    }

    public void removePublicTransport(int id) {
        publicTransportDao.removePublicTransport(id);
    }

    public void getPublicTransport(int id) {
        publicTransportDao.getPublicTransport(id);
    }
}
