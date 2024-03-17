package assignments.assignment1;

import java.util.Scanner;

public class OrderGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String orderId = "";

        while (true) {
            showMenu();
            System.out.print("Pilihan menu: ");
            int menu = scanner.nextInt();

            switch (menu) {
                case 1:
                    orderId = generateOrderId(scanner);
                    System.out.println("Order ID " + orderId + " diterima!");
                    break;
                case 2:
                    generateBill(orderId, scanner);
                    break;
                case 3:
                    System.out.println("Terima kasih telah menggunakan DepeFood!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }
        }
    }

    public static void showMenu() {
        System.out.println(">>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.out.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
        System.out.println();
        System.out.println("Pilih menu:");
        System.out.println("1. Generate Order ID");
        System.out.println("2. Generate Bill");
        System.out.println("3. Lihat Daftar Makanan");
        System.out.println("4. Keluar");
    }

    public static String generateOrderId(Scanner scanner) {
        System.out.print("Nama Restoran: ");
        String restoran = scanner.next();
        if (restoran.length() < 4) {
            System.out.println("Nama restoran tidak valid (minimal 4 huruf)!");
            return "";
        }
        restoran = restoran.substring(0, 4).toUpperCase();

        System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
        String tanggal = scanner.next();
        if (!tanggal.matches("\\d{2}/\\d{2}/\\d{4}")) {
            System.out.println("Format tanggal tidak valid!");
            return "";
        }
        tanggal = tanggal.replaceAll("/", "");

        System.out.print("Nomor Telepon User: ");
        String telepon = scanner.next();
        if (!telepon.matches("\\d+")) {
            System.out.println("Nomor telepon tidak valid!");
            return "";
        }
        int sum = 0;
        for (int i = 0; i < telepon.length(); i++) {
            sum += Integer.parseInt(String.valueOf(telepon.charAt(i)));
        }
        int lastTwoDigits = sum % 100;
        String formattedLastTwoDigits = String.format("%02d", lastTwoDigits);

        String orderId = restoran + tanggal + formattedLastTwoDigits;

        int checksum1 = 0, checksum2 = 0;
        for (int i = 0; i < orderId.length(); i++) {
            int numericValue = getNumericValue(orderId.charAt(i));
            if ((i + 1) % 2 == 0) {
                checksum1 += numericValue;
            } else {
                checksum2 += numericValue;
            }
        }
        checksum1 %= 36;
        checksum2 %= 36;

        char checksum1Char = getCharValue(checksum1);
        char checksum2Char = getCharValue(checksum2);

        orderId += String.valueOf(checksum1Char) + String.valueOf(checksum2Char);

        return orderId;
    }

    public static void generateBill(String orderId, Scanner scanner) {
        if (orderId.isEmpty()) {
            System.out.println("Silahkan masukkan Order ID yang valid!");
            return;
        }

        System.out.print("Lokasi Pengiriman: ");
        String lokasi = scanner.next().toUpperCase();

        int biayaOngkir;
        switch (lokasi) {
            case "P":
                biayaOngkir = 10000;
                break;
            case "U":
                biayaOngkir = 20000;
                break;
            case "T":
                biayaOngkir = 35000;
                break;
            case "S":
                biayaOngkir = 40000;
                break;
            case "B":
                biayaOngkir = 60000;
                break;
            default:
                System.out.println("Harap masukkan lokasi pengiriman yang ada pada jangkauan!");
                return;
        }

        System.out.println("Bill:");
        System.out.println("Order ID: " + orderId);
        System.out.println("Tanggal Pemesanan: " + orderId.substring(4, 12).replaceAll("(\\d{2})(\\d{2})(\\d{4})", "$1/$2/$3"));
        System.out.println("Lokasi Pengiriman: " + lokasi);
        System.out.println("Biaya Ongkos Kirim: Rp " + biayaOngkir);
    }

    public static int getNumericValue(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        } else if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 10;
        }
        return -1;
    }

    public static char getCharValue(int n) {
        if (n >= 0 && n <= 9) {
            return (char) (n + '0');
        } else if (n >= 10 && n <= 35) {
            return (char) (n - 10 + 'A');
        }
        return '-';
    }
}
