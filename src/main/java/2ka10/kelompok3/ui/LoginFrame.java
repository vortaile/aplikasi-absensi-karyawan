import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class LoginFrame extends JFrame {
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JButton btnLogin, btnRegister;

    // Menyimpan data username dan password sementara
    private HashMap<String, String> userDatabase = new HashMap<>();

    public LoginFrame() {
        setTitle("Login Karyawan");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 4, 5, 10));

        // Komponen
        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");
        tfUsername = new JTextField();
        pfPassword = new JPasswordField();
        btnLogin = new JButton("Login");
        btnRegister = new JButton("Register");

        // Tambah ke frame
        add(lblUsername);
        add(tfUsername);
        add(lblPassword);
        add(pfPassword);
        add(btnLogin);
        add(btnRegister);

        // Event Login
        btnLogin.addActionListener(e -> loginUser());

        // Event Register
        btnRegister.addActionListener(e -> registerUser());

        setVisible(true);
    }

    private void loginUser() {
        String username = tfUsername.getText();
        String password = String.valueOf(pfPassword.getPassword());

        if (userDatabase.containsKey(username)) {
            if (userDatabase.get(username).equals(password)) {
                JOptionPane.showMessageDialog(this, "Login berhasil! Selamat datang, " + username);
            } else {
                JOptionPane.showMessageDialog(this, "Password salah!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Username tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registerUser() {
        String username = tfUsername.getText();
        String password = String.valueOf(pfPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan Password tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (userDatabase.containsKey(username)) {
            JOptionPane.showMessageDialog(this, "Username sudah terdaftar!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            userDatabase.put(username, password);
            JOptionPane.showMessageDialog(this, "Registrasi berhasil! Silakan login.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}