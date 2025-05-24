import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class AplikasiKaryawan {

    private static JFrame frame;
    private static int userId = -1; // Menyimpan user ID setelah login

    private static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://127.0.0.1:3306/employee_db";
        String username = "root";
        String password = "Nanda1213";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AplikasiKaryawan::tampilkanMenuUtama);
    }

    private static void tampilkanMenuUtama() {
        frame = new JFrame("Aplikasi Manajemen Karyawan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton btnLogin = new JButton("Login");
        JButton btnRegistrasi = new JButton("Registrasi");
        JButton btnLihatKehadiran = new JButton("Lihat Kehadiran");

        btnLogin.addActionListener(e -> tampilkanFormLogin());
        btnRegistrasi.addActionListener(e -> tampilkanFormRegistrasi());
        btnLihatKehadiran.addActionListener(e -> tampilkanDaftarKehadiran());

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.add(btnLogin);
        panel.add(btnRegistrasi);
        panel.add(btnLihatKehadiran);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    private static void tampilkanFormLogin() {
        JTextField fieldUsername = new JTextField();
        JPasswordField fieldPassword = new JPasswordField();

        Object[] fields = {
                "Username:", fieldUsername,
                "Password:", fieldPassword
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Login", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String username = fieldUsername.getText();
            String password = new String(fieldPassword.getPassword());

            try (Connection conn = getConnection()) {
                PreparedStatement ps = conn.prepareStatement("SELECT id FROM users WHERE username = ? AND password = ?");
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    userId = rs.getInt("id");
                    JOptionPane.showMessageDialog(frame, "Login berhasil.");
                    tampilkanMenuSetelahLogin(username);
                } else {
                    JOptionPane.showMessageDialog(frame, "Username atau password salah.");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void tampilkanMenuSetelahLogin(String username) {
        JFrame menuLoginFrame = new JFrame("Selamat Datang, " + username);
        menuLoginFrame.setSize(400, 200);
        menuLoginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton btnCheckIn = new JButton("Check-In");
        JButton btnCheckOut = new JButton("Check-Out");
        JButton btnLogout = new JButton("Logout");

        btnCheckIn.addActionListener(e -> catatKehadiran("check-in"));
        btnCheckOut.addActionListener(e -> catatKehadiran("check-out"));
        btnLogout.addActionListener(e -> {
            userId = -1;
            menuLoginFrame.dispose();
            JOptionPane.showMessageDialog(frame, "Logout berhasil.");
        });

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(btnCheckIn);
        panel.add(btnCheckOut);
        panel.add(btnLogout);

        menuLoginFrame.setContentPane(panel);
        menuLoginFrame.setVisible(true);
    }

    private static void catatKehadiran(String jenis) {
        if (userId == -1) {
            JOptionPane.showMessageDialog(frame, "Anda belum login.");
            return;
        }

        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO kehadiran (user_id, jenis) VALUES (?, ?)");
            ps.setInt(1, userId);
            ps.setString(2, jenis);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(frame, jenis + " berhasil dicatat.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Gagal mencatat kehadiran: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void tampilkanFormRegistrasi() {
        JTextField fieldUsername = new JTextField();
        JPasswordField fieldPassword = new JPasswordField();

        Object[] fields = {
                "Username baru:", fieldUsername,
                "Password:", fieldPassword
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Registrasi", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String username = fieldUsername.getText();
            String password = new String(fieldPassword.getPassword());

            if (!username.isEmpty() && !password.isEmpty()) {
                try (Connection conn = getConnection()) {
                    PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
                    ps.setString(1, username);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(frame, "Username sudah digunakan.");
                    } else {
                        ps = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
                        ps.setString(1, username);
                        ps.setString(2, password);
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(frame, "Registrasi berhasil.");
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Input tidak boleh kosong.");
            }
        }
    }

    private static void tampilkanDaftarKehadiran() {
        JFrame tableFrame = new JFrame("Daftar Kehadiran");
        tableFrame.setSize(600, 300);
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Username");
        model.addColumn("Jenis");
        model.addColumn("Waktu");

        try (Connection conn = getConnection()) {
            String sql = "SELECT u.username, k.jenis, k.waktu FROM kehadiran k JOIN users u ON k.user_id = u.id ORDER BY k.waktu DESC";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("username"),
                        rs.getString("jenis"),
                        rs.getTimestamp("waktu").toString()
                });
            }

            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(tableFrame, "Belum ada data kehadiran.");
                tableFrame.dispose();
                return;
            }

            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            tableFrame.add(scrollPane, BorderLayout.CENTER);

            JButton btnClose = new JButton("Close");
            btnClose.addActionListener(e -> tableFrame.dispose());
            tableFrame.add(btnClose, BorderLayout.SOUTH);

            tableFrame.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Gagal mengambil data kehadiran: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
