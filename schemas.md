-- Buat database jika belum ada
CREATE DATABASE IF NOT EXISTS employee_db;
USE employee_db;

-- Tabel Users
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- Tabel Kehadiran dengan jenis check-in / check-out
CREATE TABLE IF NOT EXISTS kehadiran (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    jenis ENUM('check-in', 'check-out') NOT NULL,
    waktu DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
