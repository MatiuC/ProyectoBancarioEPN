package UserInterface.Form.DashBoardCliente;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import UserInterface.Form.ClienteTransacciones;

public class PanelActividadReciente extends JPanel{

    //Llamar a la tabla de transacciones filtrada por el usuario el id de la persona que esta logueado y mostrar las ultimas 5 transacciones
    public PanelActividadReciente() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#FFFFFF"));
        setBorder(BorderFactory.createLineBorder(Color.decode("#D3D3D3"), 1, true));
        setPreferredSize(new Dimension(350, 250));

        // Datos de la tabla
        String[] columnas = {"Origen", "Destino", "Monto", "Tipo", "Fecha"};
        Object[][] datos = {
            {"Cuenta-1001", "Destino-2001", "$100", "Depósito", "08/02/2025"},
            {"Cuenta-1002", "Destino-2002", "$200", "Retiro", "08/02/2025"},
            {"Cuenta-1003", "Destino-2003", "$300", "Transferencia", "08/02/2025"}
        };

        // Crear la tabla con los datos y las columnas
        JTable table = new JTable(datos, columnas);
        table.setFont(new Font("Arial", Font.PLAIN, 14));

        // Desactivar la edición y el reordenamiento de celdas
        table.setEnabled(false);  // Desactivar la edición de celdas
        table.getTableHeader().setReorderingAllowed(false);  // Evitar que las columnas se reordenen
        table.getTableHeader().setResizingAllowed(false);    // Evitar que se cambien los tamaños de las celdas

        // Definir colores para la tabla usando la paleta proporcionada
        table.setBackground(Color.decode("#FFFFFF"));
        table.setGridColor(Color.decode("#3D3D3D"));
        table.setSelectionBackground(Color.decode("#B68E6D"));
        table.setSelectionForeground(Color.WHITE);

        // Personalizar el encabezado de la tabla
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.decode("#274156"));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 14));

        // Cambiar color de las celdas (no seleccionadas)
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (isSelected) {
                    c.setBackground(Color.decode("#B68E6D"));  // Fila seleccionada
                    c.setForeground(Color.WHITE);
                } else {
                    if (row % 2 == 0) {
                        c.setBackground(Color.decode("#FFFFFF")); // Filas pares
                    } else {
                        c.setBackground(Color.decode("#F1F1F1")); // Filas impares
                    }
                    c.setForeground(Color.decode("#3D3D3D"));
                }
                return c;
            }
        });

        // Crear un JScrollPane para contener la tabla
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Crear el botón "Ver más" usando la clase Button
        JButton btnVerMas = new JButton("Ver Más");
        btnVerMas.setBackground(new Color(211, 211, 211));
        btnVerMas.setForeground(new Color(47, 72, 88));
        
        // Botón completamente transparente, sin borde y sin cambio de estilo al pasar el cursor
        btnVerMas.setBackground(new Color(0, 0, 0, 0));  // Fondo completamente transparente
        btnVerMas.setContentAreaFilled(false);  // No rellenar el área de contenido
        btnVerMas.setFocusPainted(false);  // No mostrar el borde al hacer clic
        btnVerMas.setRolloverEnabled(false);  // Deshabilitar el efecto de "hover"
        btnVerMas.setBorderPainted(false);  // Deshabilitar el borde visible
        
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
