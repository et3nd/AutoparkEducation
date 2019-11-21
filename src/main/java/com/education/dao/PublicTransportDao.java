package com.education.dao;

import com.education.entity.PublicTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class PublicTransportDao extends EntityDao {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger log = LoggerFactory.getLogger(PublicTransportDao.class);
    private static final String ADD_TRANSPORT_SCRIPT = "/db/add-transport-script.sql";
    private static final String UPDATE_TRANSPORT_SCRIPT = "/db/update-transport-script.sql";
    private static final String REMOVE_TRANSPORT_SCRIPT = "/db/remove-transport-script.sql";
    private static final String GET_TRANSPORT_SCRIPT = "/db/get-transport-script.sql";

    @Autowired
    public PublicTransportDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addPublicTransport(PublicTransport transport) throws SQLException {
        String script = getInitializationScript(PublicTransportDao.class.getResourceAsStream(ADD_TRANSPORT_SCRIPT));
        try {
            log.info("Connection to the database was successful");
            if (transport.getTransportNumber() == 0) throw new SQLException("Zero identifier");
            jdbcTemplate.update(script,
                    transport.getTransportNumber(),
                    transport.getBusBrand(),
                    transport.getCapacity(),
                    transport.getIssueYear());
            log.info("Transport add was successful");
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void updatePublicTransport(PublicTransport transport) {
        String script = getInitializationScript(PublicTransportDao.class.getResourceAsStream(UPDATE_TRANSPORT_SCRIPT));
        log.info("Connection to the database was successful");
        jdbcTemplate.update(script,
                transport.getBusBrand(),
                transport.getCapacity(),
                transport.getIssueYear(),
                transport.getTransportNumber());
        log.info("Transport update was successful");
    }

    public void removePublicTransport(int transportNumber) {
        String script = getInitializationScript(PublicTransportDao.class.getResourceAsStream(REMOVE_TRANSPORT_SCRIPT));
        log.info("Connection to the database was successful");
        jdbcTemplate.update(script, transportNumber);
        log.info("Transport remove was successful");
    }

    public PublicTransport getPublicTransport(int transportNumber) {
        String script = getInitializationScript(PublicTransportDao.class.getResourceAsStream(GET_TRANSPORT_SCRIPT));
        log.info("Connection to the database was successful");
        PublicTransport transport = jdbcTemplate.queryForObject(script,
                new Integer[]{transportNumber},
                new BeanPropertyRowMapper<>(PublicTransport.class));
        log.info("Read: \n" + transport);
        return transport;
    }
}
