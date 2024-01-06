import java.sql.*;

public class Database {
    String JDBC_DRIVER = "com.mysql.jdbc.minimarket";
    String DB_URL = "jdbc:mysql://localhost/minimarket";
    String user = "root";
    String pass = "";
    Connection conn;
    Statement stmt;
    ResultSet rs;

    try {
    Class.forName(JDBC_DRIVER);
    conn = DriverManager.getConnection(DB_URL, user, pass);
    stmt = conn.createStatement();
    } catch (Exception e){
        e.printStackTrace();
    }
}
