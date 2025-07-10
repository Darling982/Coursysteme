package GUI;


import dao.AdministrateurDAO;
import dao.ConnectionDAO;
import dao.EtudiantDAO;
import model.Etudiants;
import model.Admin;
import stockage.StudentStatut;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 * Interface graphique de connexion permettant aux étudiants et à l'administrateur
 * de se connecter à l'application.
 * 
 * @author Equipe 7_4 lot1
 * @version Finale
 */
public class loginGUI {
    
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    /**
     * Point d'entrée de l'application. Lance l'interface de connexion.
     * @param args les arguments de la ligne de commande (non utilisés ici)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            loginGUI login = new loginGUI();
            login.getFrame().setVisible(true);
        });
    }

    /**
     * Constructeur de la classe loginGUI.
     * Initialise l'interface de connexion.
     */
    public loginGUI() {
        initialize();
    }

    /**
     * Initialise les composants de la fenêtre principale de connexion.
     */
    private void initialize() {
        setFrame(new JFrame("Connexion"));
        getFrame().getContentPane().setBackground(new Color(221, 177, 226));
        getFrame().getContentPane().setForeground(new Color(0, 0, 0));
        getFrame().setBounds(100, 100, 450, 500);
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getFrame().getContentPane().setLayout(null);

        // Image de fond
        ImageIcon bgIcon = new ImageIcon(loginGUI.class.getResource("/pictures/EXT 14.jpg"));
        BackgroundPanel backgroundPanel = new BackgroundPanel(bgIcon);
        frame.setContentPane(backgroundPanel);

        JLabel userLabel = new JLabel("Username");
        userLabel.setForeground(Color.RED);
        userLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        userLabel.setBounds(50, 140, 120, 25);
        frame.getContentPane().add(userLabel);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Tahoma", Font.BOLD, 14));
        usernameField.setBounds(50, 163, 342, 36);
        getFrame().getContentPane().add(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.RED);
        passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        passwordLabel.setBounds(50, 223, 120, 25);
        getFrame().getContentPane().add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Times New Roman", Font.BOLD, 14));
        passwordField.setBounds(50, 245, 342, 36);
        getFrame().getContentPane().add(passwordField);

        JButton adminLoginButton = new JButton("Admin Login");
        adminLoginButton.setBackground(new Color(192, 192, 192));
        adminLoginButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
        adminLoginButton.setBounds(20, 372, 120, 30);
        getFrame().getContentPane().add(adminLoginButton);

        JButton etudiantLoginButton = new JButton("Etudiant Login");
        etudiantLoginButton.setBackground(new Color(192, 192, 192));
        etudiantLoginButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
        etudiantLoginButton.setBounds(286, 372, 140, 30);
        getFrame().getContentPane().add(etudiantLoginButton);

        JLabel lblNewLabel = new JLabel("WELCOME");
        lblNewLabel.setForeground(Color.RED);
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
        lblNewLabel.setBounds(129, 57, 231, 46);
        getFrame().getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setBounds(197, 257, 45, 13);
        getFrame().getContentPane().add(lblNewLabel_1);

        JCheckBox chckbxNewCheckBox = new JCheckBox("Afficher le mot de passe");
        chckbxNewCheckBox.setFont(new Font("Times New Roman", Font.BOLD, 12));
        chckbxNewCheckBox.setBounds(50, 297, 172, 21);

        chckbxNewCheckBox.addActionListener(e -> {
            if (chckbxNewCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('•');
            }
        });
        getFrame().getContentPane().add(chckbxNewCheckBox);

        // Action du bouton admin
        adminLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                ConnectionDAO connectionDAO = new ConnectionDAO();
                Connection conn = connectionDAO.getConnection();

                if (conn == null) {
                    JOptionPane.showMessageDialog(getFrame(), "Erreur de connexion à la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                AdministrateurDAO adminDao = new AdministrateurDAO(conn);
                Admin admin = adminDao.getByCredentials(username, password);

                if (admin != null) {
                    JOptionPane.showMessageDialog(getFrame(), "✅ Connexion admin réussie !\nBienvenue " + admin.getUsername());
                    AdminIHM adihm = new AdminIHM();
                    getFrame().dispose();
                } else {
                    JOptionPane.showMessageDialog(getFrame(), "❌ Identifiants admin incorrects", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action du bouton étudiant
        etudiantLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                StudentStatut.setIdStudent(username);
                String password = String.valueOf(passwordField.getPassword());

                ConnectionDAO connectionDAO = new ConnectionDAO();
                Connection conn = connectionDAO.getConnection();

                if (conn == null) {
                    JOptionPane.showMessageDialog(getFrame(), "Erreur de connexion à la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                EtudiantDAO etuDao = new EtudiantDAO(conn);
                Etudiants etu = etuDao.getByCredentials(username, password);

                if (etu != null) {
                    JOptionPane.showMessageDialog(getFrame(),
                            "Connexion réussie !\nBienvenue " + etu.getPrenom() + " " + etu.getNom());

                    int id = etu.getId();
                    String statut = etu.getStatut();

                    EtudiantIHM etuIHM = new EtudiantIHM();
                    getFrame().dispose();

                } else {
                    JOptionPane.showMessageDialog(getFrame(), "❌ Identifiants étudiant incorrects", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * Retourne la fenêtre principale de l'application.
     * @return le JFrame principal
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Définit la fenêtre principale de l'application.
     * @param frame le JFrame à affecter
     */
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}
