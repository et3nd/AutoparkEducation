package com.education.dao;

import com.education.entity.Driver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
@Slf4j
public class DriverDao extends EntityDao {
    private final JdbcTemplate jdbcTemplate;
    private static final String ADD_DRIVER_SCRIPT = "/db/add-driver-script.sql";
    private static final String UPDATE_DRIVER_SCRIPT = "/db/update-driver-script.sql";
    private static final String REMOVE_DRIVER_SCRIPT = "/db/remove-driver-script.sql";
    private static final String GET_DRIVER_SCRIPT = "/db/get-driver-script.sql";

    @Autowired
    public DriverDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addDriver(Driver driver) throws SQLException {
        String script = getInitializationScript(DriverDao.class.getResourceAsStream(ADD_DRIVER_SCRIPT));
        try {
            log.info("Connection to the database was successful");
            if (driver.getLicense() == 0) throw new SQLException("Zero identifier");
            jdbcTemplate.update(script,
                    driver.getLicense(),
                    driver.getFio(),
                    driver.getSalary(),
                    driver.getAddress(),
                    driver.getBirthDate());
            log.info("Driver add was successful");
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void updateDriver(Driver driver) {
        String script = getInitializationScript(DriverDao.class.getResourceAsStream(UPDATE_DRIVER_SCRIPT));
        log.info("Connection to the database was successful");
        jdbcTemplate.update(script,
                driver.getFio(),
                driver.getSalary(),
                driver.getAddress(),
                driver.getBirthDate(),
                driver.getLicense());
        log.info("Driver update was successful");
    }

    public void removeDriver(int license) {
        String script = getInitializationScript(DriverDao.class.getResourceAsStream(REMOVE_DRIVER_SCRIPT));
        log.info("Connection to the database was successful");
        jdbcTemplate.update(script, license);
        log.info("Driver remove was successful");
    }

    public Driver getDriver(int license) {
        String script = getInitializationScript(DriverDao.class.getResourceAsStream(GET_DRIVER_SCRIPT));
        log.info("Connection to the database was successful");
        Driver driver = jdbcTemplate.queryForObject(script,
                new Integer[]{license},
                new BeanPropertyRowMapper<>(Driver.class));
        log.info("Read: \n" + driver);
        return driver;
    }
}
