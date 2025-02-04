package UserInterface.Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class MenuPanel extends JPanel {
    private JPanel selectedPanel;  // Para mantener el panel seleccionado actual
    private final Color hoverColor = new Color(50, 50, 50);  // Color cuando el mouse está sobre el panel
    private final Color defaultColor = new Color(33, 33, 33);  // Color de fondo predeterminado (oscuro)
    private JPanel sidebarPanel;
    private Map<JPanel, String> menuOptions;

    public MenuPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(defaultColor);

        // Crear el panel de la barra lateral (inicialmente invisible o delgada)
        sidebarPanel = new JPanel();
        sidebarPanel.setBackground(new Color(255, 165, 0));  // Naranja
        sidebarPanel.setPreferredSize(new Dimension(5, 50));  // Ancho de la barra lateral
        add(sidebarPanel);

        menuOptions = new HashMap<>();

        // Añadir opciones de menú
        addMenuOption("Home", "home_icon.png");
        addMenuOption("Profile", "profile_icon.png");
        addMenuOption("Assets", "assets_icon.png");
        addMenuOption("Transactions", "transactions_icon.png");
        addMenuOption("Settings", "settings_icon.png");
    }

    // Método público para agregar una opción al menú con ícono y texto
    public void addMenuOption(String optionName, String iconPath) {
        JPanel menuOption = new JPanel();
        menuOption.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuOption.setBackground(defaultColor);

        // Crear los elementos: ícono y texto
        ImageIcon icon = new ImageIcon(getClass().getResource("/UserInterface/Resource/Icons/" + iconPath));
        JLabel iconLabel = new JLabel(icon);
        JLabel textLabel = new JLabel(optionName);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        
        // Agregar al panel
        menuOption.add(iconLabel);
        menuOption.add(textLabel);

        menuOption.setPreferredSize(new Dimension(200, 50));

        // Listener para el cambio de color al pasar el mouse
        menuOption.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menuOption.setBackground(hoverColor); // Cambiar color cuando el mouse pasa por encima
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (menuOption != selectedPanel) {
                    menuOption.setBackground(defaultColor); // Volver al color original si no está seleccionado
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (selectedPanel != null) {
                    selectedPanel.setBackground(defaultColor); // Restablecer el color del panel previamente seleccionado
                }
                menuOption.setBackground(new Color(255, 165, 0)); // Cambiar el color cuando se selecciona
                selectedPanel = menuOption; // Actualizar el panel seleccionado

                // Actualizar la barra lateral para que coincida con la opción seleccionada
                sidebarPanel.setPreferredSize(new Dimension(5, 50)); // Ajusta el tamaño de la barra lateral
                revalidate();
                repaint();
            }
        });

        add(menuOption); // Añadir al panel principal
        menuOptions.put(menuOption, optionName); // Guardamos el panel junto con su nombre
    }
}
