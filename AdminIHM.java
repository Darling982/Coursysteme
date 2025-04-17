package GUI;

import dao.ConnectionDAO;
import dao.EtudiantDAO;
import model.Etudiants;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import model.Admin;
import java.awt.Font;
import java.awt.Color;

public class AdminIHM {

	 private JFrame jframe;
	    
	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            AdminIHM menu1 = new  AdminIHM();
	            menu1.jframe.setVisible(true);
	        });
	    }   
	     public AdminIHM() {
		        initialize();
		    }

		  
		    private void initialize() {
		    	 jframe = new JFrame("Menu Adminisrateur");
			        jframe.getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 14));
			        jframe.getContentPane().setBackground(new Color(175, 96, 255));
			        jframe.getContentPane().setForeground(new Color(0, 0, 0));
			        jframe.setBounds(100, 100, 600, 650);
			        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			        jframe.getContentPane().setLayout(null);
			        
			        JLabel lblNewLabel = new JLabel("MENU");
			        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 38));
			        lblNewLabel.setBounds(210, 42, 167, 92);
			        jframe.getContentPane().add(lblNewLabel);
			        
			        JButton btnNewButton = new JButton("SAISIR INFOS ETUDIANTS");
			        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
			        btnNewButton.setBounds(125, 166, 313, 56);
			        jframe.getContentPane().add(btnNewButton);
			        
			        JButton btnNewButton_1 = new JButton("LANCEMENT PROCEDURE");
			        btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
			        btnNewButton_1.setBounds(125, 273, 313, 56);
			        jframe.getContentPane().add(btnNewButton_1);
			        
			        JButton btnNewButton_2 = new JButton("PARAMETRAGE DES CHOIX");
			        btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
			        btnNewButton_2.setBounds(125, 371, 311, 56);
			        jframe.getContentPane().add(btnNewButton_2);
			        
			        JButton btnNewButton_3 = new JButton("SUIVI DES CHOIX");
			        btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
			        btnNewButton_3.setBounds(125, 473, 313, 56);
			        jframe.getContentPane().add(btnNewButton_3);
			        jframe.setVisible(true);
			        
		    	
		    }
}
