import java.sql.*;
import java.util.Scanner;
public class Login {
    String email;
    String username;
    String password;
    String emailBenar;
    String usernameBenar;
    String passwordBenar;

    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String DB_URL = "jdbc:mysql://localhost/minimarket";
    String user = "root";
    String pass = "";
    Connection conn;
    Statement stmt;
    ResultSet rs;

    Scanner scanner = new Scanner(System.in);
    void showMenu(){
        Integer pilih = 1;

        System.out.println("=========Login/Register=========");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("0. Exit");
        System.out.println("");
        System.out.print("Pilih = ");

        while (pilih !=0){
        try {
            pilih = Integer.parseInt(scanner.nextLine());
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, user, pass);
            stmt = conn.createStatement();

            switch (pilih){
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                default:
                    System.out.println("Pilihan Salah!");
                    System.out.print("Pilih = ");
                }
            } catch (NumberFormatException e){
                System.out.println("Silahkan Masukkan Angka!");
                System.out.print("Pilih = ");

            } catch (SQLException e){
                e.printStackTrace();

            } catch (ClassNotFoundException e){
                e.printStackTrace();
            } break;
        }
    }

    void register() {
        while (true){
        try {
        System.out.println("====================");

        System.out.print("Masukkan Email : ");
        email = scanner.nextLine();

        System.out.print("Masukkan Username : ");
        username = scanner.nextLine();

        System.out.print("Masukkan Password : ");
        password = scanner.nextLine();
         
        String sql = "INSERT into user (email, username, password) VALUE ('%s', '%s', '%s')";
        sql = String.format(sql, email, username, password);
        stmt.execute(sql);  
        break;
        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Email Sudah Digunakan!");
        } catch (SQLException e){
            e.printStackTrace();
            break;    
        } 
    } showMenu();
}

    void login(){
        String sql = "SELECT * from user";
        try {
            rs = stmt.executeQuery(sql);

            while (rs.next()){
                email = rs.getString("email");
                username = rs.getString("username");
                password = rs.getString("password");
            }
        while(true){
            System.out.println("====================");
    
            System.out.print("Masukkan Email : ");
            emailBenar = scanner.nextLine();
    
            System.out.print("Masukkan Password : ");
            passwordBenar = scanner.nextLine();

            if (emailBenar.equals(String.format("%s", email)) && passwordBenar.equals(String.format("%s", password))){
            System.out.println("Selamat Datang " + String.format("%s", username) + "!");
            break;
            } else{
            System.out.println("Username atau Password Salah!");
            }
        }
            } catch (Exception e){
                e.printStackTrace();
            }
        
    }
}

