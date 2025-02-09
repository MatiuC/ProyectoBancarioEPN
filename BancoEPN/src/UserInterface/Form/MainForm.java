package UserInterface.Form;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {

    private JPanel contentPanel;
    private CardLayout cardLayout;

    public MainForm() {
        try {
            setTitle("Banco EPN");
            setSize(1000, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // Configuración del layout principal
            JPanel mainPanel = new JPanel(new BorderLayout());
            MenuPanel menuPanel = new MenuPanel();  // Cambié el constructor para no pasar la referencia del frame

            // Panel de contenido con CardLayout para cambiar entre vistas
            contentPanel = new JPanel();
            cardLayout = new CardLayout();
            contentPanel.setLayout(cardLayout);

            // Agregar las diferentes vistas
            contentPanel.add(new InicioPanel(), "Inicio"); // Imagen de fondo
            // Vincular las vistas con el menú
            menuPanel.addMenuOption("Home", "src/UserInterface/Resource/Icons/Home.png");
            menuPanel.addMenuOption("Profile", "src/UserInterface/Resource/Icons/Ayuda.png");
            menuPanel.addMenuOption("Assets", "src/UserInterface/Resource/Icons/Asesor.png");
            menuPanel.addMenuOption("Transactions", "src/UserInterface/Resource/Icons/Cliente.png");
            menuPanel.addMenuOption("Settings", "src/UserInterface/Resource/Icons/Ayuda.png");

            // Configuración de los paneles
            mainPanel.add(menuPanel, BorderLayout.WEST);
            mainPanel.add(contentPanel, BorderLayout.CENTER);

            add(mainPanel);
        } catch (Exception e) {
            e.printStackTrace();  // Esto imprimirá el error completo para poder revisarlo.
        }
    }

    // Método para cambiar de vista en el CardLayout
    public void cambiarVista(String nombrePanel) {
        cardLayout.show(contentPanel, nombrePanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainForm mainForm = new MainForm();
            mainForm.setVisible(true);
        });
    }
}
