package UserInterface.Form.DashboardClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelServicioFinanciero extends JPanel{
    public PanelServicioFinanciero() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#F5F5F5"));
        setBorder(BorderFactory.createLineBorder(Color.decode("#D3D3D3"), 1, true));
        setPreferredSize(new Dimension(350, 250));

        JLabel titleLabel = new JLabel("Servicios Financieros", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.decode("#2F4858"));

        JPanel servicesPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        servicesPanel.setOpaque(false);

        servicesPanel.add(createServicePanel("Ahorros", "Descripción Ahorros"));
        servicesPanel.add(createServicePanel("Inversiones", "Descripción Inversiones"));
        servicesPanel.add(createServicePanel("Seguros", "Descripción Seguros"));
        servicesPanel.add(createServicePanel("Préstamos", "Descripción Préstamos"));

        add(titleLabel, BorderLayout.NORTH);
        add(servicesPanel, BorderLayout.CENTER);
    }

    private JPanel createServicePanel(String title, String description) {
        JPanel servicePanel = new JPanel();
        servicePanel.setLayout(new BorderLayout());
        servicePanel.setBackground(Color.decode("#E0E0E0"));
        JLabel titleLabel = new JLabel(title);
        JLabel descriptionLabel = new JLabel(description);
        servicePanel.add(titleLabel, BorderLayout.NORTH);
        servicePanel.add(descriptionLabel, BorderLayout.CENTER);
        return servicePanel;
    }
}
