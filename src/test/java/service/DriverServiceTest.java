package service;

import dao.DriverDao;
import entity.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverServiceTest {
    private DriverService driverService = new DriverService();

    @Mock
    private DriverDao driverDao;

    @BeforeEach
    void setDriverDao() {
        driverService.setDriverDao(driverDao);
    }

    @Test
    void getDriver() {
        doReturn(new Driver()).when(driverDao).getDriver(0);
        assertEquals(new Driver(), driverService.getDriver(0));
        verify(driverDao).getDriver(0);
    }

    @Test
    void addDriver() throws SQLException {
        doNothing().when(driverDao).addDriver(new Driver());
        driverService.addDriver(new Driver());
        verify(driverDao).addDriver(new Driver());
    }

    @Test
    void addDriverWithException() throws SQLException {
        doThrow(SQLException.class).when(driverDao).addDriver(new Driver());
        driverService.addDriver(new Driver());
        verify(driverDao).addDriver(new Driver());
    }

    @Test
    void updateDriver() {
        doNothing().when(driverDao).updateDriver(new Driver());
        driverService.updateDriver(new Driver());
        verify(driverDao).updateDriver(new Driver());
    }

    @Test
    void removeDriver() {
        doNothing().when(driverDao).removeDriver(0);
        driverService.removeDriver(0);
        verify(driverDao).removeDriver(0);
    }
}