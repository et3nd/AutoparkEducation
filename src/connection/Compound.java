package connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class Compound {
    private static final String SCRIPT = "src/resources/db/table-creation-script.sql";
    private static final String URL = "jdbc:h2:file:/home/alex/IdeaProjects/AutoparkEducation/auto park database";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static final Logger log = LoggerFactory.getLogger(Compound.class);

    void interactWithDatabase() {
        String script = writeDataFromFile();
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script)) {
            log.info("Connection to the database was successful");
            preparedStatement.execute();
            log.info("Database has been initialized successfully");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    private String writeDataFromFile() {
        StringBuilder sqlScript = new StringBuilder();
        try {
            Files.lines(Paths.get(SCRIPT),
                    StandardCharsets.UTF_8).forEach(sqlScript::append);
            return sqlScript.toString();
        } catch (IOException e) {
            log.error("Error: ", e);
            return "";
        }
    }
}