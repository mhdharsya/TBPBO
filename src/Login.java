import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
public class Login extends Database {
    String email;
    String username;
    String password;
    String emailBenar;
    String usernameBenar;
    String passwordBenar;

    Date date = new Date();
    ManageBarang mb = new ManageBarang();
    SimpleDateFormat tanggal = new SimpleDateFormat("E dd/mm/yyyy");
    SimpleDateFormat jam = new SimpleDateFormat("hh:mm:ss");
    Scanner scanner = new Scanner(System.in);
    void showMenu(){
        connect();
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
                    break;
                }
            } catch (NumberFormatException e){
                System.out.println("Silahkan Masukkan Angka!");
                System.out.print("Pilih = ");
            } 
        }
    }

    void register() {
        while (true){
        try {
        System.out.println("====================");

        System.out.print("Masukkan Username : ");
        username = scanner.nextLine();

        System.out.print("Masukkan Password : ");
        password = scanner.nextLine();
         
        String sql = "INSERT into user (username, password) VALUE ('%s', '%s')";
        sql = String.format(sql, username, password);
        stmt.execute(sql);  
        break;
        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Username Sudah Digunakan!");
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
                username = rs.getString("username");
                password = rs.getString("password");
            }
        while(true){
            System.out.println("====================");
    
            System.out.print("Masukkan Username : ");
            usernameBenar = scanner.nextLine();
    
            System.out.print("Masukkan Password : ");
            passwordBenar = scanner.nextLine();

            if (usernameBenar.equals(String.format("%s", username)) && passwordBenar.equals(String.format("%s", password))){
            System.out.println("====================");
            System.out.println(tanggal.format(date) + " " + jam.format(date));
            System.out.println("Selamat Datang " + String.format("%s", username) + "!");
            break;
            } else{
            System.out.println("Username atau Password Salah!");
            }
        }
            } catch (Exception e){
                e.printStackTrace();
            }
        mb.tampilMenu();
    }
}

