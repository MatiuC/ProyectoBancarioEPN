package UserInterface.Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import BussinesLogic.Entities.ATM.Retiro;

public class ATMPanel extends JFrame {

    public ATMPanel() {
        super("Panel de ATM");

        // Configuración de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
         setLayout(new GridBagLayout()); // Cambiado a GridBagLayout para mejor centrado
        setUndecorated(true);
        setResizable(false);
         setBackground(Color.WHITE);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel de imagen
        BackgroundPanel imagePanel = new BackgroundPanel("src\\UserInterface\\Resource\\Img\\ATMpanel.jpeg");
        imagePanel.setPreferredSize(new Dimension(420, 400));

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setPreferredSize(new Dimension(180, 400));
        buttonPanel.setBackground(Color.BLACK);

        // Campos para tarjeta y PIN
        JTextField txtTarjeta = new JTextField(16);
        txtTarjeta.setFont(new Font("Arial", Font.PLAIN, 14));
        JPasswordField txtPIN = new JPasswordField(3);
        txtPIN.setFont(new Font("Arial", Font.PLAIN, 14));

        // Panel para campos de entrada
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.setBackground(Color.BLACK);
        inputPanel.add(new JLabel("Número de tarjeta:"));
        inputPanel.add(txtTarjeta);
        inputPanel.add(new JLabel("PIN (3 dígitos):"));
        inputPanel.add(txtPIN);

        // Botón "Retirar"
        JButton btnRetirar = createStyledButton("Retirar");
        btnRetirar.setPreferredSize(new Dimension(140, 50));

        // Botón "Salir"
        JButton btnSalir = createStyledButton("Salir");
        btnSalir.setPreferredSize(new Dimension(140, 50));

        // Acción para abrir el panel de Retiro
        btnRetirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroTarjeta = txtTarjeta.getText();
                String pin = new String(txtPIN.getPassword());

                if (numeroTarjeta.isEmpty() || pin.isEmpty()) {
                    JOptionPane.showMessageDialog(ATMPanel.this,
                        "Por favor ingrese número de tarjeta y PIN",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (pin.length() != 3) {
                    JOptionPane.showMessageDialog(ATMPanel.this,
                        "El PIN debe tener exactamente 3 dígitos",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Retiro retiro = new Retiro();
                    boolean exito = retiro.realizarRetiro(numeroTarjeta, pin, 0); // Monto se pedirá en RetirarPanel
                    
                    if (exito) {
                        new RetirarPanel();
                    } else {
                        JOptionPane.showMessageDialog(ATMPanel.this,
                            "Error al procesar la tarjeta. Verifique los datos",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(ATMPanel.this,
                        "Error de conexión con la base de datos",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Accion para salir
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginPanel loginPanel = new LoginPanel();
                loginPanel.setVisible(true);
            }

        });


        // Centrar botón en el panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        buttonPanel.add(btnRetirar, gbc);

        // Configurar restricciones para el botón Salir
        gbc.gridy = 1;         // Mover a la siguiente fila
        gbc.weighty = 1.0;     // Dar peso vertical
        gbc.anchor = GridBagConstraints.SOUTH; // Anclar en la parte inferior
        buttonPanel.add(btnSalir, gbc);

        // Agregar paneles
        mainPanel.add(imagePanel, BorderLayout.WEST);
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        add(mainPanel);
        setVisible(true);
    }

    // Método para estilizar botones
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        return button;
    }

    // Panel personalizado para la imagen de fondo
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            ImageIcon icon = new ImageIcon(imagePath);
            backgroundImage = icon.getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

}
