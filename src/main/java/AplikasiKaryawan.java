import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AplikasiKaryawan {

    private static Map<String, String> users = new HashMap<>();
    private static ArrayList<String> kehadiran = new ArrayList<>();
    private static JFrame frame;

    // Method koneksi ke database di dalam class (MySQL)
    private static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://127.0.0.1:3306/employee_db";
        String username = "root";
        String password = "1234"; // Replace with your MySQL password

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }

    public static void main(String[] args) {
        try {
        Connection conn = getConnection();
        JOptionPane.showMessageDialog(null, "Koneksi ke database berhasil!");
        conn.close();
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal koneksi: " + e.getMessage());
        e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> tampilkanMenuUtama());
    }

    private static void tampilkanMenuUtama() {
        frame = new JFrame("Aplikasi Manajemen Karyawan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        JButton btnLogin = new JButton("Login");
        JButton btnRegistrasi = new JButton("Registrasi");
        JButton btnLihatKehadiran = new JButton("Lihat Kehadiran");
        JButton btnKeluar = new JButton("Keluar");

        btnLogin.addActionListener(e -> tampilkanFormLogin());
        btnRegistrasi.addActionListener(e -> tampilkanFormRegistrasi());
        btnLihatKehadiran.addActionListener(e -> tampilkanDaftarKehadiran());
        btnKeluar.addActionListener(e -> System.exit(0));

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.add(btnLogin);
        panel.add(btnRegistrasi);
        panel.add(btnLihatKehadiran);
        panel.add(btnKeluar);

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
        int userId = rs.getInt("id");

        PreparedStatement absensiStmt = conn.prepareStatement("INSERT INTO kehadiran (user_id) VALUES (?)");
        absensiStmt.setInt(1, userId);
        absensiStmt.executeUpdate();

        JOptionPane.showMessageDialog(frame, "Login berhasil dan absensi dicatat.");
        } else {
        JOptionPane.showMessageDialog(frame, "Username atau password salah.");
        }
        } catch (Exception e) {
        JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
        e.printStackTrace();
        }
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
        // Create a new JFrame for the table view
        JFrame tableFrame = new JFrame("Daftar Kehadiran");
        tableFrame.setSize(500, 300);
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a DefaultTableModel for the JTable
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Username");
        model.addColumn("Waktu");

        try (Connection conn = getConnection()) {
        String sql = "SELECT u.username, k.waktu FROM kehadiran k JOIN users u ON k.user_id = u.id ORDER BY k.waktu DESC";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        // Populate the table model with data
        while (rs.next()) {
        model.addRow(new Object[]{
                rs.getString("username"),
                rs.getTimestamp("waktu").toString()
        });
        }

        // If no data, show a message
        if (model.getRowCount() == 0) {
        JOptionPane.showMessageDialog(tableFrame, "Belum ada data kehadiran.");
        tableFrame.dispose();
        return;
        }

        // Create JTable and add it to a JScrollPane
        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the frame
        tableFrame.add(scrollPane, BorderLayout.CENTER);

        // Add a "Close" button at the bottom
        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(e -> tableFrame.dispose());
        tableFrame.add(btnClose, BorderLayout.SOUTH);

        // Display the frame
        tableFrame.setVisible(true);

        } catch (Exception e) {
        JOptionPane.showMessageDialog(frame, "Gagal mengambil data kehadiran: " + e.getMessage());
        e.printStackTrace();
        }
    }
}