package connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class Compound {
    private static final String SCRIPT = "/db/table-creation-script.sql";
    private static final String URL = "jdbc:h2:file:/home/alex/IdeaProjects/Education/autopark-database";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static final Logger log = LoggerFactory.getLogger(Compound.class);

    void initializeDataBase() {
        String initializationScript = getInitializationScript();
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(initializationScript)) {
            log.info("Connection to the database was successful");
            preparedStatement.execute();
            log.info("Database has been initialized successfully");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    private String getInitializationScript() {
        StringBuilder sqlScript = new StringBuilder();
        try {
            InputStream stream = Compound.class.getResourceAsStream(SCRIPT);
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