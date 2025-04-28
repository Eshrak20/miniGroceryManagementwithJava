import java.sql.Connection;

public class TestDBConnection {
    public static void main(String[] args) {
        // Attempt to get a connection to the database
        Connection con = DBConnection.getConnection();
        
        // Check if the connection was successful
        if (con != null) {
            System.out.println("Connected to the database successfully!");
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
