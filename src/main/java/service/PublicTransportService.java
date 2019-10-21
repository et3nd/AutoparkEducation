package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.PublicTransportDao;
import entity.PublicTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PublicTransportService {
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger log = LoggerFactory.getLogger(PublicTransportService.class);
    private PublicTransportDao publicTransportDao;

    public void setPublicTransportDao(PublicTransportDao publicTransportDao) {
        this.publicTransportDao = publicTransportDao;
    }

    public String addPublicTransport(String input) {
        try {
            PublicTransport transport = new ObjectMapper().setDateFormat(df).readValue(input, PublicTransport.class);
            publicTransportDao.addPublicTransport(transport);
            return "Success";
        } catch (SQLException e) {
            return e.getMessage();
        } catch (IOException e) {
            log.error("Error: ", e);
            return e.getMessage();
        }
    }

    public String updatePublicTransport(String input) {
        try {
            PublicTransport inputTransport = new ObjectMapper().setDateFormat(df).readValue(input, PublicTransport.class);
            PublicTransport outputTransport = publicTransportDao.getPublicTransport(inputTransport.getTransportNumber());
            if (inputTransport.equals(outputTransport)) throw new SQLException("Same values");
            if (!(inputTransport.getBusBrand().equals("default")))
                outputTransport.setBusBrand(inputTransport.getBusBrand());
            if (!(inputTransport.getCapacity() == 0)) outputTransport.setCapacity(inputTransport.getCapacity());
            if (!(inputTransport.getIssueYear() == 0)) outputTransport.setIssueYear(inputTransport.getIssueYear());
            publicTransportDao.updatePublicTransport(outputTransport);
            return "Success";
        } catch (SQLException e) {
            return e.getMessage();
        } catch (IOException e) {
            log.error("Error: ", e);
            return e.getMessage();
        }
    }

    public String removePublicTransport(int transportNumber) {
        try {
            publicTransportDao.getPublicTransport(transportNumber);
            publicTransportDao.removePublicTransport(transportNumber);
            return "Success";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String getPublicTransport(int transportNumber) {
        try {
            PublicTransport transport = publicTransportDao.getPublicTransport(transportNumber);
            return new ObjectMapper().setDateFormat(df).writeValueAsString(transport);
        } catch (SQLException e) {
            return e.getMessage();
        } catch (JsonProcessingException e) {
            log.error("Error: ", e);
            return e.getMessage();
        }
    }
}
