import java.sql.*;
import java.util.Scanner;
import java.util.LinkedHashSet;
import java.util.Set;

public class ManageBarang extends Database implements Method {
    String namaBarang;
    Integer jumlahBarang;
    Scanner scanner = new Scanner(System.in);
    Set<String> linkedHashSet = new LinkedHashSet<>();

    @Override
    void connect() {
        super.connect();
    }

    void tampilMenu(){
        Integer pilih = 1;

        System.out.println("=========SISTEM INVENTARIS BARANG=========");
        System.out.println("1. Tambah Barang");
        System.out.println("2. List Barang");
        System.out.println("3. Lihat Jumlah Barang");
        System.out.println("4. Tambah Jumlah Barang");
        System.out.println("5. Kurang Jumlah Barang");
        System.out.println("6. Hapus Barang");
        System.out.println("0. Exit");
        System.out.println("");
        System.out.print("Pilih = ");

        while (pilih !=0){ 
            connect();
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
                    listBarang();
                    break;
                case 3:
                    lihatBarang();
                    break;
                case 4:
                    tambahJumlahBarang();
                    break;
                case 5:
                    kurangJumlahBarang();
                    break;
                case 6:
                    hapusBarang();
                    break;
                default:
                    System.out.println("Pilihan Salah!");
                    System.out.print("Pilih = ");
                    break;
            }
            } catch (NumberFormatException e){
                System.out.println("Silahkan Masukkan Angka!");
                System.out.print("Pilih = ");
                // break;
            } 
        }
    }

    @Override
    public void tambahBarang() {
        try{
        System.out.println("=================");

        System.out.print("Masukkan Nama Barang : ");
        namaBarang = scanner.nextLine();

        System.out.print("Masukkan Jumlah Barang : ");
        jumlahBarang = scanner.nextInt();


        String sql = "INSERT into barang (nama_barang, jumlah_barang) VALUES ('%s', '%s')";
        sql = String.format(sql, namaBarang, jumlahBarang);
        stmt.execute(sql);
        linkedHashSet.add(namaBarang);
        System.out.println("Data Berhasil Di Tambahkan!");

        System.out.println(" ");

        while(true){
        System.out.print("Ingin kembali? (Y/N) : ");
        scanner.nextLine();
        String kembali = scanner.nextLine();

        if (kembali.equalsIgnoreCase("y")){
            System.out.println(" ");
            tampilMenu();
            break;
        }
        }

        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Barang Sudah Ada!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void listBarang(){
        String sql = "SELECT * from barang";
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                String namaBarang = rs.getString("nama_barang");
                linkedHashSet.add(namaBarang);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("=========LIST BARANG=========");
        if (linkedHashSet.isEmpty()){
            System.out.println("Tidak Ada Barang!");
        } else {
            System.out.println("List Barang : " + linkedHashSet);
        }

        while (true){
        System.out.println(" ");
        System.out.print("Ingin kembali? (Y/N) : ");
        String kembali = scanner.nextLine();
        if (kembali.equalsIgnoreCase("y")){
            System.out.println(" ");
            tampilMenu();
        }
    }
    }

    public void lihatBarang() {
        String sql = "SELECT * from barang";

        try {
            rs = stmt.executeQuery(sql);

            System.out.println("=================");
            System.out.println("   Data Barang     ");
            System.out.println("=================");
            System.out.println(" Nama Barang    Jumlah Barang");

            while (rs.next()){
                String namaBarang = rs.getString("nama_barang");
                Integer jumlahBarang = rs.getInt("jumlah_barang");

                System.out.println(String.format(" %s                %d", namaBarang, jumlahBarang));
                System.out.println(" ");
            }

            while (true){
                System.out.println(" ");
                System.out.print("Ingin kembali? (Y/N) : ");
                String kembali = scanner.nextLine();
            if (kembali.equalsIgnoreCase("y")){
                System.out.println(" ");
                tampilMenu();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void kurangJumlahBarang(){
        try {
            String sql = "SELECT * from barang";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                jumlahBarang = rs.getInt("jumlah_barang");
                System.out.println("============");

                System.out.print("Masukkan Nama Barang Yang Ingin Di Kurangi = ");
                namaBarang = scanner.nextLine();

                System.out.print("Jumlah Barang Yang Ingin Di Kurangi = ");
                Integer kurangBarang = jumlahBarang - scanner.nextInt();

                try {
                Statement stmt2 = conn.createStatement();
                String sql2 = "UPDATE barang SET jumlah_barang=%d WHERE nama_barang='%s'";
                sql2 = String.format(sql2, kurangBarang, namaBarang);
                stmt2.executeUpdate(sql2);

                System.out.println("Data Berhasil Dikurangi!");

            while (true){
                System.out.println(" ");
                System.out.print("Ingin kembali? (Y/N) : ");
                scanner.nextLine();
                String kembali = scanner.nextLine();
            if (kembali.equalsIgnoreCase("y")){
                System.out.println(" ");
                tampilMenu();
                }
            } 

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        tampilMenu();
    }

    public void tambahJumlahBarang(){
        try {
            String sql = "SELECT * from barang";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                jumlahBarang = rs.getInt("jumlah_barang");
                System.out.println("============");

                System.out.print("Masukkan Nama Barang Yang Ingin Di Tambah = ");
                namaBarang = scanner.nextLine();

                System.out.print("Jumlah Barang Yang Ingin Di Tambah = ");
                Integer tambahBarang = jumlahBarang + scanner.nextInt();

                try {
                Statement stmt2 = conn.createStatement();
                String sql2 = "UPDATE barang SET jumlah_barang=%d WHERE nama_barang='%s'";
                sql2 = String.format(sql2, tambahBarang, namaBarang);
                stmt2.executeUpdate(sql2);

                System.out.println("Data Berhasil Di Tambahkan!");

                while (true){
                System.out.println(" ");
                System.out.print("Ingin kembali? (Y/N) : ");
                scanner.nextLine();
                String kembali = scanner.nextLine();
            if (kembali.equalsIgnoreCase("y")){
                System.out.println(" ");
                tampilMenu();
                }
            }

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        tampilMenu();
    }

    public void hapusBarang() {
        try{
            System.out.print("Nama Barang Yang Ingin Di Hapus = ");
            String namaBarang = scanner.nextLine();

            String sql = "DELETE FROM barang WHERE nama_barang='%s'";
            sql = String.format(sql, namaBarang);
            stmt.executeUpdate(sql);

            linkedHashSet.remove(namaBarang);

            System.out.println("Data Berhasil Di Hapus!");

        while (true){
            System.out.println(" ");
            System.out.print("Ingin kembali? (Y/N) : ");
            String kembali = scanner.nextLine();
        if (kembali.equalsIgnoreCase("y")){
            System.out.println(" ");
            tampilMenu();
            }
        }

        } catch (Exception e){
            e.printStackTrace();
        }
        tampilMenu();
    }

}
