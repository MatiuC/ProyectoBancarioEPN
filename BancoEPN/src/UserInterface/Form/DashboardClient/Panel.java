package UserInterface.Form.DashboardClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;


public class Panel extends JPanel {
    public Panel() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#FFFFFF")); // Fondo blanco

        // ============================
        // Encabezado Superior
        // ============================
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.decode("#FFFFFF"));
        headerPanel.setPreferredSize(new Dimension(0, 60));

        // Parte Izquierda: Ícono de usuario y bienvenida
        headerPanel.add(new PanelBienvenida(), BorderLayout.WEST);

        // Parte Derecha: Botones "Conoce lo nuevo" y "Mostrar saldos"
        headerPanel.add(new PanelBotones(), BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        // ============================
        // Panel Principal con secciones
        // ============================
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.decode("#FFFFFF"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.BOTH;

        // Tarjeta de Saldo Principal (Izquierda)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Solo ocupa 1 columna
        mainPanel.add(new PanelSaldo(), gbc);
        
        // Actividad Reciente (Centro)
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Solo ocupa 1 columna en el centro
        mainPanel.add(new PanelActividadReciente(), gbc);
        
        // Tarjeta de Crédito (Derecha)
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Solo ocupa 1 columna a la derecha
        mainPanel.add(new PanelTarjetaCredito(), gbc);
        
        
        // Si quieres otro panel de servicios debajo (opcional)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3; // También ocupa toda la fila
        mainPanel.add(new PanelServicioFinanciero(), gbc);

        add(mainPanel, BorderLayout.CENTER);
    }
    }

