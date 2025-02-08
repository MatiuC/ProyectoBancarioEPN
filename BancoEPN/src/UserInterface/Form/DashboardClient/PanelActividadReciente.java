package UserInterface.Form.DashboardClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import UserInterface.CustomerControl.TransparentButton;
import UserInterface.Form.ClienteTransacciones;

public class PanelActividadReciente extends JPanel{
    public PanelActividadReciente() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#FFFFFF"));
        setBorder(BorderFactory.createLineBorder(Color.decode("#D3D3D3"), 1, true));

        setPreferredSize(new Dimension(350, 250));

        // Aquí puedes agregar la tabla de movimientos financieros
        String[] columnas = {"Origen", "Destino", "Monto", "Tipo", "Fecha"};
        Object[][] datos = {
            {"Cuenta-1001", "Destino-2001", "$100", "Depósito", "08/02/2025"},
            {"Cuenta-1002", "Destino-2002", "$200", "Retiro", "08/02/2025"},
            {"Cuenta-1003", "Destino-2003", "$300", "Transferencia", "08/02/2025"}
        };

        JTable table = new JTable(datos, columnas);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    

        // Crear el botón "Ver más"
        JButton btnVerMas = TransparentButton.createButton("Ver Más");
        btnVerMas.setFont(new Font("Arial", Font.PLAIN, 14));
        btnVerMas.setForeground(Color.decode("#2F4858"));
        btnVerMas.setContentAreaFilled(false);
        btnVerMas.setFocusPainted(false);
        
        // Acción para abrir la ventana de transacciones completas
        btnVerMas.addActionListener(e -> {
            JFrame frame = new JFrame("Cliente Transacciones");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().setBackground(Color.WHITE);

            // Crear y agregar el panel de transacciones
            ClienteTransacciones panel = new ClienteTransacciones();
            frame.add(panel);

            // Configurar la ventana
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        // Agregar el botón "Ver más"
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnVerMas);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
}
