package gui;

import dao.AdministrateurDAO;
import dao.ConnectionDAO;
import model.Administrateur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class LoginGUI {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginGUI login = new LoginGUI();
            login.frame.setVisible(true);
        });
    }

    public LoginGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Connexion - Administrateur");
        frame.setBounds(100, 100, 400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel userLabel = new JLabel("Nom d'utilisateur:");
        userLabel.setBounds(50, 30, 120, 25);
        frame.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(180, 30, 150, 25);
        frame.add(usernameField);

        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordLabel.setBounds(50, 70, 120, 25);
        frame.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 70, 150, 25);
        frame.add(passwordField);

        JButton loginButton = new JButton("Se connecter");
        loginButton.setBounds(120, 110, 150, 30);
        frame.add(loginButton);

        // 👉 使用真实数据库验证
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                // Connexion à la BDD via DAO
                ConnectionDAO connectionDAO = new ConnectionDAO();
                Connection conn = connectionDAO.getConnection();

                if (conn == null) {
                    JOptionPane.showMessageDialog(frame, "Erreur de connexion à la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                AdministrateurDAO adminDao = new AdministrateurDAO(conn);
                Administrateur admin = adminDao.getByCredentials(username, password);

                if (admin != null) {
                    JOptionPane.showMessageDialog(frame, "✅ Connexion réussie !");
                    frame.dispose();
                    // TODO: lancer l'interface AdminGUI ici
                    // new AdminGUI(admin); // par exemple
                } else {
                    JOptionPane.showMessageDialog(frame, "❌ Identifiants incorrects", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}