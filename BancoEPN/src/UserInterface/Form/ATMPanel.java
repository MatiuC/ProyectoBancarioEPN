package UserInterface.Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ATMPanel extends JFrame {

    public ATMPanel() {
        super("Panel de ATM");

        // Configuración de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);

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

        // Botón "Retirar"
        JButton btnRetirar = createStyledButton("Retirar");
        btnRetirar.setPreferredSize(new Dimension(140, 50));

        // Acción para abrir el panel de Retiro
        btnRetirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new RetirarPanel();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } // Abre la ventana de retiro
            }
        });

        // Centrar botón en el panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        buttonPanel.add(btnRetirar, gbc);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATMPanel());
    }
}
