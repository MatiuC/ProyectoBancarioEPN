package UserInterface.Form;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {

    private JPanel contentPanel;
    private CardLayout cardLayout;
    
    public MainForm() {
        setTitle("Banco EPN");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Configuración del layout principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel menuPanel = new MenuPanel(this);  // Pasamos la referencia del Frame principal
        
        // Panel de contenido con CardLayout para cambiar entre vistas
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        // Agregar las diferentes vistas
        contentPanel.add(new InicioPanel(), "Inicio"); // Imagen de fondo
        contentPanel.add(new ClienteFormPanel(this), "RegistroClientes"); // Formulario de clientes
        
        mainPanel.add(menuPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);
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
