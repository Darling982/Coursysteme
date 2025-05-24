package GUI;


import dao.ConnectionDAO;
import dao.EtudiantDAO;
import model.Etudiants;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.awt.Font;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class AdminIHM {

    private JFrame jframe;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminIHM menu1 = new AdminIHM();
            menu1.jframe.setVisible(true);
        });
    }

    public AdminIHM() {
        initialize();
    }

   private void initialize() {
        jframe = new JFrame("Menu Adminisrateur");
        jframe.getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 14));
        jframe.getContentPane().setBackground(new Color(124, 124, 124));
        jframe.getContentPane().setForeground(new Color(0, 0, 0));
        jframe.setBounds(100, 100, 963, 742);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("GESTION ADMINISTRATTIF");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 38));
        lblNewLabel.setBounds(195, 0, 612, 92);
        jframe.getContentPane().add(lblNewLabel);

        
        
        // premiere fonction
        JButton btnNewButton = new JButton("SUIVI DES CHOIX");
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnNewButton.setBounds(46, 125, 313, 56);
        jframe.getContentPane().add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame saisieFrame = new JFrame("Rechercher un étudiant");
                saisieFrame.setSize(400, 300);
                saisieFrame.getContentPane().setLayout(null);

                JLabel nomLabel = new JLabel("Nom:");
                nomLabel.setBounds(50, 50, 100, 25);
                JTextField nomField = new JTextField();
                nomField.setBounds(150, 50, 180, 25);

                JLabel prenomLabel = new JLabel("Prénom:");
                prenomLabel.setBounds(50, 100, 100, 25);
                JTextField prenomField = new JTextField();
                prenomField.setBounds(150, 100, 180, 25);

                JButton searchBtn = new JButton("Rechercher");
                searchBtn.setBounds(130, 160, 120, 30);

                saisieFrame.getContentPane().add(nomLabel);
                saisieFrame.getContentPane().add(nomField);
                saisieFrame.getContentPane().add(prenomLabel);
                saisieFrame.getContentPane().add(prenomField);
                saisieFrame.getContentPane().add(searchBtn);

                saisieFrame.setVisible(true);

                searchBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String nom = nomField.getText().trim();
                        String prenom = prenomField.getText().trim();

                        ConnectionDAO cdao = new ConnectionDAO();
                        Connection conn = cdao.getConnection();
                        EtudiantDAO edao = new EtudiantDAO(conn);
                        Etudiants etu = edao.getByNomPrenom(nom, prenom);
                        
                        if (etu != null) {
                            List<String> dominantes = edao.getDominantesByUsername(etu.getUsername());
                            StringBuilder domiText = new StringBuilder();
                            if (dominantes.isEmpty()) {
                                domiText.append("Aucun choix enregistré.");
                            } else {
                                domiText.append("Dominantes choisies :\n");
                                for (String choix : dominantes) {
                                    domiText.append(" - ").append(choix).append("\n");
                                }
                            }

                            JOptionPane.showMessageDialog(saisieFrame,
                                    "Nom: " + etu.getNom() + "\n" +
                                    "Prénom: " + etu.getPrenom() + "\n" +
                                    "Filière: " + etu.getStatut() + "\n" +
                                    "Promo: " + etu.getPromo() + "\n" +
                                    "Date Naissance: " + etu.getDateNaissance() + "\n" +
                                    "Username: " + etu.getUsername() + "\n" +
                                    "Password: " + etu.getPassword() + "\n\n" +
                                    domiText.toString()
                                    
                            );
                        } else {
                            JOptionPane.showMessageDialog(saisieFrame, "❌ Étudiant non trouvé.");
                        }
                    }
                });
            }
        });

        JButton btnNewButton_1 = new JButton("Modifier l'ETAT de dominant");
        btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnNewButton_1.setBounds(602, 300, 313, 56);
        jframe.getContentPane().add(btnNewButton_1);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ProcedureFrame(); 
            }
        });
        
       

        JButton btnNewButton_2 = new JButton("MODIFIER LES INFOS");
        btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnNewButton_2.setBounds(602, 125, 311, 56);
        jframe.getContentPane().add(btnNewButton_2);
        
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame gestionFrame = new JFrame("SAISIR DES INFORMATIONS");
                gestionFrame.setSize(500, 400);
                gestionFrame.getContentPane().setLayout(null);

                // Panel pour le menu (boutons gauche)
                JPanel panelMenu = new JPanel();
                panelMenu.setBounds(10, 10, 200, 340);
                panelMenu.setLayout(null);
                gestionFrame.getContentPane().add(panelMenu);

                // Panel pour afficher les options dynamiquement
                JPanel panelContent = new JPanel();
                panelContent.setBounds(220, 10, 250, 340);
                panelContent.setLayout(null);
                gestionFrame.getContentPane().add(panelContent);

                // === BOUTON: GESTION ÉTUDIANTS ===
                JButton btnGestionEtudiants = new JButton("Gestion des étudiants");
                btnGestionEtudiants.setBounds(10, 30, 180, 30);
                panelMenu.add(btnGestionEtudiants);

                btnGestionEtudiants.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                        panelContent.removeAll();

                        JButton btnAddStudent = new JButton("Ajouter étudiant");
                        btnAddStudent.setBounds(20, 20, 200, 30);
                        panelContent.add(btnAddStudent);

                        JButton btnDeleteStudent = new JButton("Supprimer étudiant");
                        btnDeleteStudent.setBounds(20, 60, 200, 30);
                        panelContent.add(btnDeleteStudent);

                        panelContent.revalidate();
                        panelContent.repaint();

                        // Ajouter étudiant
                        btnAddStudent.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JFrame addFrame = new JFrame("Ajouter un étudiant");
                                addFrame.setSize(400, 440);
                                addFrame.getContentPane().setLayout(null);

                                String[] labels = { "Username", "Nom", "Prénom", "Filière", "Promo", "Date naissance (YYYY-MM-DD)", "Password", "Rang", "ID_PH" };
                                JTextField[] fields = new JTextField[labels.length];

                                for (int i = 0; i < labels.length; i++) {
                                    JLabel label = new JLabel(labels[i] + ":");
                                    label.setBounds(30, 30 + i * 40, 180, 25);
                                    addFrame.getContentPane().add(label);

                                    fields[i] = new JTextField();
                                    fields[i].setBounds(200, 30 + i * 40, 150, 25);
                                    addFrame.getContentPane().add(fields[i]);
                                }

                                JButton confirmBtn = new JButton("Ajouter");
                                confirmBtn.setBounds(130, 370, 120, 30);
                                addFrame.getContentPane().add(confirmBtn);

                                confirmBtn.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        try {
                                            // 1) Récupération des valeurs des champs
                                            String username  = fields[0].getText().trim();
                                            String nom       = fields[1].getText().trim();
                                            String prenom    = fields[2].getText().trim();
                                            String filiere   = fields[3].getText().trim();
                                            String promo     = fields[4].getText().trim();
                                            String dateNaiss = fields[5].getText().trim();    // doit être AAAA-MM-JJ
                                            String password  = fields[6].getText().trim();
                                            int    rang      = Integer.parseInt(fields[7].getText().trim());
                                            String idPhStr   = fields[8].getText().trim();     // peut être vide

                                            // 2) La requête avec 9 placeholders
                                            String sql = 
                                              "INSERT INTO ETUDIANT " +
                                              "(USERNAME, NOM, PRENOM, FILIERE, PROMO, DATENAISSANCE, PASSWORD, RANG, ID_PH) " +
                                              "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                                            try (Connection conn = ConnectionDAO.getConnection();
                                                 PreparedStatement ps = conn.prepareStatement(sql)) {

                                                // 3) Positionner les paramètres dans l’ordre
                                                int idx = 1;
                                                ps.setString(idx++, username);  
                                                ps.setString(idx++, nom);
                                                ps.setString(idx++, prenom);
                                                ps.setString(idx++, filiere);
                                                ps.setString(idx++, promo);
                                                // conversion de la date texte en java.sql.Date
                                                ps.setDate(  idx++, java.sql.Date.valueOf(dateNaiss));
                                                ps.setString(idx++, password);
                                                ps.setInt(   idx++, rang);

                                                // 4) ID_PH : NULL si vide, sinon la valeur
                                                if (idPhStr.isEmpty()) {
                                                    ps.setNull(idx++, java.sql.Types.INTEGER);
                                                } else {
                                                    ps.setInt(idx++, Integer.parseInt(idPhStr));
                                                }

                                                // 5) Exécuter
                                                ps.executeUpdate();
                                                JOptionPane.showMessageDialog(addFrame, "✅ Étudiant ajouté avec succès !");
                                                addFrame.dispose();
                                            }
                                        } 
                                        catch (Exception ex) {
                                            ex.printStackTrace();
                                            JOptionPane.showMessageDialog(addFrame, "❌ Erreur: " + ex.getMessage());
                                        }
                                    }
                                });


                                addFrame.setVisible(true);
                            }
                        });

                        // Supprimer étudiant
                        btnDeleteStudent.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JFrame delFrame = new JFrame("Supprimer un étudiant");
                                delFrame.setSize(350, 180);
                                delFrame.getContentPane().setLayout(null);

                                JLabel userLabel = new JLabel("Username de l'étudiant :");
                                userLabel.setBounds(20, 20, 300, 25);
                                delFrame.getContentPane().add(userLabel);

                                JTextField userField = new JTextField();
                                userField.setBounds(20, 50, 290, 25);
                                delFrame.getContentPane().add(userField);

                                JButton deleteBtn = new JButton("Supprimer");
                                deleteBtn.setBounds(100, 90, 120, 30);
                                delFrame.getContentPane().add(deleteBtn);

                                deleteBtn.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        String username = userField.getText().trim();
                                        try {
                                            Connection conn = ConnectionDAO.getConnection();
                                            Statement stmt = conn.createStatement();
                                            String sql = "DELETE FROM ETUDIANT WHERE USERNAME = '" + username + "'";
                                            int rows = stmt.executeUpdate(sql);
                                            if (rows > 0) {
                                                JOptionPane.showMessageDialog(delFrame, "✅ Étudiant supprimé !");
                                                delFrame.dispose();
                                            } else {
                                                JOptionPane.showMessageDialog(delFrame, "❌ Étudiant non trouvé.");
                                            }
                                        } catch (Exception ex) {
                                            JOptionPane.showMessageDialog(delFrame, "❌ Erreur: " + ex.getMessage());
                                        }
                                    }
                                });

                                delFrame.setVisible(true);
                            }
                        });
                        
                        
                        JButton btnShowStudents = new JButton("Afficher les étudiants");
                        btnShowStudents.setBounds(20, 100, 200, 30);
                        panelContent.add(btnShowStudents);

                        btnShowStudents.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JFrame listFrame = new JFrame("Liste des étudiants");
                                listFrame.setSize(800, 400);
                                listFrame.getContentPane().setLayout(new BorderLayout());

                                try {
                                    Connection conn = ConnectionDAO.getConnection();
                                    Statement stmt = conn.createStatement();
                                    ResultSet rs = stmt.executeQuery("SELECT * FROM ETUDIANT");

                                    // Obtenir metadata pour les noms de colonnes
                                    ResultSetMetaData meta = rs.getMetaData();
                                    int columnCount = meta.getColumnCount();
                                    String[] columnNames = new String[columnCount];
                                    for (int i = 1; i <= columnCount; i++) {
                                        columnNames[i - 1] = meta.getColumnName(i);
                                    }

                                    // Ajouter les lignes
                                    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
                                    while (rs.next()) {
                                        Object[] row = new Object[columnCount];
                                        for (int i = 1; i <= columnCount; i++) {
                                            row[i - 1] = rs.getObject(i);
                                        }
                                        model.addRow(row);
                                    }

                                    JTable table = new JTable(model);
                                    JScrollPane scrollPane = new JScrollPane(table);
                                    listFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(listFrame, "❌ Erreur : " + ex.getMessage());
                                }

                                listFrame.setVisible(true);
                            }
                        });

                    }
                });

                // === BOUTON: GESTION DOMINANTES ===
                JButton btnGestionDominantes = new JButton("Gestion des dominantes");
                btnGestionDominantes.setBounds(10, 80, 180, 30);
                panelMenu.add(btnGestionDominantes);

                btnGestionDominantes.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                        panelContent.removeAll();

                        JButton btnAddDom = new JButton("Ajouter dominante");
                        btnAddDom.setBounds(20, 20, 200, 30);
                        panelContent.add(btnAddDom);

                        JButton btnDelDom = new JButton("Supprimer dominante");
                        btnDelDom.setBounds(20, 60, 200, 30);
                        panelContent.add(btnDelDom);

                        panelContent.revalidate();
                        panelContent.repaint();

                        // Ajouter dominante
                        btnAddDom.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JFrame addDomFrame = new JFrame("Ajouter dominante");
                                addDomFrame.setSize(400, 300);
                                addDomFrame.getContentPane().setLayout(null);

                                JLabel nomLabel = new JLabel("Nom dominante:");
                                nomLabel.setBounds(30, 30, 150, 25);
                                JTextField nomField = new JTextField();
                                nomField.setBounds(180, 30, 150, 25);

                                JLabel placesLabel = new JLabel("Places dispo:");
                                placesLabel.setBounds(30, 70, 150, 25);
                                JTextField placesField = new JTextField("33");
                                placesField.setBounds(180, 70, 150, 25);

                                JLabel prisesLabel = new JLabel("Places prises:");
                                prisesLabel.setBounds(30, 110, 150, 25);
                                JTextField prisesField = new JTextField("0");
                                prisesField.setBounds(180, 110, 150, 25);

                                JLabel idphLabel = new JLabel("ID_PH_D (optionnel):");
                                idphLabel.setBounds(30, 150, 150, 25);
                                JTextField idphField = new JTextField();
                                idphField.setBounds(180, 150, 150, 25);

                                JButton confirmBtn = new JButton("Ajouter");
                                confirmBtn.setBounds(130, 200, 120, 30);

                                addDomFrame.getContentPane().add(nomLabel); addDomFrame.getContentPane().add(nomField);
                                addDomFrame.getContentPane().add(placesLabel); addDomFrame.getContentPane().add(placesField);
                                addDomFrame.getContentPane().add(prisesLabel); addDomFrame.getContentPane().add(prisesField);
                                addDomFrame.getContentPane().add(idphLabel); addDomFrame.getContentPane().add(idphField);
                                addDomFrame.getContentPane().add(confirmBtn);

                                confirmBtn.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        String nom = nomField.getText().trim();
                                        String dispo = placesField.getText().trim();
                                        String prises = prisesField.getText().trim();
                                        String idph = idphField.getText().trim();

                                        try {
                                            Connection conn = ConnectionDAO.getConnection();
                                            Statement stmt = conn.createStatement();
                                            String sql;
                                            if (idph.isEmpty()) {
                                                sql = "INSERT INTO DOMINANTES (NOM, PLACESDISPO, PLACESPRISES, ID_PH_D) VALUES ('" + nom + "', " + dispo + ", " + prises + ", NULL)";
                                            } else {
                                                sql = "INSERT INTO DOMINANTES (NOM, PLACESDISPO, PLACESPRISES, ID_PH_D) VALUES ('" + nom + "', " + dispo + ", " + prises + ", " + idph + ")";
                                            }
                                            stmt.executeUpdate(sql);
                                            JOptionPane.showMessageDialog(addDomFrame, "✅ Dominante ajoutée !");
                                            addDomFrame.dispose();
                                        } catch (Exception ex) {
                                            JOptionPane.showMessageDialog(addDomFrame, "❌ Erreur: " + ex.getMessage());
                                        }
                                    }
                                });

                                addDomFrame.setVisible(true);
                            }
                        });

                        // Supprimer dominante
                        btnDelDom.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JFrame delDomFrame = new JFrame("Supprimer dominante");
                                delDomFrame.setSize(350, 180);
                                delDomFrame.getContentPane().setLayout(null);

                                JLabel label = new JLabel("Nom dominante :");
                                label.setBounds(20, 20, 300, 25);
                                JTextField nomField = new JTextField();
                                nomField.setBounds(20, 50, 290, 25);

                                JButton deleteBtn = new JButton("Supprimer");
                                deleteBtn.setBounds(100, 90, 120, 30);

                                delDomFrame.getContentPane().add(label);
                                delDomFrame.getContentPane().add(nomField);
                                delDomFrame.getContentPane().add(deleteBtn);

                                deleteBtn.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        String nom = nomField.getText().trim();
                                        try {
                                            Connection conn = ConnectionDAO.getConnection();
                                            Statement stmt = conn.createStatement();
                                            String sql = "DELETE FROM DOMINANTES WHERE NOM = '" + nom + "'";
                                            int rows = stmt.executeUpdate(sql);
                                            if (rows > 0) {
                                                JOptionPane.showMessageDialog(delDomFrame, "✅ Dominante supprimée !");
                                                delDomFrame.dispose();
                                            } else {
                                                JOptionPane.showMessageDialog(delDomFrame, "❌ Aucune dominante trouvée.");
                                            }
                                        } catch (Exception ex) {
                                            JOptionPane.showMessageDialog(delDomFrame, "❌ Erreur: " + ex.getMessage());
                                        }
                                    }
                                });

                                delDomFrame.setVisible(true);
                            }
                        });
                        
                        JButton btnShowDominantes = new JButton("Afficher les dominantes");
                        btnShowDominantes.setBounds(20, 100, 200, 30);
                        panelContent.add(btnShowDominantes);

                        btnShowDominantes.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JFrame listFrame = new JFrame("Liste des dominantes");
                                listFrame.setSize(600, 300);
                                listFrame.getContentPane().setLayout(new BorderLayout());

                                try {
                                    Connection conn = ConnectionDAO.getConnection();
                                    Statement stmt = conn.createStatement();
                                    ResultSet rs = stmt.executeQuery("SELECT * FROM DOMINANTES");

                                    ResultSetMetaData meta = rs.getMetaData();
                                    int columnCount = meta.getColumnCount();
                                    String[] columnNames = new String[columnCount];
                                    for (int i = 1; i <= columnCount; i++) {
                                        columnNames[i - 1] = meta.getColumnName(i);
                                    }

                                    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
                                    while (rs.next()) {
                                        Object[] row = new Object[columnCount];
                                        for (int i = 1; i <= columnCount; i++) {
                                            row[i - 1] = rs.getObject(i);
                                        }
                                        model.addRow(row);
                                    }

                                    JTable table = new JTable(model);
                                    JScrollPane scrollPane = new JScrollPane(table);
                                    listFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(listFrame, "❌ Erreur : " + ex.getMessage());
                                }

                                listFrame.setVisible(true);
                            }
                        });

                    }
                });

                gestionFrame.setVisible(true);
            }
        });

         
        JButton btnAutoAffect = new JButton("VALIDER");
        btnAutoAffect.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnAutoAffect.setBounds(349, 602, 313, 56);
        jframe.getContentPane().add(btnAutoAffect);

        btnAutoAffect.addActionListener(e -> {
            try {
                Connection conn = ConnectionDAO.getConnection();
                conn.setAutoCommit(false);

                dao.ChoixDAO.autoDistribuerSansChoix(conn);

                conn.commit(); 
                JOptionPane.showMessageDialog(jframe, "✅ Attribution automatique terminée !");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(jframe, "❌ Erreur lors de l'attribution.");
            }
        });
        
       
        JButton btnDistribuer3 = new JButton("AUTOCLASSEMENT");
        btnDistribuer3.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnDistribuer3.setBounds(602, 454, 313, 56); 
        jframe.getContentPane().add(btnDistribuer3);

        btnDistribuer3.addActionListener(e -> {
            try {
                Connection conn = ConnectionDAO.getConnection();
                conn.setAutoCommit(false);

                dao.ChoixDAO.autoDistribuerSansChoix(conn);
                conn.commit();

                JOptionPane.showMessageDialog(jframe, "🎯 Autodistribution terminée !");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(jframe, "❌ Erreur dans l’autodistribution.");
            }
        });
      
        JButton btnForcer = new JButton("FORCER INSCRIPTION");
        btnForcer.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnForcer.setBounds(46, 454, 313, 56);
        jframe.getContentPane().add(btnForcer);

        btnForcer.addActionListener(e -> {
            new ForcerInscriptionFrame();
        });
        
         
         JButton btnLancerProcedure = new JButton("LANCER LA PROCEDURE");
         btnLancerProcedure.setFont(new Font("Times New Roman", Font.BOLD, 18));
         btnLancerProcedure.setBounds(46, 300, 313, 56);
         jframe.getContentPane().add(btnLancerProcedure);
         
         JButton btnNewButton_3 = new JButton("Retour");
         btnNewButton_3.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		jframe.dispose();
         		SwingUtilities.invokeLater(() -> {
    	            loginGUI login = new loginGUI();
    	            login.getFrame().setVisible(true);
    	        });
         	}
         });
         btnNewButton_3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
         btnNewButton_3.setBounds(25, 32, 85, 21);
         jframe.getContentPane().add(btnNewButton_3);
        btnLancerProcedure.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		
         		 FenetreDates fenDates = new FenetreDates();
                 fenDates.setVisible(true);
             
         	}
         });
        
        jframe.setVisible(true);
   
                                    
                                    }
                                }
                  
                                        
                                  