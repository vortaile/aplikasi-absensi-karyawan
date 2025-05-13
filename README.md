# Aplikasi Absensi Karyawan

Aplikasi Absensi Karyawan adalah sistem berbasis desktop yang dirancang untuk membantu perusahaan atau organisasi dalam mencatat kehadiran karyawan secara efisien. Aplikasi ini memungkinkan karyawan untuk login, melakukan check-in/check-out, dan menghasilkan laporan bulanan dalam format Excel. Proyek ini dikembangkan sebagai bagian dari tugas akademik untuk membantu memahami pengembangan aplikasi menggunakan Java.

## **Fitur Utama**
- **Login dan Registrasi**: Karyawan dapat login menggunakan ID dan password. Jika belum memiliki akun, mereka dapat mendaftar langsung melalui aplikasi.
- **Absensi Harian**: Karyawan dapat mencatat waktu masuk (check-in) dan keluar (check-out) setiap hari.
- **Laporan Bulanan**: Aplikasi dapat menghasilkan laporan absensi bulanan dalam format Excel, yang mencakup total kehadiran, absen, dan terlambat.
- **Database Lokal**: Data disimpan secara lokal menggunakan SQLite untuk kemudahan penggunaan.

## **Teknologi yang Digunakan**
Proyek ini dibangun dengan teknologi berikut:
- **Bahasa Pemrograman**: Java (JDK 23 - Oracle OpenJDK 23.0.1)
- **Antarmuka Pengguna (UI)**: AWT dan Swing untuk membangun tampilan aplikasi berbasis desktop.
- **Build Tool**: Gradle untuk manajemen dependensi dan build proyek.
- **Database**: SQLite dengan JDBC untuk menyimpan data karyawan dan absensi secara lokal.
- **Library Eksternal**:
    - **Apache POI**: Untuk menghasilkan laporan dalam format Excel (versi 5.2.5).
    - **SQLite JDBC**: Untuk koneksi ke database SQLite (versi 3.46.0).
    - **JUnit**: Untuk unit testing (versi 5.10.2).
- **IDE**: IntelliJ IDEA (versi 2024.3.5) sebagai alat pengembangan utama.
- **Version Control**: Git dan GitHub untuk kolaborasi tim.

## **Alur Kerja Aplikasi (Flow)**
Berikut adalah alur kerja aplikasi dari penggunaan awal hingga akhir:
1. **Memulai Aplikasi**:
    - Pengguna membuka aplikasi, yang akan langsung menampilkan halaman login.
2. **Login atau Registrasi**:
    - Pengguna memasukkan ID karyawan dan password untuk login.
    - Jika belum memiliki akun, pengguna dapat klik tombol "Register" untuk membuat akun baru dengan memasukkan ID, password, nama, jabatan, dan email.
    - Data akun disimpan ke tabel `Employee` di database SQLite.
3. **Menu Utama**:
    - Setelah login berhasil, pengguna masuk ke menu utama.
    - Terdapat tiga opsi utama:
        - **Check-In/Check-Out**: Pengguna dapat mencatat waktu masuk atau keluar. Data disimpan ke tabel `Attendance`.
        - **Generate Laporan Bulanan**: Pengguna memilih bulan, lalu aplikasi menghitung total kehadiran, absen, dan terlambat berdasarkan data di tabel `Attendance`. Laporan diekspor ke file Excel.
        - **Logout**: Kembali ke halaman login.
4. **Selesai**:
    - Pengguna menutup aplikasi setelah selesai.

## **Penggunaan**
Berikut adalah langkah-langkah untuk menggunakan Aplikasi Absensi Karyawan:

1. **Jalankan Aplikasi**:
    - Pastikan kamu sudah mengunduh dan menjalankan aplikasi (lihat bagian "Cara Menjalankan Aplikasi" di atas untuk langkah build dan run).
    - Setelah aplikasi berjalan, kamu akan melihat halaman login sebagai tampilan awal.

2. **Login atau Registrasi**:
    - **Login**: Masukkan ID karyawan dan password di kolom yang tersedia, lalu klik tombol "Login".
        - Contoh: ID: `EMP001`, Password: `password123`.
    - **Registrasi**: Jika belum punya akun, klik tombol "Register".
        - Isi data seperti ID karyawan, password, nama, jabatan, dan email (opsional).
        - Contoh: ID: `EMP002`, Password: `mypassword`, Nama: `Budi`, Jabatan: `Staff`, Email: `budi@example.com`.
        - Klik "Submit" untuk menyimpan akun. Akun akan tersimpan di database dan kamu bisa langsung login.

3. **Menu Utama**:
    - Setelah login berhasil, kamu akan masuk ke menu utama aplikasi.
    - Terdapat tiga fitur utama:
        - **Check-In/Check-Out**:
            - Klik tombol "Check-In" untuk mencatat waktu masuk (misalnya: 08:00 WIB).
            - Klik tombol "Check-Out" untuk mencatat waktu keluar (misalnya: 17:00 WIB).
            - Data absensi akan tersimpan di database dengan tanggal hari itu.
        - **Generate Laporan Bulanan**:
            - Klik tombol "Generate Report".
            - Pilih bulan yang ingin kamu buat laporannya (misalnya: Mei 2025).
            - Aplikasi akan menghitung total kehadiran, absen, dan terlambat berdasarkan data absensi.
            - Laporan akan diekspor ke file Excel bernama `laporan_absensi_[bulan].xlsx` (misalnya: `laporan_absensi_mei_2025.xlsx`).
            - File akan tersimpan di folder proyek atau folder yang kamu tentukan.
        - **Logout**:
            - Klik tombol "Logout" untuk kembali ke halaman login.

4. **Selesai**:
    - Setelah selesai, tutup aplikasi dengan klik tombol "X" di jendela aplikasi.

**Catatan**:
- Pastikan komputer terhubung ke file database (`absensi.db`) agar data tersimpan dengan baik.
- Jika lupa password, hubungi admin (dalam hal ini ketua tim) untuk reset manual di database.

## **Anggota Tim**
Berikut adalah anggota tim yang berkontribusi dalam pengembangan proyek ini, beserta kontak dan pembagian tugas:

- **Rizky Abra Um** (Ketua Tim)  
  Email: rizkyabraum@example.com  
  Tugas:
    - Membuat struktur database dan koneksi (tabel `Employee`, `Attendance`, dan `DatabaseConnection.java`).
    - Mengembangkan fitur autentikasi (login dan registrasi di `AuthManager.java`).
    - Membuat fitur laporan bulanan (`AttendanceReport.java`).
    - Bertanggung jawab atas review kode dan merge ke branch `main`.

- **Anggota 1**  
  Email: anggota1@example.com  
  Tugas:
    - Membuat kelas model `Employee.java` untuk menyimpan data karyawan.

- **Anggota 2**  
  Email: anggota2@example.com  
  Tugas:
    - Membuat kelas model `Attendance.java` untuk menyimpan data absensi.

- **Anggota 3**  
  Email: anggota3@example.com  
  Tugas:
    - Mengembangkan antarmuka pengguna menggunakan Swing (`LoginFrame.java`, `RegisterFrame.java`, `MainMenuFrame.java`).

- **Anggota 4**  
  Email: anggota4@example.com  
  Tugas:
    - Membuat fitur ekspor laporan ke Excel (`ExcelExporter.java`).

- **Anggota 5**  
  Email: anggota5@example.com  
  Tugas:
    - Menulis dokumentasi proyek (README, komentar kode).
    - Membuat unit test untuk memastikan kode berfungsi dengan baik.