package UserInterface.CustomerControl;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

public class CustomTransactionTable extends JPanel {
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private List<JButton> botonesNumeros;
    private JButton btnAnterior;
    private JButton btnSiguiente;
    private int paginaActual = 1;
    private final int REGISTROS_POR_PAGINA = 5;
    private final int MAX_PAGINAS_MOSTRADAS = 5;
    private JLabel mensajeNoData;
    private List<String[]> todosLosDatos;
    private JScrollPane scrollPane;

    public CustomTransactionTable() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        botonesNumeros = new ArrayList<>();
        todosLosDatos = new ArrayList<>();
        
        // Inicializar datos de prueba primero
        inicializarDatosPrueba();
        
        // Luego inicializar componentes
        inicializarComponentes();
        
        // Finalmente mostrar la primera página
        mostrarPagina(1);
    }

    private void inicializarDatosPrueba() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        // Generar datos de ejemplo
        for (int i = 1; i <= 20; i++) {
            todosLosDatos.add(new String[]{
                "Cuenta-" + (1000 + i),
                "Destino-" + (2000 + i),
                "$" + (i * 100) + ".00",
                i % 3 == 0 ? "Transferencia" : (i % 3 == 1 ? "Depósito" : "Retiro"),
                sdf.format(new Date())
            });
        }
    }

    private void inicializarComponentes() {
        // Crear el modelo de tabla
        String[] columnas = {"Origen", "Destino", "Monto", "Tipo de Transacción", "Fecha de Transacción"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Crear y configurar la tabla
        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(56);
        tabla.setShowGrid(true);
        tabla.setGridColor(new Color(240, 240, 240));
        tabla.setFont(new Font("Arial", Font.PLAIN, 14));
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setReorderingAllowed(false);

        // Configurar header
        JTableHeader header = tabla.getTableHeader();
        header.setPreferredSize(new Dimension(0, 56));
        header.setBackground(new Color(245, 246, 250));
        header.setForeground(new Color(52, 73, 94));
        header.setFont(new Font("Arial", Font.BOLD, 14));

        // Configurar renderer para las columnas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < tabla.getColumnCount(); i++) {
            TableColumn column = tabla.getColumnModel().getColumn(i);
            column.setCellRenderer(centerRenderer);
            
            switch (i) {
                case 0: // Origen
                case 1: // Destino
                    column.setPreferredWidth(150);
                    break;
                case 2: // Monto
                    column.setPreferredWidth(100);
                    break;
                case 3: // Tipo de Transacción
                    column.setPreferredWidth(150);
                    column.setCellRenderer(new DefaultTableCellRenderer() {
                        @Override
                        public Component getTableCellRendererComponent(JTable table, Object value,
                                boolean isSelected, boolean hasFocus, int row, int column) {
                            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                            String tipo = value.toString();
                            setHorizontalAlignment(JLabel.CENTER);
                            if (tipo.equals("Depósito")) {
                                setForeground(new Color(46, 204, 113));
                            } else if (tipo.equals("Retiro")) {
                                setForeground(new Color(231, 76, 60));
                            } else {
                                setForeground(new Color(52, 152, 219));
                            }
                            return c;
                        }
                    });
                    break;
                case 4: // Fecha
                    column.setPreferredWidth(200);
                    break;
            }
        }

        // ScrollPane
        scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));
        scrollPane.setBackground(Color.WHITE);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Mensaje de no datos
        mensajeNoData = new JLabel("No hay transacciones para mostrar", SwingConstants.CENTER);
        mensajeNoData.setFont(new Font("Arial", Font.BOLD, 14));
        mensajeNoData.setForeground(new Color(149, 165, 166));

        // Panel de paginación
        JPanel paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
        paginationPanel.setBackground(Color.WHITE);

        // Botones de navegación
        btnAnterior = crearBotonNavegacion("←");
        btnAnterior.addActionListener(e -> mostrarPagina(paginaActual - 1));

        // Botones de número
        for (int i = 1; i <= MAX_PAGINAS_MOSTRADAS; i++) {
            JButton btnNumero = crearBotonNumero(String.valueOf(i));
            final int pagina = i;
            btnNumero.addActionListener(e -> mostrarPagina(pagina));
            botonesNumeros.add(btnNumero);
            paginationPanel.add(btnNumero);
        }

        // Botón siguiente
        btnSiguiente = crearBotonNavegacion("→");
        btnSiguiente.addActionListener(e -> mostrarPagina(paginaActual + 1));

        // Agregar botones al panel de paginación
        paginationPanel.add(btnAnterior);
        for (JButton btn : botonesNumeros) {
            paginationPanel.add(btn);
        }
        paginationPanel.add(btnSiguiente);

        // Agregar todo al panel principal
        add(mainPanel, BorderLayout.CENTER);
        add(paginationPanel, BorderLayout.SOUTH);
    }

    private JButton crearBotonNavegacion(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 18));
        configurarBoton(btn);
        return btn;
    }

    private JButton crearBotonNumero(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        configurarBoton(btn);
        return btn;
    }

    private void configurarBoton(JButton btn) {
        btn.setPreferredSize(new Dimension(40, 40));
        btn.setMargin(new Insets(0, 0, 0, 0));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setForeground(new Color(52, 73, 94));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (btn.isEnabled()) {
                    btn.setContentAreaFilled(true);
                    btn.setBackground(new Color(52, 152, 219));
                    btn.setForeground(Color.WHITE);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!btn.equals(botonesNumeros.get(paginaActual - 1))) {
                    btn.setContentAreaFilled(false);
                    btn.setForeground(new Color(52, 73, 94));
                }
            }
        });
    }

    private void mostrarPagina(int nuevaPagina) {
        if (nuevaPagina < 1 || nuevaPagina > getTotalPaginas()) return;

        paginaActual = nuevaPagina;
        int inicio = (paginaActual - 1) * REGISTROS_POR_PAGINA;
        int fin = Math.min(inicio + REGISTROS_POR_PAGINA, todosLosDatos.size());

        modeloTabla.setRowCount(0);
        for (int i = inicio; i < fin; i++) {
            modeloTabla.addRow(todosLosDatos.get(i));
        }

        actualizarBotonesPaginacion();
        actualizarVisibilidadDatos();
    }

    private void actualizarBotonesPaginacion() {
        btnAnterior.setEnabled(paginaActual > 1);
        btnSiguiente.setEnabled(paginaActual < getTotalPaginas());

        for (int i = 0; i < botonesNumeros.size(); i++) {
            JButton btn = botonesNumeros.get(i);
            if (i + 1 == paginaActual) {
                btn.setContentAreaFilled(true);
                btn.setBackground(new Color(52, 152, 219));
                btn.setForeground(Color.WHITE);
            } else {
                btn.setContentAreaFilled(false);
                btn.setForeground(new Color(52, 73, 94));
            }
        }
    }

    private void actualizarVisibilidadDatos() {
        boolean hayDatos = modeloTabla.getRowCount() > 0;
        scrollPane.setVisible(hayDatos);
        mensajeNoData.setVisible(!hayDatos);
        revalidate();
        repaint();
    }

    private int getTotalPaginas() {
        return (int) Math.ceil((double) todosLosDatos.size() / REGISTROS_POR_PAGINA);
    }
}