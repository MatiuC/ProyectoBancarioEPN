package UserInterface.Form;

import BussinesLogic.Entities.BancoLogic.ValidarIngreso;
import UserInterface.Form.DashBoardCliente.DashBoardCliente;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.border.*;



public class LoginPanel extends JFrame {
 // Los colores se mantienen igual que en tu código anterior
 private static final Color CHARCOAL = new Color(47, 72, 88); // #2F4858
 private static final Color LION = new Color(182, 142, 109); // #B68E6D
 private static final Color TIMBERWOLF = new Color(211, 211, 211); // #D3D3D3
 private static final Color DARK_CHARCOAL = new Color(39, 65, 86); // #274156

 public LoginPanel() {
     // Configuración de la ventana
     setTitle("Iniciar sesión");
     setSize(400, 600);
     setLocationRelativeTo(null);
     setLayout(new GridBagLayout()); // Cambiado a GridBagLayout para mejor centrado
     setUndecorated(true);
     setResizable(false);
     setBackground(Color.RED);

     // Panel principal con fondo blanco
     JPanel mainPanel = new JPanel();
     mainPanel.setLayout(new GridBagLayout());
     mainPanel.setBackground(Color.RED);
     
     // Panel interno para los componentes
     JPanel contentPanel = new JPanel();
     contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
     contentPanel.setBackground(Color.RED);
     contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

     // Título "Hola, inicia sesión"
     JLabel titleLabel = new JLabel("Hola, inicia sesión");
     titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
     titleLabel.setForeground(CHARCOAL);
     titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
     contentPanel.add(titleLabel);
     contentPanel.add(Box.createVerticalStrut(50));

     // Panel para campos de entrada
     JPanel fieldsPanel = new JPanel();
     fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
     fieldsPanel.setBackground(Color.WHITE);
     fieldsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

     // Campo de usuario
     JLabel userLabel = new JLabel("Ingresa tu usuario");
     userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
     userLabel.setForeground(CHARCOAL);
     userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
     fieldsPanel.add(userLabel);
     fieldsPanel.add(Box.createVerticalStrut(10));

     JTextField userField = createStyledTextField("Usuario");
     userField.setAlignmentX(Component.CENTER_ALIGNMENT);
     fieldsPanel.add(userField);
     fieldsPanel.add(Box.createVerticalStrut(30));

     // Campo de contraseña
     JLabel passwordLabel = new JLabel("Ingresa tu contraseña");
     passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
     passwordLabel.setForeground(CHARCOAL);
     passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
     fieldsPanel.add(passwordLabel);
     fieldsPanel.add(Box.createVerticalStrut(10));

     JPasswordField passwordField = createStyledPasswordField("Contraseña");
     passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
     fieldsPanel.add(passwordField);
     fieldsPanel.add(Box.createVerticalStrut(40));

     contentPanel.add(fieldsPanel);

     // Botón Continuar
     JButton loginButton = new JButton("Continuar");
     loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
     loginButton.setForeground(Color.WHITE);
     loginButton.setBackground(CHARCOAL);
     loginButton.setPreferredSize(new Dimension(320, 55));
     loginButton.setMaximumSize(new Dimension(320, 55));
     loginButton.setBorder(BorderFactory.createLineBorder(CHARCOAL, 3));
     loginButton.setOpaque(true);
     loginButton.setFocusPainted(false);
     loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
     
     loginButton.addActionListener(e -> {
         ValidarIngreso validarIngreso = new ValidarIngreso();
         if (validarIngreso.validarCredenciales(userField.getText(), passwordField.getPassword())) {
             JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
             int rol = validarIngreso.obtenerRol(userField.getText());
             validarIngreso.mostrarVentanas(rol);
             if (rol == 1) {

             }
             if (rol == 2) {
                 this.setVisible(false);
                 DashBoardCliente dashBoardCliente = new DashBoardCliente(validarIngreso.obtenerId(userField.getText()));
                 dashBoardCliente.setVisible(true);  
             }

             if (rol == 3) {
                 this.setVisible(false);
                 new PanelMenu();
             }
         } else {
             JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
         }
     });
     
     contentPanel.add(loginButton);
     contentPanel.add(Box.createVerticalStrut(30));

     // Link "¿Olvidaste tu usuario o contraseña?"
     JLabel forgotLabel = new JLabel("¿Olvidaste tu usuario o contraseña?");
     forgotLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
     forgotLabel.setForeground(LION);
     forgotLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
     forgotLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
     contentPanel.add(forgotLabel);

     // Añadir espacio vertical
     contentPanel.add(Box.createVerticalStrut(20)); // Esto crea un espacio de 10 píxeles

     // Botón para abrir el panel ATM
     JButton openATMButton = new JButton("Abrir ATM ");
     openATMButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
     openATMButton.setForeground(Color.WHITE);
     openATMButton.setBackground(CHARCOAL);
     openATMButton.setPreferredSize(new Dimension(320, 55));
     openATMButton.setMaximumSize(new Dimension(320, 55));
     openATMButton.setBorder(BorderFactory.createLineBorder(CHARCOAL, 3));
     openATMButton.setOpaque(true);
     openATMButton.setFocusPainted(false);
     openATMButton.setAlignmentX(Component.CENTER_ALIGNMENT);

     openATMButton.addActionListener(e -> {
         // Abrir el panel ATM
         dispose();
         ATMPanel atmPanel = new ATMPanel();  // METODO PARA VINCULAR EL ATMPANEL
         atmPanel.setVisible(true);
     });

     // Agregar el botón debajo del enlace
     contentPanel.add(openATMButton);

     // Agregar contentPanel al mainPanel usando GridBagConstraints para centrarlo
     GridBagConstraints gbc = new GridBagConstraints();
     gbc.gridx = 0;
     gbc.gridy = 0;
     gbc.weightx = 1;
     gbc.weighty = 1;
     gbc.anchor = GridBagConstraints.CENTER;
     mainPanel.add(contentPanel, gbc);

     // Agregar mainPanel al frame
     add(mainPanel);

     // Agregar borde al frame
     getRootPane().setBorder(BorderFactory.createLineBorder(TIMBERWOLF, 1));
 }

 // Método para crear el campo de texto personalizado
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

 // Método para crear el campo de contraseña personalizado
 private JPasswordField createStyledPasswordField(String placeholder) {
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
     
     // Placeholder
     field.setEchoChar((char)0); // Mostrar texto normal para el placeholder
     field.setText(placeholder);
     field.setForeground(TIMBERWOLF);
     
     field.addFocusListener(new FocusListener() {
         @Override
         public void focusGained(FocusEvent e) {
             if (String.valueOf(field.getPassword()).equals(placeholder)) {
                 field.setText("");
                 field.setEchoChar('•'); // Cambiar a puntos cuando se empiece a escribir
                 field.setForeground(CHARCOAL);
             }
         }

         @Override
         public void focusLost(FocusEvent e) {
             if (field.getPassword().length == 0) {
                 field.setEchoChar((char)0); // Volver a mostrar texto normal
                 field.setText(placeholder);
                 field.setForeground(TIMBERWOLF);
             }
         }
     });
     
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
