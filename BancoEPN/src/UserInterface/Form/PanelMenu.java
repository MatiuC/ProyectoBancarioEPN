package UserInterface.Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelMenu {

    private static JButton selectedButton;
    
    public PanelMenu() {
        // Crear la ventana principal
        JFrame frame = new JFrame("Aplicación con Panel Lateral");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear un panel principal con BorderLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Crear el panel lateral y los botones
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS)); 
        sidebar.setBackground(new Color(50, 50, 50)); 
        sidebar.setPreferredSize(new Dimension(200, 0)); 

        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon("src\\UserInterface\\Resource\\Img\\LOGO.jpg"); 
        Image logoImage = logoIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); 
        logoIcon = new ImageIcon(logoImage);
        logoLabel.setIcon(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        sidebar.add(logoLabel); 

        // Crear los botones para la barra lateral
        JButton btnHome = new JButton("Home");
        JButton btnCreateNew = new JButton("Registrar Clientes");
        JButton btnRegisterCard = new JButton("Registrar Tarjeta");
        JButton btncerrar = new JButton("Cerrar sesión");
        

        styleSidebarButton(btnHome, "src\\UserInterface\\Resource\\Icon\\Home.png"); 
        styleSidebarButton(btnCreateNew, "src\\UserInterface\\Resource\\Icon\\Cliente.png");
        styleSidebarButton(btnRegisterCard, "src\\UserInterface\\Resource\\Icon\\Tarjeta.png");
        styleSidebarButton(btncerrar, "src\\UserInterface\\Resource\\Icon\\IconUser.png");

        sidebar.add(btnHome);
        sidebar.add(btnCreateNew);
        sidebar.add(btnRegisterCard);
        sidebar.add(btncerrar);
        // Crear un panel de contenido donde el formulario cambiará
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(new BorderLayout());

        btnHome.addActionListener(e -> {
            // Cargar la imagen que deseas mostrar
            ImageIcon homeImageIcon = new ImageIcon("src\\UserInterface\\Resource\\Img\\forest-landscape.jpg"); // Cambia la ruta a la imagen que desees
            Image homeImage = homeImageIcon.getImage();
            // Redimensionar la imagen para que se ajuste al tamaño del panel de contenido
            homeImage = homeImage.getScaledInstance(contentPanel.getWidth(), contentPanel.getHeight(), Image.SCALE_SMOOTH);
            homeImageIcon = new ImageIcon(homeImage);
            // Crear un JLabel con la imagen
            JLabel homeLabel = new JLabel(homeImageIcon, SwingConstants.CENTER);
            // Limpiar el panel de contenido y agregar la imagen
            contentPanel.removeAll();
            contentPanel.add(homeLabel, BorderLayout.CENTER); 
            // Actualizar el panel de contenido
            contentPanel.revalidate();
            contentPanel.repaint();
            // Cambiar el color del botón seleccionado
            updateButtonColor(btnHome);
        });

        btnCreateNew.addActionListener(e -> {
            contentPanel.removeAll();  
            contentPanel.revalidate();  
            contentPanel.repaint();  

            // Crear y mostrar el formulario de RegistroClientesForm
            RegistroClientesForm registroForm = new RegistroClientesForm();
            JPanel formPanel = registroForm.getFormularioPanel();
            contentPanel.add(formPanel, BorderLayout.CENTER);
            contentPanel.revalidate();
            contentPanel.repaint();
            updateButtonColor(btnCreateNew); 
        });

            

        btnRegisterCard.addActionListener(e -> {
            contentPanel.removeAll();
            contentPanel.revalidate();
            contentPanel.repaint();

            // Crear y mostrar el formulario de AgenteRegistrarTarjeta
            AgenteRegistrarTarjeta agenteForm = new AgenteRegistrarTarjeta();
            JPanel formPanel = agenteForm.initializeComponents();
            contentPanel.add(formPanel, BorderLayout.CENTER);
            contentPanel.revalidate();
            contentPanel.repaint();
            updateButtonColor(btnRegisterCard);

        });
        
        btncerrar.addActionListener(e -> {  
            int opcion = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro que desea cerrar sesión?",
                "Confirmar Cierre de Sesión",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (opcion == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(
                    null, 
                    "Cerrando sesión...", 
                    "Cierre de Sesión", 
                    JOptionPane.INFORMATION_MESSAGE
                );
                frame.dispose();
                LoginPanel loginFrame = new LoginPanel();
                loginFrame.setVisible(true);
            }
        });



        // Agregar los paneles al panel principal

        mainPanel.add(sidebar, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Establecer el panel principal en la ventana
        frame.add(mainPanel);

        // Hacer visible la ventana
        frame.setVisible(true);
    }

    private static void styleSidebarButton(JButton button, String imagePath) {
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 70, 70));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 40));
        button.setMinimumSize(new Dimension(200, 40));

        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        button.setIcon(icon);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (selectedButton != button) {
                    button.setBackground(new Color(90, 90, 90));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (selectedButton != button) {
                    button.setBackground(new Color(70, 70, 70));
                }
            }
        });

        button.addActionListener(e -> updateButtonColor(button));
    }

    private static void updateButtonColor(JButton selectedButton) {
        Component[] buttons = selectedButton.getParent().getComponents();
        for (Component comp : buttons) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.setBackground(new Color(70, 70, 70));
            }
        }

        selectedButton.setBackground(new Color(100, 100, 100));
        PanelMenu.selectedButton = selectedButton;
    }

}

