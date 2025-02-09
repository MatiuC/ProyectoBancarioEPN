package UserInterface.Form;

import BussinesLogic.Entities.BancoLogic.ValidarIngreso;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.border.*;

public class LoginPanel extends JFrame {

    private static final Color CHARCOAL = new Color(47, 72, 88); // #2F4858
    private static final Color LION = new Color(182, 142, 109); // #B68E6D
    private static final Color TIMBERWOLF = new Color(211, 211, 211); // #D3D3D3
    private static final Color DARK_CHARCOAL = new Color(39, 65, 86); // #274156

    public LoginPanel() {
        // Configuración de la ventana
        setTitle("Iniciar sesión");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setUndecorated(true);
        setResizable(false);
        setBackground(Color.WHITE);

        // Panel principal con fondo blanco
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Título "Hola, inicia sesión"
        JLabel titleLabel = new JLabel("Hola, inicia sesión");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(CHARCOAL);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(50));

        // Campo de usuario
        JLabel userLabel = new JLabel("Ingresa tu usuario");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        userLabel.setForeground(CHARCOAL);
        userLabel.setAlignmentX(LEFT_ALIGNMENT);
        mainPanel.add(userLabel);
        mainPanel.add(Box.createVerticalStrut(10));

        JTextField userField = createStyledTextField("Usuario");
        mainPanel.add(userField);
        mainPanel.add(Box.createVerticalStrut(30));

        // Campo de contraseña
        JLabel passwordLabel = new JLabel("Ingresa tu contraseña");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordLabel.setForeground(CHARCOAL);
        passwordLabel.setAlignmentX(LEFT_ALIGNMENT);
        mainPanel.add(passwordLabel);
        mainPanel.add(Box.createVerticalStrut(10));

        JPasswordField passwordField = createStyledPasswordField();
        mainPanel.add(passwordField);
        mainPanel.add(Box.createVerticalStrut(40));

        // Botón Continuar
        JButton loginButton = new JButton("Continuar");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(CHARCOAL);
        loginButton.setPreferredSize(new Dimension(320, 45));
        loginButton.setMaximumSize(new Dimension(320, 45));
        loginButton.setBorder(new RoundedBorder(8));
        loginButton.setFocusPainted(false);
        loginButton.setAlignmentX(CENTER_ALIGNMENT);
        
        loginButton.addActionListener(e -> {
            ValidarIngreso validarIngreso = new ValidarIngreso();
            validarIngreso.ValidarIngreso();
        });
        
        mainPanel.add(loginButton);
        mainPanel.add(Box.createVerticalStrut(30));

        // Link "¿Olvidaste tu usuario o contraseña?"
        JLabel forgotLabel = new JLabel("¿Olvidaste tu usuario o contraseña?");
        forgotLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        forgotLabel.setForeground(LION);
        forgotLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotLabel.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(forgotLabel);

        // Agregar panel principal al frame
        add(mainPanel);

        // Agregar borde al frame
        getRootPane().setBorder(BorderFactory.createLineBorder(TIMBERWOLF, 1));
    }

    private JTextField createStyledTextField(String placeholder) {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(320, 45));
        field.setMaximumSize(new Dimension(320, 45));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(8),
            BorderFactory.createEmptyBorder(0, 15, 0, 15)
        ));
        field.setBackground(Color.WHITE);
        field.setForeground(CHARCOAL);

        // Placeholder
        field.setText(placeholder);
        field.setForeground(TIMBERWOLF);
        
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(CHARCOAL);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(TIMBERWOLF);
                }
            }
        });

        return field;
    }

    private JPasswordField createStyledPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setPreferredSize(new Dimension(320, 45));
        field.setMaximumSize(new Dimension(320, 45));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(8),
            BorderFactory.createEmptyBorder(0, 15, 0, 15)
        ));
        field.setBackground(Color.WHITE);
        field.setForeground(CHARCOAL);
        return field;
    }

    // Clase para crear bordes redondeados
    private static class RoundedBorder implements Border {
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
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(TIMBERWOLF);
            g2d.drawRoundRect(x, y, width - 1, height - 1, radius * 2, radius * 2);
            g2d.dispose();
        }
    }
}