import java.sql.*;
public class DatabaseConnector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/fitness";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Test connecting to the database
        System.out.println("Connecting to the database...");
        Connection connection = DatabaseConnector.connect();
        if (connection != null) {
            System.out.println("Connected successfully!");
        } else {
            System.out.println("Failed to connect to the database.");
        }

        // Test closing the connection
        System.out.println("Closing the connection...");
        DatabaseConnector.closeConnection(connection);
        System.out.println("Connection closed.");
    }
}
