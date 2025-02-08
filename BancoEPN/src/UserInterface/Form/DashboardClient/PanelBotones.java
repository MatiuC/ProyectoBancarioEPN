package UserInterface.Form.DashboardClient;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class PanelBotones extends JPanel{
    public PanelBotones() {
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        setOpaque(false);

        JButton btnConoceLoNuevo = new JButton("Conoce lo nuevo");
        JButton btnMostrarSaldos = new JButton("Mostrar saldos");

        // Iconos y diseño
        btnConoceLoNuevo.setIcon(new ImageIcon("path/to/your/newIcon.png"));
        btnMostrarSaldos.setIcon(new ImageIcon("path/to/your/eyeIcon.png"));

        add(btnConoceLoNuevo);
        add(btnMostrarSaldos);
    }
}
