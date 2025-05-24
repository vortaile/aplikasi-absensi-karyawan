# 📋 Aplikasi Absensi Karyawan

**Aplikasi Absensi Karyawan** adalah sistem desktop yang dibuat untuk mencatat kehadiran karyawan secara efisien. Aplikasi ini memungkinkan pengguna untuk login atau registrasi, melakukan absensi (check-in dan check-out), serta melihat data kehadiran mereka. Data disimpan secara lokal menggunakan SQLite, dan laporan bulanan dapat diekspor ke Excel.

---

## ✨ Fitur Utama

- 🔐 **Login dan Registrasi**
    - Pengguna dapat login atau membuat akun baru.
- 🙋‍♂️ **Halo, {Nama}**
    - Setelah login, pengguna disambut dengan nama mereka.
- ⏱️ **Absensi Harian**
    - Check-in dan check-out ditampilkan secara berdampingan.
- 📅 **Riwayat Kehadiran**
    - Daftar kehadiran pengguna ditampilkan di bawah panel absensi.
- 🧑‍💼 **Dashboard**
    - Tampilan dashboard berisi avatar (placeholder), informasi pengguna, dan tombol-tombol interaktif.
- 📊 **Laporan Bulanan**
    - Laporan dapat diekspor ke format Excel menggunakan Apache POI.

---

## 🛠️ Teknologi yang Digunakan

- 💻 **Bahasa Pemrograman**: Java (JDK 23 - Oracle OpenJDK 23.0.1)
- 🖼️ **GUI**: AWT & Swing
- 🧱 **Build Tool**: Gradle
- 🗃️ **Database**: SQLite + JDBC
- 📦 **Library Eksternal**:
    - Apache POI (v5.2.5) untuk ekspor Excel
    - SQLite JDBC (v3.46.0)
    - JUnit 5 (v5.10.2)
- 🧠 **IDE**: IntelliJ IDEA
- 🌐 **Version Control**: Git & GitHub

---

## 🔄 Alur Aplikasi

1. **Tampilan Awal**
    - Hanya menampilkan panel Login dan Register.

2. **Login atau Registrasi**
    - Login: Masukkan ID dan password.
    - Registrasi: Isi ID, password, nama, jabatan, dan email.

3. **Dashboard**
    - Menampilkan teks: `Halo, {Nama} selamat datang di aplikasi absensi`
    - Avatar placeholder
    - Tombol Check-In dan Check-Out (berdampingan)
    - Daftar riwayat kehadiran pengguna
    - Tombol untuk melihat laporan atau logout

4. **Laporan Bulanan**
    - Pilih bulan → Ekspor data ke Excel

---

## 💡 Cara Menggunakan

1. **Jalankan Aplikasi**
    - Jalankan dari IDE atau hasil build Gradle.

2. **Login atau Registrasi**
    - Masuk atau buat akun baru.

3. **Dashboard**
    - Lakukan check-in/check-out
    - Lihat riwayat kehadiran
    - Ekspor laporan jika dibutuhkan

4. **Tutup Aplikasi**
    - Logout dan keluar dari aplikasi.

📝 **Catatan**: File database `absensi.db` harus berada di direktori proyek.

---

## 👥 Anggota Tim

| Nama                          | NPM        |
|-------------------------------|------------|
| Fauzan Arrozi                | 10123436   |
| Arizha Praja Wirakusuma      | 10123184   |
| Faiz Alfano Duriat           | 10123406   |
| Ananda Raihana               | 10123141   |
| Kevin Arya Moranza           | 10123584   |
| Darrell Dzaky Ahnaf          | 10123296   |

---

