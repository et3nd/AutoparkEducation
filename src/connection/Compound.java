package connection;

import java.io.IOException;
import java.nio.charset.*;
import java.nio.file.*;
import java.sql.*;

import org.slf4j.*;

class Compound {
    private static final String URL = "jdbc:h2:file:/home/alex/IdeaProjects/AutoparkEducation/auto park database";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static final Logger log = LoggerFactory.getLogger(Compound.class);

    void interactWithDatabase() {
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(writeDataFromFile())) {
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
            Files.lines(Paths.get("src/resources/db/table-creation-script.sql"),
                    StandardCharsets.UTF_8).forEach(sqlScript::append);
            return sqlScript.toString();
        } catch (IOException e) {
            log.error("Error: ", e);
            return "";
        }
    }
}