package service;

import dao.DriversDao;
import entity.Drivers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DriversServiceTest {

    @Mock
    DriversDao driverDao;
    private DriversService driverService = new DriversService();

    @Test
    void getDriverTest() {
        driverService.setDriversDao(driverDao);
        when(driverDao.getDriver(111111)).thenReturn(new Drivers());
        assertEquals(new Drivers().toString(), driverService.getDriver(111111).toString());
    }
}