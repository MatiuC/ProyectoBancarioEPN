package UserInterface.Form.DashboardClient;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelBienvenida extends JPanel{ 
    public PanelBienvenida() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setOpaque(false);

        // Ícono de Usuario y Mensaje de Bienvenida
        ImageIcon userIconRaw = new ImageIcon("path/to/your/icon.png");
        Image userIconImg = userIconRaw.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JLabel userIcon = new JLabel(new ImageIcon(userIconImg));
        JLabel welcomeLabel = new JLabel("Bienvenido, Usuario");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setForeground(Color.decode("#2F4858"));

        add(userIcon);
        add(welcomeLabel);
    }
}
