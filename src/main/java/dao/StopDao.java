package dao;

import entity.Stop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class StopDao extends EntityDao {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger log = LoggerFactory.getLogger(StopDao.class);
    private static final String ADD_STOP_SCRIPT = "/db/add-stop-script.sql";
    private static final String UPDATE_STOP_SCRIPT = "/db/update-stop-script.sql";
    private static final String REMOVE_STOP_SCRIPT = "/db/remove-stop-script.sql";
    private static final String GET_STOP_SCRIPT = "/db/get-stop-script.sql";

    @Autowired
    public StopDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addStop(Stop stop) throws SQLException {
        String script = getInitializationScript(StopDao.class.getResourceAsStream(ADD_STOP_SCRIPT));
        try {
            log.info("Connection to the database was successful");
            if (stop.getStopName().equals("default")) throw new SQLException("Zero identifier");
            jdbcTemplate.update(script,
                    stop.getStopName(),
                    stop.getDirection(),
                    stop.getArrivalTimeOnStop());
            log.info("Stop add was successful");
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void updateStop(Stop stop) {
        String script = getInitializationScript(StopDao.class.getResourceAsStream(UPDATE_STOP_SCRIPT));
        log.info("Connection to the database was successful");
        jdbcTemplate.update(script,
                stop.getDirection(),
                stop.getArrivalTimeOnStop(),
                stop.getStopName());
        log.info("Stop update was successful");
    }

    public void removeStop(String stopName) {
        String script = getInitializationScript(StopDao.class.getResourceAsStream(REMOVE_STOP_SCRIPT));
        log.info("Connection to the database was successful");
        jdbcTemplate.update(script, stopName);
        log.info("Stop remove was successful");
    }

    public Stop getStop(String stopName) {
        String script = getInitializationScript(StopDao.class.getResourceAsStream(GET_STOP_SCRIPT));
        log.info("Connection to the database was successful");
        Stop stop = jdbcTemplate.queryForObject(script,
                new String[]{stopName},
                new BeanPropertyRowMapper<>(Stop.class));
        log.info("Read: \n" + stop);
        return stop;
    }
}
