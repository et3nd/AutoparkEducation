package connection;

import java.io.*;
import java.sql.*;

class Compound {

    private static final String URL = "jdbc:h2:file:/home/alex/IdeaProjects/Databases";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static StringBuilder sb = new StringBuilder();
    private static Connection connection;
    private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Compound.class);

    void runProgramCode() {
        sendCommandsForDatabase();
    }

    private void sendCommandsForDatabase() {
        try {
            connectToDatabase();
            PreparedStatement preparedStatement = connection.prepareStatement(readSQLScriptFromFile());
            preparedStatement.execute();
            connection.close();
            log.info("Has been completed.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private String readSQLScriptFromFile() throws IOException {
        BufferedReader in = new BufferedReader(
                new FileReader("/home/alex/IdeaProjects/AutoparkEducation/src/resources/java.sql"));
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        in.close();
        return sb.toString();
    }

    private void connectToDatabase() throws SQLException {
        connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        log.info("Has been initialized.");
    }

}