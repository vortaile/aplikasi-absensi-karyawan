# 📋 Aplikasi Absensi Karyawan

**Aplikasi Absensi Karyawan** adalah sistem desktop berbasis Java yang digunakan untuk mencatat kehadiran karyawan secara efisien. Aplikasi ini menyediakan fitur login, registrasi, check-in, check-out, serta ekspor laporan bulanan dalam format Excel.

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
| 🗃️ Database      | SQLite + JDBC                                 |
| 📦 Library       | Apache POI v5.2.5, SQLite JDBC v3.46.0        |
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

