import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AplikasiKaryawan {

    private static Map<String, String> users = new HashMap<>();
    private static ArrayList<String> kehadiran = new ArrayList<>();
    private static JFrame frame;

    public static void main(String[] args) {
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

            if (users.containsKey(username) && users.get(username).equals(password)) {
                JOptionPane.showMessageDialog(frame, "Login berhasil, absensi dicatat.");
                catatAbsensi(username);
            } else {
                JOptionPane.showMessageDialog(frame, "Username atau password salah.");
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
                if (!users.containsKey(username)) {
                    users.put(username, password);
                    JOptionPane.showMessageDialog(frame, "Registrasi berhasil.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Username sudah digunakan.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Input tidak boleh kosong.");
            }
        }
    }

    private static void catatAbsensi(String username) {
        String waktu = java.time.LocalDateTime.now().toString();
        kehadiran.add(username + " hadir pada " + waktu);
    }

    private static void tampilkanDaftarKehadiran() {
        StringBuilder sb = new StringBuilder();
        if (kehadiran.isEmpty()) {
            sb.append("Belum ada data kehadiran.");
        } else {
            for (String k : kehadiran) {
                sb.append(k).append("\n");
            }
        }

        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setPreferredSize(new Dimension(350, 200));

        JOptionPane.showMessageDialog(frame, scrollPane, "Daftar Kehadiran", JOptionPane.INFORMATION_MESSAGE);
    }
}
