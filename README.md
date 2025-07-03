# 📋 Aplikasi Absensi Karyawan

Proyek ini adalah Aplikasi Absensi Karyawan yang dikembangkan menggunakan Java 2 Standard Edition (J2SE) dengan menerapkan konsep Pemrograman Berorientasi Objek (OOP) dan memanfaatkan Java Swing untuk antarmuka grafis. Aplikasi ini dirancang untuk membantu pengelolaan kehadiran karyawan, dengan fokus pada proses autentikasi pengguna, pencatatan kehadiran (check-in/check-out), dan pengelolaan data kehadiran. Aplikasi ini sangat berguna bagi perusahaan atau organisasi yang membutuhkan sistem absensi sederhana namun efisien untuk memantau kehadiran karyawan secara digital. Aplikasi ini terintegrasi dengan MySQL untuk menyimpan dan mengelola data secara efisien. 

---

## ✨ Fitur Utama

- 🔐 **Login dan Registrasi**
  - Pengguna dapat login atau membuat akun baru.

- 🙋‍♂️ **Halo, {Nama}**
  - Setelah login, pengguna disambut dengan nama mereka.

- ⏱️ **Absensi Harian**
  - Check-in dan check-out ditampilkan secara berdampingan.

- 📅 **Riwayat Kehadiran**
  - Menampilkan riwayat kehadiran pengguna.

- 🧑‍💼 **Dashboard Interaktif**
  - Berisi avatar (placeholder), informasi pengguna, dan tombol interaktif.

- 📊 **Laporan Bulanan**
  - Dapat diekspor ke Excel menggunakan Apache POI.

---

## 🛠️ Teknologi yang Digunakan

| Kategori         | Teknologi                                    |
|------------------|-----------------------------------------------|
| 💻 Bahasa        | Java (JDK 23 - Oracle OpenJDK 23.0.1)         |
| 🖼️ GUI           | AWT & Swing                                   |
| 🧱 Build Tool    | Gradle                                        |
| 🗃️ Database      | MySQL + JDBC                                 |
| 📦 Library       | Apache POI v5.2.5, MySQL JDBC v3.46.0        |
| 🧪 Testing       | JUnit 5 (v5.10.2)                              |
| 💡 IDE           | IntelliJ IDEA                                 |
| 🌐 Version Ctrl  | Git & GitHub                                  |

---

## 🔄 Alur Aplikasi

1. **Tampilan Awal**
   - Panel Login dan Registrasi

2. **Login / Registrasi**
   - Login: Masukkan ID dan Password
   - Registrasi: Isi ID, Password, Nama, Jabatan, dan Email

3. **Dashboard**
   - Menampilkan: `Halo, {Nama} selamat datang di aplikasi absensi`
   - Avatar Placeholder
   - Tombol Check-In dan Check-Out
   - Riwayat Kehadiran
   - Tombol Ekspor Laporan dan Logout

4. **Laporan Bulanan**
   - Pilih bulan → Ekspor ke Excel

---

## 💡 Cara Menggunakan

1. **Jalankan Aplikasi**
   - Bisa dijalankan dari IDE (IntelliJ) atau hasil build Gradle

2. **Login atau Registrasi**
   - Masuk atau buat akun baru

3. **Dashboard**
   - Lakukan absensi dan lihat riwayat

4. **Ekspor Laporan**
   - Gunakan tombol untuk ekspor ke Excel

📝 **Catatan**: File database `absensi.db` harus berada di direktori proyek.

---

## 👥 Anggota Tim

| Nama                          | NPM        |
|-------------------------------|------------|
| **Fauzan Arrozi** (Ketua)     | 10123436   |
| Arizha Praja Wirakusuma       | 10123184   |
| Faiz Alfano Duriat            | 10123406   |
| Ananda Raihana                | 10123141   |
| Kevin Arya Moranza            | 10123584   |
| Darrell Dzaky Ahnaf           | 10123296   |

---

## 📎 Lisensi

Proyek ini dibuat untuk keperluan pembelajaran dan presentasi mata kuliah. Bebas dimodifikasi dan dikembangkan lebih lanjut.

