import java.util.Scanner;

public class ManageBarang extends Barang implements Crud {
    Scanner scanner = new Scanner(System.in);

    @Override
    void connect() {
        super.connect();
    }

    void tampilMenu(){
        connect();
        Integer pilih = 1;

        System.out.println("=========SISTEM INVENTARIS BARANG=========");
        System.out.println("1. Tambah Barang");
        System.out.println("2. Lihat Barang");
        System.out.println("3. Tambah Jumlah Barang");
        System.out.println("4. Kurang Jumlah Barang");
        System.out.println("5. Hapus Barang");
        System.out.println("0. Exit");
        System.out.println("");
        System.out.print("Pilih = ");

        while (pilih !=0){
            try{  
                pilih = Integer.parseInt(scanner.nextLine());
            switch (pilih){
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    tambahBarang();
                    break;
                case 2:
                    lihatBarang();
                    break;
                case 3:
                    tambahJumlahBarang();
                    break;
                case 4:
                    kurangJumlahBarang();
                    break;
                case 5:
                    hapusBarang();
                    break;
                default:
                    System.out.println("Pilihan Salah!");
                    System.out.println("Pilih = ");
            }
            } catch (NumberFormatException e){
                System.out.println("Silahkan Masukkan Angka!");
                System.out.println("Pilih = ");
            } break;
        }
    }

    @Override
    public void tambahBarang() {
        try{
        System.out.println("=================");
        System.out.print("Masukkan Id Barang : ");
        idBarang = scanner.nextLine();

        System.out.print("Masukkan Nama Barang : ");
        namaBarang = scanner.nextLine();

        System.out.print("Masukkan Jumlah Barang : ");
        jumlahBarang = scanner.nextInt();

        String sql = "INSERT into barang (id_barang, nama_barang, jumlah_barang) VALUES ('%s', '%s', '%s')";
        sql = String.format(sql, idBarang, namaBarang, jumlahBarang);
        stmt.execute(sql);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void lihatBarang() {
        String sql = "SELECT * from barang";

        try {
            rs = stmt.executeQuery(sql);

            System.out.println("=================");
            System.out.println("   Data Barang     ");
            System.out.println("=================");
            System.out.println(" Id Barang     Nama Barang    Jumlah Barang");

            while (rs.next()){
                String idBarang = rs.getString("id_barang");
                String namaBarang = rs.getString("nama_barang");
                Integer jumlahBarang = rs.getInt("jumlah_barang");

                System.out.println(String.format(" %s          %s                %d", idBarang, namaBarang, jumlahBarang));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void kurangJumlahBarang(){
        String sql = "SELECT * from barang";
        try {
            rs = stmt.executeQuery(sql);

            while (rs.next()){
                jumlahBarang = rs.getInt("jumlah_barang");
                System.out.println("============");

                System.out.print("Masukkan Id Barang Yang Ingin Di Kurangi = ");
                idBarang = scanner.nextLine();

                System.out.print("Jumlah Barang Yang Ingin Di Kurangi = ");
                Integer kurangBarang = jumlahBarang - scanner.nextInt();

                String sql2 = "UPDATE barang SET jumlah_barang=%d WHERE id_barang='%s'";
                sql2 = String.format(sql2, kurangBarang, idBarang);
                stmt.executeUpdate(sql2);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void tambahJumlahBarang(){

    }

    public void hapusBarang() {
        try{
            System.out.print("Id Barang Yang Ingin Di Hapus = ");
            String idBarang = scanner.nextLine();

            String sql = "DELETE FROM barang WHERE id_barang='%s'";
            sql = String.format(sql, idBarang);
            stmt.executeUpdate(sql);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
