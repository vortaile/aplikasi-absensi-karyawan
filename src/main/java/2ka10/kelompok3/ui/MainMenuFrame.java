import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuFrame extends JFrame {

    public MainMenuFrame() {
        // Set frame properties
        setTitle("Menu Utama - Aplikasi Absensi Karyawan");
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create panel and set layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Create buttons
        JButton btnInputAbsensi = new JButton("Input Absensi");
        JButton btnLihatData = new JButton("Lihat Data Karyawan");
        JButton btnLaporan = new JButton("Laporan Absensi");
        JButton btnLogout = new JButton("Logout");

        // Add action listeners (contoh fungsi sederhana)
        btnInputAbsensi.addActionListener(e -> JOptionPane.showMessageDialog(this, "Menu Input Absensi diklik"));
        btnLihatData.addActionListener(e -> JOptionPane.showMessageDialog(this, "Menu Lihat Data diklik"));
        btnLaporan.addActionListener(e -> JOptionPane.showMessageDialog(this, "Menu Laporan diklik"));
        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin logout?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Add buttons to panel
        panel.add(new JLabel("Selamat Datang di Aplikasi Absensi", SwingConstants.CENTER));
        panel.add(btnInputAbsensi);
        panel.add(btnLihatData);
        panel.add(btnLaporan);
        panel.add(btnLogout);

        // Add panel to frame
        add(panel);
    }

    public static void main(String[] args) {
        // Jalankan frame di Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            MainMenuFrame frame = new MainMenuFrame();
            frame.setVisible(true);
        });
    }
}
