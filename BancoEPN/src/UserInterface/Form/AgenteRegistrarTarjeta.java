package UserInterface.Form;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class AgenteRegistrarTarjeta extends JPanel {
    private JPanel topPanel;
    private JLabel infoLabel;
    private JLabel userLabel;
    private JLabel titleLabel;
    private JTextField searchField;
    private JTable clientTable;
    private DefaultTableModel tableModel;
    private JButton selectButton;
    private JButton addCardButton;
    private JButton registerButton;
    private boolean clientSelected = false;
    private String scannedCard = null;
    
    public AgenteRegistrarTarjeta() {
        setLayout(new BorderLayout());
        initializeComponents();
        setupLayout();
        setupListeners();
        
        // Cargar datos de ejemplo
        loadSampleData();
    }
    
    private void initializeComponents() {
        // Panel superior (8% de altura)
        topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(getWidth(), (int)(getHeight() * 0.08)));
        topPanel.setBackground(new Color(0, 83, 138)); // Color institucional EPN
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        topPanel.setLayout(new GridLayout(1, 2, 10, 0));
        
        // Información y usuario
        JPanel leftInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftInfo.setOpaque(false);
        infoLabel = new JLabel("BANCO EPN - Sistema de Registro de Tarjetas");
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        leftInfo.add(infoLabel);
        
        JPanel rightInfo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightInfo.setOpaque(false);
        userLabel = new JLabel("Usuario: Admin");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        rightInfo.add(userLabel);
        
        topPanel.add(leftInfo);
        topPanel.add(rightInfo);
        
        // Título
        titleLabel = new JLabel("Registro de tarjeta", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        // Campo de búsqueda
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        
        JLabel searchLabel = new JLabel("Ingresar cédula:", SwingConstants.CENTER);
        searchLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchField = new JTextField(15);
        searchField.setMaximumSize(new Dimension(200, 30));
        searchField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        searchPanel.add(Box.createVerticalStrut(20));
        searchPanel.add(searchLabel);
        searchPanel.add(Box.createVerticalStrut(5));
        searchPanel.add(searchField);
        searchPanel.add(Box.createVerticalStrut(20));
        
        // Tabla de clientes
        String[] columns = {"Cédula", "Nombre", "Apellido"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        clientTable = new JTable(tableModel);
        clientTable.getTableHeader().setReorderingAllowed(false);
        clientTable.getTableHeader().setResizingAllowed(false);
        clientTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        clientTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        // Configurar anchos fijos de columnas
        TableColumn cedulaColumn = clientTable.getColumnModel().getColumn(0);
        TableColumn nombreColumn = clientTable.getColumnModel().getColumn(1);
        TableColumn apellidoColumn = clientTable.getColumnModel().getColumn(2);
        
        cedulaColumn.setPreferredWidth(100);
        nombreColumn.setPreferredWidth(150);
        apellidoColumn.setPreferredWidth(150);
        
        // Botones
        selectButton = new JButton("Seleccionar Cliente");
        addCardButton = new JButton("Agregar Tarjeta");
        registerButton = new JButton("REALIZAR REGISTRO");
        
        // Estilo de botones
        selectButton.setPreferredSize(new Dimension(150, 35));
        addCardButton.setPreferredSize(new Dimension(150, 35));
        registerButton.setPreferredSize(new Dimension(150, 35));
        
        // Estado inicial de botones y componentes
        resetComponentStates();
    }
    
    private void setupLayout() {
        // Configuración del panel superior
        add(topPanel, BorderLayout.NORTH);
        
        // Panel central
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Agregar título
        centerPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Panel de búsqueda y tabla
        JPanel searchTablePanel = new JPanel(new BorderLayout(5, 5));
        
        // Panel para el campo de búsqueda
        JPanel searchWrapperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchWrapperPanel.add(new JPanel() {{
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            add(Box.createVerticalStrut(10));
            add(searchField);
            add(Box.createVerticalStrut(10));
        }});
        searchTablePanel.add(searchWrapperPanel, BorderLayout.NORTH);
        
        // Panel para la tabla centrada
        JPanel tableWrapperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JScrollPane scrollPane = new JScrollPane(clientTable);
        scrollPane.setPreferredSize(new Dimension(405, 200)); // Ancho fijo para la tabla
        tableWrapperPanel.add(scrollPane);
        searchTablePanel.add(tableWrapperPanel, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(selectButton);
        buttonPanel.add(addCardButton);
        buttonPanel.add(registerButton);
        
        centerPanel.add(searchTablePanel, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(centerPanel, BorderLayout.CENTER);
    }
    
    private void setupListeners() {
        // Búsqueda en tiempo real
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterClients(searchField.getText());
            }
        });
        
        // Selección de cliente
        selectButton.addActionListener(e -> {
            int selectedRow = clientTable.getSelectedRow();
            if (selectedRow >= 0) {
                clientSelected = true;
                
                // Deshabilitar selección de cliente
                selectButton.setEnabled(false);
                searchField.setEnabled(false);
                clientTable.setEnabled(false);
                
                // Habilitar siguiente paso
                addCardButton.setEnabled(true);
                
                JOptionPane.showMessageDialog(this, 
                    "Cliente seleccionado: " + 
                    tableModel.getValueAt(selectedRow, 1) + " " + 
                    tableModel.getValueAt(selectedRow, 2));
            }
        });
        
        // Agregar tarjeta
        addCardButton.addActionListener(e -> {
            showCardScannerDialog();
            addCardButton.setEnabled(false);
        });
        
        // Registro final
        registerButton.addActionListener(e -> {
            registerCard();
            resetComponentStates();
            loadSampleData(); // Recargar datos de ejemplo
        });
    }
    
    private void resetComponentStates() {
        // Resetear estados
        clientSelected = false;
        scannedCard = null;
        
        // Habilitar componentes de selección
        searchField.setEnabled(true);
        clientTable.setEnabled(true);
        selectButton.setEnabled(true);
        
        // Deshabilitar botones de proceso
        addCardButton.setEnabled(false);
        registerButton.setEnabled(false);
        
        // Limpiar búsqueda
        searchField.setText("");
        tableModel.setRowCount(0);
    }
    
    private void loadSampleData() {
        // Datos de ejemplo
        String[][] sampleData = {
            {"1234567890", "Juan", "Pérez"},
            {"0987654321", "María", "González"},
            {"1122334455", "Pedro", "Rodríguez"}
        };
        
        for (String[] row : sampleData) {
            tableModel.addRow(row);
        }
    }
    
    private void filterClients(String cedula) {
        tableModel.setRowCount(0);
        // Filtrando datos de ejemplo
        String[][] sampleData = {
            {"1234567890", "Juan", "Pérez"},
            {"0987654321", "María", "González"},
            {"1122334455", "Pedro", "Rodríguez"}
        };
        
        for (String[] row : sampleData) {
            if (row[0].contains(cedula)) {
                tableModel.addRow(row);
            }
        }
    }
    
    private void showCardScannerDialog() {
        JDialog scannerDialog = new JDialog((Frame)null, "Escanear Tarjeta", true);
        scannerDialog.setUndecorated(true); // Elimina los botones de la ventana
        scannerDialog.setSize(300, 150);
        scannerDialog.setLocationRelativeTo(this);
        scannerDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        
        JPanel scannerPanel = new JPanel(new BorderLayout(10, 10));
        scannerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel scanLabel = new JLabel("ESCANEAR TARJETA", SwingConstants.CENTER);
        scanLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField scanField = new JTextField();
        scanField.requestFocusInWindow(); // Dar foco al campo de texto
        
        
        scannerPanel.add(scanLabel, BorderLayout.NORTH);
        scannerPanel.add(scanField, BorderLayout.CENTER);
        
        scanField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String input = scanField.getText().trim();
                    if (!input.isEmpty()) {
                        scannedCard = input;
                        registerButton.setEnabled(true);
                        scannerDialog.dispose();
                    }
                }
            }
        });
        
        scannerDialog.add(scannerPanel);
        scannerDialog.setVisible(true);
    }
    
    private void registerCard() {
        if (clientSelected && scannedCard != null) {
            int selectedRow = clientTable.getSelectedRow();
            JOptionPane.showMessageDialog(this, 
                "Tarjeta " + scannedCard + " registrada exitosamente para el cliente: " + 
                tableModel.getValueAt(selectedRow, 1) + " " + 
                tableModel.getValueAt(selectedRow, 2));
        }
    }
    
    public void setUserInfo(String username) {
        userLabel.setText("Usuario: " + username);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Establecer Look and Feel del sistema
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            JFrame frame = new JFrame("Registro de Tarjeta - BANCO EPN");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 500);
            frame.setMinimumSize(new Dimension(500, 400));
            frame.setLocationRelativeTo(null);
            
            AgenteRegistrarTarjeta panel = new AgenteRegistrarTarjeta();
            frame.getContentPane().setBackground(new Color(240, 240, 240));
            frame.setContentPane(panel);
            
            frame.setVisible(true);
        });
    }
}
