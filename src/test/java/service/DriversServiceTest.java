package service;

import dao.DriversDao;
import entity.Drivers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriversServiceTest {
    private DriversService driverService = new DriversService();

    @Mock
    private DriversDao driverDao;

    @BeforeEach
    void setDriverDao() {
        driverService.setDriversDao(driverDao);
    }

    @Test
    void getDriver() {
        when(driverDao.getDriver(111111)).thenReturn(new Drivers());
        assertEquals(new Drivers(), driverService.getDriver(111111));
    }

    @Test
    void addDriver() throws SQLException {
        doNothing().when(driverDao).addDriver(new Drivers());
        driverService.addDriver(new Drivers());
    }

    @Test
    void addDriverWithException() throws SQLException {
        doThrow(SQLException.class).when(driverDao).addDriver(new Drivers());
        driverService.addDriver(new Drivers());
    }

    @Test
    void updateDriver() {
        doNothing().when(driverDao).updateDriver(new Drivers());
        driverService.updateDriver(new Drivers());
    }

    @Test
    void removeDriver() {
        doNothing().when(driverDao).removeDriver(111111);
        driverService.removeDriver(111111);
    }
}