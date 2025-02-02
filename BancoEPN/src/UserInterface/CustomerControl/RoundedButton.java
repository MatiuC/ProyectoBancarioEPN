package UserInterface.CustomerControl;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {

    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 14));
        setPreferredSize(new Dimension(150, 50));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(0x003459));  // Color de fondo azul prusiano
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Bordes redondeados con radio 30
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);  // Color del borde blanco
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30); // Bordes redondeados con radio 30
        g2.dispose();
    }
}
