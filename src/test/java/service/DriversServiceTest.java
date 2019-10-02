package service;

import dao.DriversDao;
import entity.Drivers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DriversServiceTest {
    private DriversDao driverDao = mock(DriversDao.class);
    private DriversService service = new DriversService();

    @Test
    void getDriverTest() {
        service.setDriversDao(driverDao);
        when(driverDao.getDriver(111111)).thenReturn(new Drivers());
        assertEquals(new Drivers().toString(), service.getDriver(111111).toString());
    }
}