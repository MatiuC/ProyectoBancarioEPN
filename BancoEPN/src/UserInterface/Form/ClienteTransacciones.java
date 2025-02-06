package UserInterface.Form;

import UserInterface.CustomerControl.CustomTransactionTable;
import java.awt.*;
import javax.swing.*;

public class ClienteTransacciones extends JPanel {
    private CustomTransactionTable tablaTransacciones;
    
    public ClienteTransacciones() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Crear la tabla personalizada
        tablaTransacciones = new CustomTransactionTable();
        tablaTransacciones.setPreferredSize(new Dimension(800, 400));
        
        // Panel contenedor con padding
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBackground(Color.WHITE);
        contenedor.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contenedor.add(tablaTransacciones, BorderLayout.CENTER);
        
        // Agregar el contenedor al panel principal
        add(contenedor, BorderLayout.CENTER);
        
        // Asegurar que el panel tenga un tamaño mínimo
        setPreferredSize(new Dimension(900, 500));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Establecer el Look and Feel del sistema
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            JFrame frame = new JFrame("Cliente Transacciones");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setBackground(Color.WHITE);
            
            // Crear y agregar el panel principal
            ClienteTransacciones panel = new ClienteTransacciones();
            frame.add(panel);
            
            // Configurar la ventana
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
