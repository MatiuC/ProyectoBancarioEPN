package UserInterface.CustomerControl;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;


public abstract class TransparentButton extends JButton {
    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        
        return button;
    }
}
