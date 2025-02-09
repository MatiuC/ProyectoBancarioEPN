package UserInterface.CustomerControl;

import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.SwingConstants;


public abstract class TransparentButton extends JButton {
     public static JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(Color.decode("#2F4858"));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);

        // Establecemos el icono a la izquierda del texto
        ImageIcon icon = new ImageIcon(iconPath);
        button.setIcon(new ImageIcon(icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));

        // Ajustamos la alineación del icono y el texto
        button.setHorizontalTextPosition(SwingConstants.RIGHT);  // Texto a la derecha del icono
        button.setHorizontalAlignment(SwingConstants.LEFT); // Alineación del texto con el icono

        return button;
    }
}
