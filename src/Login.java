import java.sql.SQLException;
import java.util.Scanner;
public class Login extends Database {
    String email;
    String username;
    String password;

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
            }
        }
    }

    void register() {
        System.out.println("====================");

        System.out.print("Masukkan Email : ");
        email = scanner.nextLine();

        System.out.print("Masukkan Username : ");
        username = scanner.nextLine();

        System.out.print("Masukkan Password : ");
        password = scanner.nextLine();

        try {
        String sql = "INSERT into user (email, username, password) VALUE ('%s', '%s', '%s')";
            sql = String.format(sql, email, username, password);
            stmt.execute(sql);
            
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    void login(){

    }
}
