package UserInterface.Form.DashBoardCliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class PanelServicioFinanciero extends JPanel{
    public PanelServicioFinanciero() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#FFFFFF"));
        setBorder(BorderFactory.createLineBorder(Color.decode("#FFFFFF"), 1, true));
        setPreferredSize(new Dimension(350, 250));

        JLabel titleLabel = new JLabel("Servicios Financieros", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.decode("#2F4858"));

        JPanel servicesPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        servicesPanel.setOpaque(false);

        // Añadir iconos a los paneles de servicio
        servicesPanel.add(createServicePanel("Ahorros", "El arte de guardar dinero hoy para que tu yo del futuro no te odie. Funciona mejor si no lo sacas a la primera tentación de comprar cosas innecesarias.", "ProyectoBancarioEPN/BancoEPN/src/UserInterface/Resource/Icon/IconAhorro.png"));
        servicesPanel.add(createServicePanel("Inversiones", "Pon tu dinero a trabajar en lugar de dejarlo de vacaciones en tu cuenta. Riesgo incluido, pero también la posibilidad de que en el futuro te sientas un genio financiero.", "ProyectoBancarioEPN/BancoEPN/src/UserInterface/Resource/Icon/IconInversion.png"));
        servicesPanel.add(createServicePanel("Seguros", "Pagas un poco hoy para no pagar un montón después. Básicamente, es como un paraguas financiero: esperas no necesitarlo, pero cuando llueve, agradeces tenerlo.", "ProyectoBancarioEPN/BancoEPN/src/UserInterface/Resource/Icon/IconSeguro.png"));
        servicesPanel.add(createServicePanel("Préstamos", "Dinero que no es tuyo, pero que amablemente te prestamos para que cumplas tus sueños… y luego nos lo devuelvas con un poquito de cariño (también conocido como intereses).", "ProyectoBancarioEPN/BancoEPN/src/UserInterface/Resource/Icon/IconPrestamo.png"));

        add(titleLabel, BorderLayout.NORTH);
        add(servicesPanel, BorderLayout.CENTER);
    }

    private JPanel createServicePanel(String title, String description, String iconPath) {
        JPanel servicePanel = new JPanel();
        servicePanel.setLayout(new BoxLayout(servicePanel, BoxLayout.Y_AXIS));  // Usar BoxLayout para apilar los elementos
        servicePanel.setBackground(Color.decode("#274156"));
        servicePanel.setPreferredSize(new Dimension(350, 180));  // Aseguramos que el panel tenga suficiente espacio

        // Crear un panel para el icono y el título (alineado a la izquierda)
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setOpaque(false);

        // Cargar el icono
        ImageIcon icon = new ImageIcon(iconPath);
        Image iconImg = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); // Redimensionar el icono
        JLabel iconLabel = new JLabel(new ImageIcon(iconImg));

        // Crear el título
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(Color.WHITE);

        // Añadir el icono y el título al panel
        titlePanel.add(iconLabel);
        titlePanel.add(titleLabel);

        // Crear el panel para la descripción (alineado a la derecha)
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
        descriptionPanel.setOpaque(false);

        // Crear la descripción
        JLabel descriptionLabel = new JLabel("<html>" + description + "</html>");  // Esto permite que el texto sea multilínea
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        descriptionLabel.setForeground(Color.WHITE);

        // Añadir la descripción al panel, ajustando el espaciado para que no se oculte
        descriptionPanel.add(descriptionLabel);

        // Añadir los paneles al panel principal
        servicePanel.add(titlePanel);
        servicePanel.add(Box.createVerticalStrut(-1000)); // Espacio entre el icono/título y la descripción
        servicePanel.add(descriptionPanel);

        // Añadir un ActionListener para abrir un mensaje emergente
        servicePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JOptionPane.showMessageDialog(servicePanel, "Esta funcionalidad está en desarrollo");
            }
        });

        return servicePanel;
    }

}
