public class App {
    public static void main(String[] args) throws Exception {
        Login login = new Login();
        ManageBarang barang = new ManageBarang();

        login.showMenu();
        barang.tampilMenu();
    }
}
