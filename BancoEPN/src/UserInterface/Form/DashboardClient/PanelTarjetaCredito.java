package UserInterface.Form.DashboardClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class PanelTarjetaCredito extends JPanel {
    public PanelTarjetaCredito() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#00A1E1"));
        setBorder(BorderFactory.createLineBorder(Color.decode("#D3D3D3"), 1, true));
        setPreferredSize(new Dimension(350, 250));

        JLabel titleLabel = new JLabel("Tarjeta de Crédito", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel cardLabel = new JLabel("**** **** **** 1234");
        centerPanel.add(cardLabel);

        add(titleLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }
}
