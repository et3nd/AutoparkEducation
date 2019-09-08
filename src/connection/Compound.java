package connection;

import java.io.IOException;
import java.nio.charset.*;
import java.nio.file.*;
import java.sql.*;
import java.util.List;

import org.slf4j.*;

class Compound {
    private static final String URL = "jdbc:h2:file:/home/alex/IdeaProjects/AutoparkEducation/auto park database";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static StringBuilder sqlScript = new StringBuilder();
    private final Logger log = LoggerFactory.getLogger(Compound.class);

    void interactWithDatabase() {
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             final PreparedStatement preparedStatement = connection.prepareStatement(writeDataFromFile())) {
            log.info("Connection to the database was successful");
            preparedStatement.execute();
            log.info("Database initialization successful");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    private String writeDataFromFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("db/java.sql"), StandardCharsets.UTF_8);
            for (String line : lines) {
                sqlScript.append(line);
            }
            return sqlScript.toString();
        } catch (IOException e) {
            log.error("Error: ", e);
            return "";
        }
    }
}