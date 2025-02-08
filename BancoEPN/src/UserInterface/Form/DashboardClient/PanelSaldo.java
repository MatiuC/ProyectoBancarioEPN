package UserInterface.Form.DashboardClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelSaldo extends JPanel {
    public PanelSaldo() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#FFCC00"));
        setBorder(BorderFactory.createLineBorder(Color.decode("#D3D3D3"), 1, true));
    
        // Definir tamaño preferido (ancho x alto)
        setPreferredSize(new Dimension(350, 250));
     // Ancho 300, Alto 200
    
        JLabel saldoLabel = new JLabel("Saldo: $5000");
        saldoLabel.setFont(new Font("Arial", Font.BOLD, 30));
        saldoLabel.setForeground(Color.decode("#2F4858"));
    
        add(saldoLabel, BorderLayout.CENTER);
    }
    
}
