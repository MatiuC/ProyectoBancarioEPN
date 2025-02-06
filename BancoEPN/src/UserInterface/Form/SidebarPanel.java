package UserInterface.Form;

import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JPanel {

    public SidebarPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(33, 33, 33));  // Fondo oscuro de la barra lateral
        setPreferredSize(new Dimension(250, getHeight()));

        // Crear las opciones del menú
        addMenuOption("Home", new ImageIcon("path/to/home_icon.png"));
        addMenuOption("Portfolio", new ImageIcon("path/to/portfolio_icon.png"));
        addMenuOption("Assets", new ImageIcon("path/to/assets_icon.png"));
        addMenuOption("Transactions", new ImageIcon("path/to/transactions_icon.png"));
        addMenuOption("Settings", new ImageIcon("path/to/settings_icon.png"));
    }

    private void addMenuOption(String optionName, ImageIcon icon) {
        JPanel menuOption = new JPanel();
        menuOption.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuOption.setBackground(new Color(33, 33, 33));

        JLabel iconLabel = new JLabel(icon);
        JLabel textLabel = new JLabel(optionName);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        menuOption.add(iconLabel);
        menuOption.add(textLabel);

        // Cambiar el color al pasar el mouse
        menuOption.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuOption.setBackground(new Color(50, 50, 50));  // Efecto de hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuOption.setBackground(new Color(33, 33, 33));  // Restaurar color original
            }
        });

        menuOption.setPreferredSize(new Dimension(240, 50));
        add(menuOption);
    }
}
