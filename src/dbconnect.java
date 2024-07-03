import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnect {

    private static String HOST = "localhost";
    private static int PORT = 5432;
    private static String DATABASE = "TaskManager";
    private static String USER = "postgres";
    private static String PASS = "123";

    public static void setCredentials(String host, int port, String database, String user, String pass) {
        HOST = host;
        PORT = port;
        DATABASE = database;
        USER = user;
        PASS = pass;
    }

    public static Connection open() {
        String url = String.format("jdbc:postgresql://%s:%d/%s", HOST, PORT, DATABASE);
        try {
            Connection con = DriverManager.getConnection(url, USER, PASS);
            return con;
        } catch (SQLException e) {
            System.out.println("Error Occurred. Cannot Establish Connection. Please inform Admin.");
            throw new RuntimeException(e);
        }
    }

    public static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Failed to close connection: " + e.getMessage());
            }
        }
    }

}
