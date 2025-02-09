package UserInterface.Form;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BussinesLogic.Entities.BancoLogic.ValidarIngreso;


public class LoginPanel extends JFrame {

    public LoginPanel() {
        // Configuración de la ventana
        setTitle("Iniciar sesión");
        setSize(500, 500); // Tamaño más compacto
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setUndecorated(true); // Eliminar la barra de título y botones
        setResizable(false); // Deshabilitar la opción de maximizar/minimizar

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(39, 56, 75));  // Color de fondo charcoal

        // Contenedor de logo y texto
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        logoPanel.setBackground(new Color(39, 56, 75));  // Fondo charcoal para el logo

        // Redimensionar el logo
        ImageIcon logoIcon = new ImageIcon("src\\UserInterface\\Resource\\Img\\LOGO.jpg");
        Image logoImage = logoIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Ajusta el tamaño aquí (ancho, alto)
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoPanel.add(logoLabel);

        mainPanel.add(logoPanel, BorderLayout.NORTH);

        // Panel de formulario de inicio de sesión
        JPanel loginFormPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        loginFormPanel.setBackground(new Color(39, 56, 75));
        loginFormPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel interno para organizar elementos verticalmente y centrados
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBackground(new Color(39, 56, 75));
        innerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Título de inicio de sesión
        JLabel titleLabel = new JLabel("Hola, inicia sesión");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setForeground(Color.WHITE);
        innerPanel.add(titleLabel);
        innerPanel.add(Box.createVerticalStrut(20));

        // Panel para usuario
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPanel.setBackground(new Color(39, 56, 75));
        userPanel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel userLabel = new JLabel("Ingresa usuario:");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        userLabel.setForeground(Color.WHITE);
        userLabel.setAlignmentX(CENTER_ALIGNMENT);
        userPanel.add(userLabel);
        userPanel.add(Box.createVerticalStrut(5));

        JTextField usernameField = new JTextField(15);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBackground(Color.WHITE);
        usernameField.setPreferredSize(new Dimension(200, 30));
        usernameField.setMaximumSize(new Dimension(200, 30));
        usernameField.setAlignmentX(CENTER_ALIGNMENT);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        usernameField.setOpaque(true);
        userPanel.add(usernameField);

        innerPanel.add(userPanel);

        innerPanel.add(Box.createVerticalStrut(10));

        // Panel para contraseña
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));
        passwordPanel.setBackground(new Color(39, 56, 75));
        passwordPanel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel passwordLabel = new JLabel("Ingresa contraseña:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setAlignmentX(CENTER_ALIGNMENT);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(Box.createVerticalStrut(5));

        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBackground(Color.WHITE);
        passwordField.setPreferredSize(new Dimension(200, 30));
        passwordField.setMaximumSize(new Dimension(200, 30));
        passwordField.setAlignmentX(CENTER_ALIGNMENT);
        passwordField.setText("Ingresa tu Contraseña");
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        passwordField.setOpaque(true);
        passwordPanel.add(passwordField);

        innerPanel.add(passwordPanel);

        innerPanel.add(Box.createVerticalStrut(10));

        // Panel para checkbox y enlace
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBackground(new Color(39, 56, 75));
        optionsPanel.setAlignmentX(CENTER_ALIGNMENT);

        JCheckBox rememberMeCheckBox = new JCheckBox("Recordarme");
        rememberMeCheckBox.setFont(new Font("Arial", Font.PLAIN, 14));
        rememberMeCheckBox.setBackground(new Color(39, 56, 75));
        rememberMeCheckBox.setForeground(Color.WHITE);
        rememberMeCheckBox.setAlignmentX(CENTER_ALIGNMENT);
        optionsPanel.add(rememberMeCheckBox);
        optionsPanel.add(Box.createVerticalStrut(10));

        JLabel forgotLabel = new JLabel("<html><a href='#'>¿Olvidaste tu usuario o contraseña?</a></html>");
        forgotLabel.setForeground(new Color(38, 72, 235));
        forgotLabel.setAlignmentX(CENTER_ALIGNMENT);
        optionsPanel.add(forgotLabel);

        innerPanel.add(optionsPanel);
        loginFormPanel.add(innerPanel);

        // Generar código QR (simulación)
        JPanel qrPanel = new JPanel();
        qrPanel.setLayout(new BoxLayout(qrPanel, BoxLayout.Y_AXIS));
        qrPanel.setBackground(new Color(39, 56, 75));  // Fondo para el panel QR

        // Imagen del gato (lado derecho)
        JLabel catLabel = new JLabel();
        ImageIcon catIcon = new ImageIcon("src\\UserInterface\\Resource\\Img\\EPN.jpeg"); // Asegúrate de colocar la ruta correcta
        // Redimensionar la imagen del gato
        Image catImage = catIcon.getImage().getScaledInstance(300, 400, Image.SCALE_SMOOTH); // Ajusta el tamaño aquí (ancho, alto)
        catLabel.setIcon(new ImageIcon(catImage)); // Asignamos la imagen redimensionada
        
        qrPanel.add(catLabel); // Añadir al panel
        

        // Panel para el botón (para centrarlo)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(39, 56, 75));

        // Botón de iniciar sesión
        JButton generateQrButton = new JButton("Iniciar Sesion");
        generateQrButton.setBackground(new Color(38, 72, 235));  // Azul
        generateQrButton.setForeground(Color.WHITE); // Texto blanco
        generateQrButton.setFont(new Font("Arial", Font.BOLD, 14));
        generateQrButton.setPreferredSize(new Dimension(150, 35));
        generateQrButton.setBorder(new RoundedBorder(15));
        generateQrButton.setFocusPainted(false);
        buttonPanel.add(generateQrButton);

        //Implementacion de la logica Entities.ValidarIngreso con boton de iniciar sesion
        generateQrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ValidarIngreso validarIngreso = new ValidarIngreso();
                validarIngreso.ValidarIngreso();

            }
        });




        // Añadir todo el formulario al panel principal

        mainPanel.add(loginFormPanel, BorderLayout.CENTER); // Centrar el formulario
        mainPanel.add(qrPanel, BorderLayout.EAST);
        
        // Actualizar el botón con borde simple
        generateQrButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Agregar panel principal al JFrame
        add(mainPanel);
    }


    // Clase para crear bordes redondeados
    public static class RoundedBorder implements Border {
        private int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(new Color(200, 200, 200)); // Gris más claro para mejor visibilidad
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}