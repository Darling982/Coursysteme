package GUI;

import javax.swing.*;
import java.awt.*;
/**
 * @autho Equipe7_4
 * @version finale
 * classe pour insérer une image
 */
public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    
    /**
     * construteur de la classe
     * @param imageIcon
     */
    public BackgroundPanel(ImageIcon imageIcon) {
        this.backgroundImage = imageIcon.getImage();
        // Redimensionne le panel à la taille de l'image
        this.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
        this.setLayout(null); // Pour placer les composants librement
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dessine l'image de fond
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}