package UserInterface.Form.DashBoardCliente;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import UserInterface.Form.ClienteTransacciones;
import UserInterface.CustomerControl.CustomTransactionTable;
import DataAccess.DAO.TransaccionDAO;
import DataAccess.DTO.TransaccionDTO;
import javax.swing.JOptionPane;


public class PanelActividadReciente extends JPanel{

    private JTable table;
    //Llamar a la tabla de transacciones filtrada por el usuario el id de la persona que esta logueado y mostrar las ultimas 5 transacciones
    public PanelActividadReciente(int id) {
        try {
            setLayout(new BorderLayout());
            setBackground(Color.decode("#FFFFFF"));
            setBorder(BorderFactory.createLineBorder(Color.decode("#D3D3D3"), 1, true));
            setPreferredSize(new Dimension(350, 250));

            // Datos de la tabla
            String[] columnas = {"ID", "Origen", "Destino", "Monto", "Tipo de Transacción", "Fecha y Hora"};
            DefaultTableModel model = new DefaultTableModel(columnas, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            // Crear la tabla con el modelo
            table = new JTable(model);
            
            // Configurar la tabla
            configureTable();

            // Crear un JScrollPane para contener la tabla
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane, BorderLayout.CENTER);

            // Agregar el botón Ver más
            addVerMasButton();

            // Cargar los datos
            cargarTransacciones(id);

        } catch (Exception e) {
            System.err.println("Error al inicializar panel: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void configureTable() {
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        // Configurar el encabezado
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.decode("#274156"));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Definir colores para la tabla usando la paleta proporcionada
        table.setBackground(Color.decode("#FFFFFF"));
        table.setGridColor(Color.decode("#3D3D3D"));
        table.setSelectionBackground(Color.decode("#B68E6D"));
        table.setSelectionForeground(Color.WHITE);

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
    }

    private void addVerMasButton() {
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

    private void cargarTransacciones(int id) {
        try {
            TransaccionDAO transaccionDAO = new TransaccionDAO();
            List<TransaccionDTO> transacciones = transaccionDAO.obtenerUltimasTransacciones(id);
            
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Limpiar tabla
            
            for (TransaccionDTO transaccion : transacciones) {
                model.addRow(new Object[]{
                    transaccion.getIdTransaccion(),
                    transaccion.getNombreOrigen(),
                    transaccion.getNombreDestino(),
                    "$ " + String.format("%.2f", transaccion.getMonto()),
                    transaccion.getTipoTransaccion(),
                    transaccion.getFecha()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las transacciones: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
