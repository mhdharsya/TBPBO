import java.sql.*;
public class Login {
    String email;
    String username;
    String password;
    String JDBC_DRIVER = "com.mysql.jdbc.minimarket";
    String DB_URL = "jdbc:mysql://localhost/minimarket";
    String user = "root";
    String pass = "";
    Connection conn;
    Statement stmt;
    ResultSet rs;
}
