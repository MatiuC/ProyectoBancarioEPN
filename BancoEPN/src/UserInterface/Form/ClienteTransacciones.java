package UserInterface.Form;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.*;

public class ClienteTransacciones extends JPanel {
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnAnterior;
    private JButton btnSiguiente;
    private int paginaActual = 0;
    private final int REGISTROS_POR_PAGINA = 2;
    
    public ClienteTransacciones() {
        setLayout(new BorderLayout());
        
        // Crear el modelo de tabla
        String[] columnas = {"Origen", "Destino", "Monto", "Tipo de Transacción", "Fecha de Transacción"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };
        
        // Crear la tabla
        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(30);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Personalizar el aspecto de la tabla
        tabla.setShowGrid(true);
        tabla.setGridColor(new Color(230, 230, 230));
        tabla.setBackground(Color.WHITE);
        tabla.setFont(new Font("Arial", Font.PLAIN, 14));
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tabla.getTableHeader().setBackground(new Color(240, 240, 240));
        
        // Crear el panel de la tabla con bordes redondeados
        JPanel panelTabla = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(getBackground());
                g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
            }
        };
        panelTabla.setLayout(new BorderLayout());
        panelTabla.add(new JScrollPane(tabla));
        panelTabla.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnAnterior = new JButton("Anterior");
        btnSiguiente = new JButton("Siguiente");
        
        btnAnterior.addActionListener(e -> paginaAnterior());
        btnSiguiente.addActionListener(e -> paginaSiguiente());
        
        panelBotones.add(btnAnterior);
        panelBotones.add(btnSiguiente);
        
        // Agregar componentes al panel principal
        add(panelTabla, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
        
        // Agregar datos de prueba
        cargarDatosPrueba();
        actualizarBotones();
    }
    
    private void cargarDatosPrueba() {
        // Limpiar tabla existente
        modeloTabla.setRowCount(0);
        
        // Datos de ejemplo
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String[][] datos = {
            {"1234-5678", "8765-4321", "$100.00", "Transferencia", sdf.format(new Date())},
            {"8765-4321", "1234-5678", "$50.00", "Transferencia", sdf.format(new Date())},
            {"1234-5678", "ATM-001", "$200.00", "Retiro", sdf.format(new Date())},
            {"DEP-001", "8765-4321", "$300.00", "Depósito", sdf.format(new Date())}
        };
        
        // Agregar datos a la tabla
        for (String[] fila : datos) {
            modeloTabla.addRow(fila);
        }
    }
    
    private void paginaAnterior() {
        if (paginaActual > 0) {
            paginaActual--;
            cargarDatosPrueba(); // En una implementación real, cargaría los datos correspondientes
            actualizarBotones();
        }
    }
    
    private void paginaSiguiente() {
        // En una implementación real, verificaríamos si hay más datos
        paginaActual++;
        cargarDatosPrueba(); // En una implementación real, cargaría los datos correspondientes
        actualizarBotones();
    }
    
    private void actualizarBotones() {
        btnAnterior.setEnabled(paginaActual > 0);
        // En una implementación real, verificaríamos si hay más datos
        btnSiguiente.setEnabled(modeloTabla.getRowCount() == REGISTROS_POR_PAGINA);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Cliente Transacciones");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.add(new ClienteTransacciones());
            frame.setVisible(true);
        });
    }
}
