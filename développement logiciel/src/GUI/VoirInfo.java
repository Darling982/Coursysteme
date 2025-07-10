package GUI;

/*import dao.EtudiantDAO;
import model.Dominantes;
import model.Etudiants;
import stockage.StudentStatut;
import model.FISE;
import model.FISA;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VoirInfo {

    private JFrame frame;
    
    private final JButton button = new JButton("New button");
    
    

    public VoirInfo() {
    	
    	
        initialize();
        
    }
    
    

    private void initialize() {
    	
        frame = new JFrame("Informations Étudiant");
        frame.setBounds(100, 100, 800, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(240, 240, 255));
        frame.getContentPane().setLayout(new BorderLayout());

        // Titre
        JLabel title = new JLabel("Mes Informations", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(56, 0, 128));
        frame.getContentPane().add(title, BorderLayout.NORTH);

        // === Panel principal avec BoxLayout pour empiler les éléments verticalement ===
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(240, 240, 255));

        // Panel pour les infos étudiant
        JPanel panelInfos = new JPanel();
        panelInfos.setLayout(new GridLayout(6, 2, 10, 10));  // 6 lignes pour toutes les infos
        panelInfos.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        panelInfos.setBackground(new Color(255, 255, 255));

        // Récupération des infos étudiant
        String username = StudentStatut.getIdStudent();
        Etudiants etu = EtudiantDAO.getStudentInfos(username);

        if (etu != null) {
            panelInfos.add(new JLabel("Nom :"));
            panelInfos.add(new JLabel(etu.getNom()));

            panelInfos.add(new JLabel("Prénom :"));
            panelInfos.add(new JLabel(etu.getPrenom()));

            panelInfos.add(new JLabel("Rang :"));
            if (etu instanceof FISE) {
                panelInfos.add(new JLabel(String.valueOf(((FISE) etu).getRang())));
            } else if (etu instanceof FISA) {
                panelInfos.add(new JLabel(String.valueOf(((FISA) etu).getRang())));
            } else {
                panelInfos.add(new JLabel("Non disponible"));
            }

            panelInfos.add(new JLabel("Promotion :"));
            panelInfos.add(new JLabel(String.valueOf(etu.getPromo())));

            panelInfos.add(new JLabel("Statut :"));
            panelInfos.add(new JLabel(etu.getStatut()));

            panelInfos.add(new JLabel("Date de Naissance :"));
            panelInfos.add(new JLabel(etu.getDateNaissance()));
        } else {
            JOptionPane.showMessageDialog(null, "Impossible de récupérer les informations de l'étudiant.");
        }

        // === Ajout du panelInfos au panel principal ===
        mainPanel.add(panelInfos);

        // === Création du tableau des dominantes ===
        String[] columnNames = {"Nom de la Dominante", "Places Disponibles", "Places Prises"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        List<Dominantes> dominantes = EtudiantDAO.getAllDominantesFull();
        for (Dominantes d : dominantes) {
            Object[] row = {
                d.getNom(),
                d.getPlacesDispo(),
                d.getPlacesPrises()
            };
            model.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Statistiques des Dominantes"));

        // === Ajout du tableau au panel principal ===
        mainPanel.add(scrollPane);

        // === Ajout du panel principal au frame ===
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
    
}*/


/*import java.util.ArrayList; // pour les listes
import dao.EtudiantDAO;
import model.Dominantes;
import model.Etudiants;
import stockage.StudentStatut;
import model.FISE;
import model.FISA;
import dao.ConnectionDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class VoirInfo {

    private JFrame frame;

    public VoirInfo() {
        FlatLightLaf.setup(); // Initialisation du style moderne
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Informations Étudiant");
        frame.setBounds(100, 100, 800, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(240, 240, 255));
        frame.getContentPane().setLayout(new BorderLayout());

        // Bouton retour
        JButton retourButton = new JButton("← Retour");
        retourButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        retourButton.setForeground(new Color(56, 0, 128));
        retourButton.setFocusPainted(false);
        retourButton.setBorderPainted(false);
        retourButton.setContentAreaFilled(false);
        retourButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        retourButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SwingUtilities.invokeLater(() -> {
                    EtudiantIHM ihm = new EtudiantIHM();
                    ihm.getJframe().setVisible(true);
                });
            }
        });
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(240, 240, 255));
        topPanel.add(retourButton, BorderLayout.WEST);

        // Titre
        JLabel title = new JLabel("Mes Informations", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(56, 0, 128));
        topPanel.add(title, BorderLayout.CENTER);

        frame.getContentPane().add(topPanel, BorderLayout.NORTH);

        // Panel principal avec BoxLayout pour empiler les éléments verticalement
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(240, 240, 255));

        // Panel pour les infos étudiant
        JPanel panelInfos = new JPanel(new GridLayout(6, 2, 10, 10));
        panelInfos.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        panelInfos.setBackground(Color.WHITE);

        // Récupération des infos étudiant
        String username = StudentStatut.getIdStudent();
        Etudiants etu = EtudiantDAO.getStudentInfos(username);

        if (etu != null) {
            panelInfos.add(new JLabel("Nom :"));
            panelInfos.add(new JLabel(etu.getNom()));

            panelInfos.add(new JLabel("Prénom :"));
            panelInfos.add(new JLabel(etu.getPrenom()));

            panelInfos.add(new JLabel("Rang :"));
            if (etu instanceof FISE) {
                panelInfos.add(new JLabel(String.valueOf(((FISE) etu).getRang())));
            } else if (etu instanceof FISA) {
                panelInfos.add(new JLabel(String.valueOf(((FISA) etu).getRang())));
            } else {
                panelInfos.add(new JLabel("Non disponible"));
            }

            panelInfos.add(new JLabel("Promotion :"));
            panelInfos.add(new JLabel(String.valueOf(etu.getPromo())));

            panelInfos.add(new JLabel("Statut :"));
            panelInfos.add(new JLabel(etu.getStatut()));

            panelInfos.add(new JLabel("Date de Naissance :"));
            panelInfos.add(new JLabel(etu.getDateNaissance()));
        } else {
            JOptionPane.showMessageDialog(null, "Impossible de récupérer les informations de l'étudiant.");
        }

        mainPanel.add(panelInfos);
     // --- Afficher les choix de dominantes des étudiants ---
        JPanel panelChoix = new JPanel();
        panelChoix.setLayout(new BoxLayout(panelChoix, BoxLayout.Y_AXIS));
        panelChoix.setBackground(Color.WHITE);
        panelChoix.setBorder(BorderFactory.createTitledBorder("Mes choix de dominantes"));

        EtudiantDAO dao = new EtudiantDAO(ConnectionDAO.getConnection());
        List<String> choix = dao.getDominantesByUsername(username);
        if (choix != null && !choix.isEmpty()) {
            for (int i = 0; i < choix.size(); i++) {
                JLabel label = new JLabel((i + 1) + "ᵉ choix : " + choix.get(i));
                panelChoix.add(label);
            }
        } else {
            panelChoix.add(new JLabel("Aucun choix trouvé."));
        }
        
     // --- Afficher la dominante finale obtenue ---
        JPanel panelAffectee = new JPanel();
        panelAffectee.setBackground(Color.WHITE);
        panelAffectee.setBorder(BorderFactory.createTitledBorder("Dominante obtenue"));

        String dominanteFinale = etu.getDominanteFinale();
        JLabel labelFinale = new JLabel(dominanteFinale != null ? dominanteFinale : "Non encore affecté");
        labelFinale.setFont(new Font("Segoe UI", Font.BOLD, 16));
        labelFinale.setForeground(new Color(0, 102, 204));
        panelAffectee.add(labelFinale);

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(panelAffectee);


        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    
    public JFrame getFrame() {
        return frame;
    }
} */


import dao.EtudiantDAO;
import dao.ConnectionDAO;
import model.Etudiants;
import stockage.StudentStatut;
import model.FISE;
import model.FISA;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.List;

public class VoirInfo {

    private JFrame frame;

    public VoirInfo() {
        FlatLightLaf.setup(); // Initialisation du style moderne
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Informations Étudiant");
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(240, 240, 255));
        frame.getContentPane().setLayout(new BorderLayout());

        // Connexion DB
        Connection conn = ConnectionDAO.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données.");
            return;
        }

        // DAO
        EtudiantDAO dao = new EtudiantDAO(conn);
        String username = StudentStatut.getIdStudent();
        Etudiants etu = dao.getStudentInfos(username);

        // Haut : titre + retour
        JButton retourButton = new JButton("← Retour");
        retourButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        retourButton.setForeground(new Color(56, 0, 128));
        retourButton.setFocusPainted(false);
        retourButton.setBorderPainted(false);
        retourButton.setContentAreaFilled(false);
        retourButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        retourButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SwingUtilities.invokeLater(() -> {
                    EtudiantIHM ihm = new EtudiantIHM();
                    ihm.getJframe().setVisible(true);
                });
            }
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(240, 240, 255));
        topPanel.add(retourButton, BorderLayout.WEST);

        JLabel title = new JLabel("Mes Informations", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(56, 0, 128));
        topPanel.add(title, BorderLayout.CENTER);

        frame.getContentPane().add(topPanel, BorderLayout.NORTH);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(240, 240, 255));

        if (etu != null) {
            // Infos personnelles
            JPanel panelInfos = new JPanel(new GridLayout(6, 2, 10, 10));
            panelInfos.setBorder(BorderFactory.createTitledBorder("Informations personnelles"));
            panelInfos.setBackground(Color.WHITE);

            panelInfos.add(new JLabel("Nom :"));
            panelInfos.add(new JLabel(etu.getNom()));

            panelInfos.add(new JLabel("Prénom :"));
            panelInfos.add(new JLabel(etu.getPrenom()));

            panelInfos.add(new JLabel("Rang :"));
            if (etu instanceof FISE) {
                panelInfos.add(new JLabel(String.valueOf(((FISE) etu).getRang())));
            } else if (etu instanceof FISA) {
                panelInfos.add(new JLabel(String.valueOf(((FISA) etu).getRang())));
            } else {
                panelInfos.add(new JLabel("Non disponible"));
            }

            panelInfos.add(new JLabel("Promotion :"));
            panelInfos.add(new JLabel(String.valueOf(etu.getPromo())));

            panelInfos.add(new JLabel("Statut :"));
            panelInfos.add(new JLabel(etu.getStatut()));

            panelInfos.add(new JLabel("Date de Naissance :"));
            panelInfos.add(new JLabel(etu.getDateNaissance()));

            mainPanel.add(panelInfos);

            // Choix de dominantes
            JPanel panelChoix = new JPanel();
            panelChoix.setLayout(new BoxLayout(panelChoix, BoxLayout.Y_AXIS));
            panelChoix.setBackground(Color.WHITE);
            panelChoix.setBorder(BorderFactory.createTitledBorder("Mes choix de dominantes"));

            List<String> choix = dao.getDominantesByUsername(username);
            if (choix != null && !choix.isEmpty()) {
                for (int i = 0; i < choix.size(); i++) {
                    JLabel label = new JLabel((i + 1) + "ᵉ choix : " + choix.get(i));
                    panelChoix.add(label);
                }
            } else {
                panelChoix.add(new JLabel(" Dominante déjà attribuée "));
            }
            mainPanel.add(Box.createVerticalStrut(10));
            mainPanel.add(panelChoix);

            // Dominante obtenue
            JPanel panelAffectee = new JPanel();
            panelAffectee.setBackground(Color.WHITE);
            panelAffectee.setBorder(BorderFactory.createTitledBorder("Dominante obtenue"));

            String dominanteFinale = dao.getDominanteObtenue(username);
            JLabel labelFinale = new JLabel(dominanteFinale != null ? dominanteFinale : "Non encore affecté");
            labelFinale.setFont(new Font("Segoe UI", Font.BOLD, 16));
            labelFinale.setForeground(new Color(0, 102, 204));
            panelAffectee.add(labelFinale);

            mainPanel.add(Box.createVerticalStrut(10));
            mainPanel.add(panelAffectee);

        } else {
            JOptionPane.showMessageDialog(null, "Impossible de récupérer les informations de l'étudiant.");
        }

        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}

