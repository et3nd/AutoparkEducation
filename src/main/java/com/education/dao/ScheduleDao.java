package com.education.dao;

import com.education.entity.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class ScheduleDao extends EntityDao {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger log = LoggerFactory.getLogger(ScheduleDao.class);
    private static final String ADD_SCHEDULE_SCRIPT = "/db/add-schedule-script.sql";
    private static final String UPDATE_SCHEDULE_SCRIPT = "/db/update-schedule-script.sql";
    private static final String REMOVE_SCHEDULE_SCRIPT = "/db/remove-schedule-script.sql";
    private static final String GET_SCHEDULE_SCRIPT = "/db/get-schedule-script.sql";

    @Autowired
    public ScheduleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addSchedule(Schedule schedule) throws SQLException {
        String script = getInitializationScript(ScheduleDao.class.getResourceAsStream(ADD_SCHEDULE_SCRIPT));
        try {
            log.info("Connection to the database was successful");
            if (schedule.getId() == 0) throw new SQLException("Zero identifier");
            jdbcTemplate.update(script,
                    schedule.getId(),
                    schedule.getDepartureTime(),
                    schedule.getArrivalTime());
            log.info("Schedule add was successful");
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void updateSchedule(Schedule schedule) {
        String script = getInitializationScript(ScheduleDao.class.getResourceAsStream(UPDATE_SCHEDULE_SCRIPT));
        log.info("Connection to the database was successful");
        jdbcTemplate.update(script,
                schedule.getDepartureTime(),
                schedule.getArrivalTime(),
                schedule.getId());
        log.info("Schedule update was successful");
    }

    public void removeSchedule(int id) {
        String script = getInitializationScript(ScheduleDao.class.getResourceAsStream(REMOVE_SCHEDULE_SCRIPT));
        log.info("Connection to the database was successful");
        jdbcTemplate.update(script, id);
        log.info("Schedule remove was successful");
    }

    public Schedule getSchedule(int id) {
        String script = getInitializationScript(ScheduleDao.class.getResourceAsStream(GET_SCHEDULE_SCRIPT));
        log.info("Connection to the database was successful");
        Schedule schedule = jdbcTemplate.queryForObject(script,
                new Integer[]{id},
                new BeanPropertyRowMapper<>(Schedule.class));
        log.info("Read: \n" + schedule);
        return schedule;
    }
}
