import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AplikasiKaryawan {

    private static JFrame frame;
    private static int userId = -1;
    private static String username = "";

    // Konfigurasi database, sesuaikan dengan milik Anda
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/employee_db?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "Nanda1213";

    private static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AplikasiKaryawan::tampilkanFrameUtama);
    }

    private static void tampilkanFrameUtama() {
        frame = new JFrame("Aplikasi Manajemen Karyawan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Center window
        frame.setLayout(new CardLayout());

        JPanel panelContainer = new JPanel(new CardLayout());

        JPanel panelLogin = buatPanelLogin(panelContainer);
        JPanel panelRegistrasi = buatPanelRegistrasi(panelContainer);
        JPanel panelDashboard = buatPanelDashboard(panelContainer);

        panelContainer.add(panelLogin, "Login");
        panelContainer.add(panelRegistrasi, "Register");
        panelContainer.add(panelDashboard, "Dashboard");

        frame.setContentPane(panelContainer);
        frame.setVisible(true);
    }

    private static JPanel buatPanelLogin(JPanel container) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));

        JTextField fieldUsername = new JTextField();
        JPasswordField fieldPassword = new JPasswordField();

        form.add(new JLabel("Username:"));
        form.add(fieldUsername);
        form.add(new JLabel("Password:"));
        form.add(fieldPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> {
            String user = fieldUsername.getText().trim();
            String pass = new String(fieldPassword.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Username dan password tidak boleh kosong.");
                return;
            }

            try (Connection conn = getConnection()) {
                String sql = "SELECT id FROM users WHERE username = ? AND password = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, user);
                ps.setString(2, pass);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    userId = rs.getInt("id");
                    username = user;
                    JOptionPane.showMessageDialog(frame, "Login berhasil.");
                    gantiPanel(container, "Dashboard");
                } else {
                    JOptionPane.showMessageDialog(frame, "Username atau password salah.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error koneksi database: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        JButton btnRegister = new JButton("Registrasi");
        btnRegister.addActionListener(e -> gantiPanel(container, "Register"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRegister);

        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        panel.add(new JLabel("Login Aplikasi", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(form, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private static JPanel buatPanelRegistrasi(JPanel container) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));

        JTextField fieldUsername = new JTextField();
        JPasswordField fieldPassword = new JPasswordField();

        form.add(new JLabel("Username baru:"));
        form.add(fieldUsername);
        form.add(new JLabel("Password:"));
        form.add(fieldPassword);

        JButton btnRegister = new JButton("Registrasi");
        btnRegister.addActionListener(e -> {
            String user = fieldUsername.getText().trim();
            String pass = new String(fieldPassword.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Input tidak boleh kosong.");
                return;
            }

            try (Connection conn = getConnection()) {
                // Cek username sudah ada atau belum
                String cekSql = "SELECT * FROM users WHERE username = ?";
                PreparedStatement cekPs = conn.prepareStatement(cekSql);
                cekPs.setString(1, user);
                ResultSet rs = cekPs.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(frame, "Username sudah digunakan.");
                } else {
                    String insertSql = "INSERT INTO users (username, password) VALUES (?, ?)";
                    PreparedStatement insertPs = conn.prepareStatement(insertSql);
                    insertPs.setString(1, user);
                    insertPs.setString(2, pass);
                    insertPs.executeUpdate();
                    JOptionPane.showMessageDialog(frame, "Registrasi berhasil. Silakan login.");
                    gantiPanel(container, "Login");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error koneksi database: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        JButton btnBack = new JButton("Kembali");
        btnBack.addActionListener(e -> gantiPanel(container, "Login"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnRegister);
        buttonPanel.add(btnBack);

        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        panel.add(new JLabel("Registrasi User Baru", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(form, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private static JPanel buatPanelDashboard(JPanel container) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Label sapaan
        JLabel greeting = new JLabel("", SwingConstants.CENTER);
        greeting.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(greeting, BorderLayout.NORTH);

        // Panel avatar dan tombol checkin checkout
        JPanel panelKiri = new JPanel(new BorderLayout(10, 10));
        JLabel avatarLabel = new JLabel("[Avatar]", SwingConstants.CENTER);
        avatarLabel.setPreferredSize(new Dimension(120, 120));
        avatarLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelKiri.add(avatarLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnCheckIn = new JButton("Check-In");
        JButton btnCheckOut = new JButton("Check-Out");
        buttonPanel.add(btnCheckIn);
        buttonPanel.add(btnCheckOut);
        panelKiri.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(panelKiri, BorderLayout.WEST);

        // Tabel kehadiran
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Jenis", "Waktu"}, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton btnRefresh = new JButton("Refresh Kehadiran");
        panel.add(btnRefresh, BorderLayout.SOUTH);

        // Tombol Logout
        JButton btnLogout = new JButton("Logout");
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.add(btnLogout);
        panel.add(logoutPanel, BorderLayout.PAGE_END);

        // Event handlers
        btnCheckIn.addActionListener(e -> {
            catatKehadiran("check-in");
            isiTabelKehadiran(table);
        });
        btnCheckOut.addActionListener(e -> {
            catatKehadiran("check-out");
            isiTabelKehadiran(table);
        });
        btnRefresh.addActionListener(e -> isiTabelKehadiran(table));

        btnLogout.addActionListener(e -> {
            userId = -1;
            username = "";
            gantiPanel(container, "Login");
        });

        // Set sapaan tiap kali dashboard muncul
        SwingUtilities.invokeLater(() -> greeting.setText("Halo, " + username + "! Selamat datang di aplikasi absensi"));

        isiTabelKehadiran(table);

        return panel;
    }

    private static void isiTabelKehadiran(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        if (userId == -1) return;

        try (Connection conn = getConnection()) {
            String sql = "SELECT jenis, waktu FROM attendance WHERE user_id = ? ORDER BY waktu DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String jenis = rs.getString("jenis");
                Timestamp waktu = rs.getTimestamp("waktu");
                model.addRow(new Object[]{jenis, waktu.toString()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Gagal mengambil data kehadiran: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static void catatKehadiran(String jenis) {
        if (userId == -1) {
            JOptionPane.showMessageDialog(frame, "Anda harus login terlebih dahulu.");
            return;
        }

        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO attendance (user_id, jenis, waktu) VALUES (?, ?, CURRENT_TIMESTAMP)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, jenis);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(frame, "Berhasil melakukan " + jenis + "!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Gagal mencatat kehadiran: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static void gantiPanel(JPanel container, String namaPanel) {
        CardLayout cl = (CardLayout) container.getLayout();
        cl.show(container, namaPanel);
    }
}
