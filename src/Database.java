import java.sql.*;

public class Database {
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String DB_URL = "jdbc:mysql://localhost/inventaris";
    String user = "root";
    String pass = "";
    Connection conn;
    Statement stmt;
    ResultSet rs;

    void connect(){
        try {
        conn = DriverManager.getConnection(DB_URL, user, pass);
        stmt = conn.createStatement();
        } catch (Exception e){
            e.printStackTrace();
        }
    }   
}
