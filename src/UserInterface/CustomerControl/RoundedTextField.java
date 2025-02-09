package UserInterface.CustomerControl;

import javax.swing.*;
import java.awt.*;

public class RoundedTextField extends JTextField {
    
    private int arcWidth = 25;  // Mayor curvatura en las esquinas
    private int arcHeight = 25;

    public RoundedTextField(int columns) {
        super(columns);
        setOpaque(false);
        setFont(new Font("Montserrat", Font.PLAIN, 16)); // Fuente más moderna y mayor tamaño
        setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15)); // Más padding interno
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar fondo redondeado más grande
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        
        // Dibujar borde azul prusiano
        g2.setColor(new Color(0x003459));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);

        g2.dispose();
        super.paintComponent(g);
    }
}
