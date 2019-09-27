package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

abstract class DAO {
    private static final Logger log = LoggerFactory.getLogger(DAO.class);
    static final String URL = "jdbc:h2:file:/home/alex/IdeaProjects/Education/autopark-database";
    static final String LOGIN = "root";
    static final String PASSWORD = "root";

    String getInitializationScript(InputStream stream) {
        StringBuilder sqlScript = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            while (reader.ready()) {
                sqlScript.append(reader.readLine());
            }
            return sqlScript.toString();
        } catch (IOException e) {
            log.error("Error: ", e);
            return "";
        }
    }
}
