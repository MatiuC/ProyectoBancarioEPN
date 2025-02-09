package UserInterface.Form;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JFrame {

    public LoginPanel() {
        // Configuración de la ventana
        setTitle("Iniciar sesión");
        setSize(600, 600); // Tamaño de ventana aumentado
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
        ImageIcon logoIcon = new ImageIcon("BancoEPN\\src\\UserInterface\\Resource\\Img\\LOGO.jpg");
        Image logoImage = logoIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Ajusta el tamaño aquí (ancho, alto)
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoPanel.add(logoLabel);

        mainPanel.add(logoPanel, BorderLayout.NORTH);

        // Panel de formulario de inicio de sesión
        JPanel loginFormPanel = new JPanel();
        loginFormPanel.setLayout(new BoxLayout(loginFormPanel, BoxLayout.Y_AXIS));
        loginFormPanel.setBackground(new Color(39, 56, 75));  // Fondo charcoal para el formulario
        loginFormPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título de inicio de sesión
        JLabel titleLabel = new JLabel("Hola, inicia sesión");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setForeground(Color.WHITE); // Título blanco
        loginFormPanel.add(titleLabel);

        // Espaciado
        loginFormPanel.add(Box.createVerticalStrut(20));

        // Etiqueta de usuario
        JLabel userLabel = new JLabel("Ingresa usuario:");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        userLabel.setForeground(Color.WHITE); // Texto blanco
        userLabel.setHorizontalAlignment(SwingConstants.LEFT); // Centrado
        
        loginFormPanel.add(userLabel);
        

        // Campo de usuario
        JTextField usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField.setBackground(Color.WHITE); // Fondo blanco para el campo de texto

        // Cambiar el borde utilizando un borde redondeado
        usernameField.setBorder(new RoundedBorder(15));
        usernameField.setOpaque(true);

        loginFormPanel.add(usernameField);

        // Espaciado
        loginFormPanel.add(Box.createVerticalStrut(10));

        // Etiqueta de contraseña
        JLabel passwordLabel = new JLabel("Ingresa contraseña:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setForeground(Color.WHITE); // Texto blanco
        loginFormPanel.add(passwordLabel);

        // Campo de contraseña
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBackground(Color.WHITE); // Fondo blanco para el campo de contraseña
        passwordField.setText("Ingresa tu Contraseña");

        // Cambiar el borde utilizando un borde redondeado
        passwordField.setBorder(new RoundedBorder(15));
        passwordField.setOpaque(true);

        loginFormPanel.add(passwordField);

        // Recordarme checkbox
        JCheckBox rememberMeCheckBox = new JCheckBox("Recordarme");
        rememberMeCheckBox.setFont(new Font("Arial", Font.PLAIN, 14));
        rememberMeCheckBox.setBackground(new Color(39, 56, 75)); // Fondo gris para checkbox
        rememberMeCheckBox.setForeground(Color.WHITE); // Texto blanco
        loginFormPanel.add(rememberMeCheckBox);

        // Espaciado
        loginFormPanel.add(Box.createVerticalStrut(20));

        // Enlace de olvidé mi usuario o contraseña
        JLabel forgotLabel = new JLabel("<html><a href='#'>¿Olvidaste tu usuario o contraseña?</a></html>");
        forgotLabel.setForeground(new Color(38, 72, 235));
        forgotLabel.setAlignmentX(CENTER_ALIGNMENT);
        loginFormPanel.add(forgotLabel);

        // Generar código QR (simulación)
        JPanel qrPanel = new JPanel();
        qrPanel.setLayout(new BoxLayout(qrPanel, BoxLayout.Y_AXIS));
        qrPanel.setBackground(new Color(39, 56, 75));  // Fondo para el panel QR

        // Imagen del gato (lado derecho)
        JLabel catLabel = new JLabel();
        ImageIcon catIcon = new ImageIcon("BancoEPN\\src\\UserInterface\\Resource\\Img\\BancoQ.jpeg"); // Asegúrate de colocar la ruta correcta
        // Redimensionar la imagen del gato
        Image catImage = catIcon.getImage().getScaledInstance(300, 400, Image.SCALE_SMOOTH); // Ajusta el tamaño aquí (ancho, alto)
        catLabel.setIcon(new ImageIcon(catImage)); // Asignamos la imagen redimensionada
        
        qrPanel.add(catLabel); // Añadir al panel
        

        // Botón de iniciar sesión
        JButton generateQrButton = new JButton("Iniciar Sesion");
        generateQrButton.setBackground(Color.WHITE);  // Botón blanco
        generateQrButton.setForeground(new Color(39, 56, 75)); // Texto oscuro en el botón blanco
        generateQrButton.setFont(new Font("Arial", Font.BOLD, 14));
        generateQrButton.setAlignmentX(CENTER_ALIGNMENT);
        generateQrButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        generateQrButton.setFocusPainted(false);

        // Añadir todo el formulario al panel principal
        mainPanel.add(loginFormPanel, BorderLayout.WEST);
        mainPanel.add(qrPanel, BorderLayout.EAST);
        mainPanel.add(generateQrButton, BorderLayout.SOUTH);

        // Agregar panel principal al JFrame
        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginPanel().setVisible(true);
            }
        });
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
            g.setColor(Color.GRAY);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
