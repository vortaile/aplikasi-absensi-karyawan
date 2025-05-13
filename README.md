# 📋 Aplikasi Absensi Karyawan

**Aplikasi Absensi Karyawan** adalah sistem berbasis desktop yang dirancang untuk membantu perusahaan atau organisasi dalam mencatat kehadiran karyawan secara efisien. Aplikasi ini memungkinkan karyawan untuk login, melakukan check-in/check-out, dan menghasilkan laporan bulanan dalam format Excel. Proyek ini dikembangkan sebagai bagian dari tugas akademik untuk membantu memahami pengembangan aplikasi menggunakan Java.

---

## ✨ **Fitur Utama**
- 🔐 **Login dan Registrasi**  
  Karyawan dapat login menggunakan ID dan password. Jika belum memiliki akun, mereka dapat mendaftar langsung melalui aplikasi.
- 🕘 **Absensi Harian**  
  Karyawan dapat mencatat waktu masuk (check-in) dan keluar (check-out) setiap hari.
- 📊 **Laporan Bulanan**  
  Aplikasi dapat menghasilkan laporan absensi bulanan dalam format Excel.
- 🗄️ **Database Lokal**  
  Data disimpan secara lokal menggunakan SQLite untuk kemudahan penggunaan.

---

## 🛠️ **Teknologi yang Digunakan**
- 💻 **Bahasa Pemrograman**: Java (JDK 23 - Oracle OpenJDK 23.0.1)
- 🖼️ **Antarmuka Pengguna (UI)**: AWT dan Swing
- 🧱 **Build Tool**: Gradle
- 🗃️ **Database**: SQLite dengan JDBC
- 📦 **Library Eksternal**:
    - 📁 Apache POI: Untuk laporan Excel (v5.2.5)
    - 🛢️ SQLite JDBC (v3.46.0)
    - ✅ JUnit (v5.10.2)
- 🧠 **IDE**: IntelliJ IDEA (v2024.3.5)
- 🌐 **Version Control**: Git dan GitHub

---

## 🔄 **Alur Kerja Aplikasi (Flow)**
1. 🏁 **Memulai Aplikasi**  
   Pengguna membuka aplikasi yang langsung menampilkan halaman login.

2. 🔐 **Login atau Registrasi**
    - Login: Masukkan ID dan password.
    - Registrasi: Isi ID, password, nama, jabatan, dan email → simpan ke database `Employee`.

3. 🧭 **Menu Utama**
    - 🟢 **Check-In/Check-Out**: Catat waktu masuk dan keluar → simpan ke `Attendance`.
    - 📊 **Generate Laporan Bulanan**: Pilih bulan → hitung kehadiran, absen, terlambat → ekspor Excel.
    - 🔄 **Logout**: Kembali ke login.

4. ✅ **Selesai**
    - Tutup aplikasi setelah selesai digunakan.

---

## 💡 **Penggunaan**
### 1️⃣ Jalankan Aplikasi
- Pastikan aplikasi sudah dibuild dan dijalankan.

### 2️⃣ Login atau Registrasi
- 🔑 Login: Masukkan ID dan password, klik "Login".
    - Contoh: `EMP001`, `password123`
- 📝 Registrasi: Klik "Register" lalu isi data → klik "Submit".

### 3️⃣ Menu Utama
- ⏱️ **Check-In/Check-Out**
- 📈 **Generate Report**
- 🚪 **Logout**

### 4️⃣ Selesai
- Tutup jendela aplikasi.

📝 **Catatan**:
- File database `absensi.db` harus ada.
- Jika lupa password, hubungi admin/kepala tim.

---

## 👥 **Anggota Tim**
| Nama                                | NPM                  | Tugas |
|-------------------------------------|----------------------|-------|
| 🧑‍💼 **Fauzan Arrozi (Ketua Tim)** | 10123436             | Struktur database, login/registrasi, laporan Excel, review & merge |
| 👨‍💻 **Arizha Praja Wirakusuma**   | 10123184             | Model `Employee.java` |
| 👨‍💻 **Faiz Alfano Duriat**        | 10123406             | Model `Attendance.java` |
| 🎨 **Ananda Raihana**               | 10123141             | GUI (LoginFrame, RegisterFrame, MainMenuFrame) |
| 📄 **Kevin Arya moranza**           | 10123584                  | Ekspor Excel (`ExcelExporter.java`) |
| 📝 **Darrell Dzaky Ahnaf**          | 10123296 | Dokumentasi & unit testing |

---
