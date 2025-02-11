package UserInterface.Form;

import BussinesLogic.Entities.Tarjeta.RegistrarTarjeta;
import DataAccess.DAO.PersonaDAO;
import DataAccess.DTO.PersonaDTO;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AgenteRegistrarTarjeta extends JPanel {
    private JPanel topPanel;
    private JLabel infoLabel;
    private JLabel userLabel;
    private JLabel titleLabel;
    private JTextField searchField;
    private JTable clientTable;
    private DefaultTableModel tableModel;
    private JTextField cardNumberField; // Campo para ingresar el número de tarjeta
    private JButton selectButton, addCardButton, registerButton;
    private boolean clientSelected = false;
    private RegistrarTarjeta tarjetaService = new RegistrarTarjeta();

    public AgenteRegistrarTarjeta() {
        setLayout(new BorderLayout());
        initializeComponents();
        setupListeners();
        loadClientData();
    }

    private void initializeComponents() {
        // Panel superior
        topPanel = new JPanel();
        topPanel.setBackground(new Color(0, 83, 138));
        infoLabel = new JLabel("BANCO EPN - Registro de Tarjetas", SwingConstants.CENTER);
        infoLabel.setForeground(Color.WHITE);
        userLabel = new JLabel("Usuario: Admin", SwingConstants.RIGHT);
        userLabel.setForeground(Color.WHITE);
        topPanel.add(infoLabel);
        topPanel.add(userLabel);

        // Título
        titleLabel = new JLabel("Registro de Tarjeta", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Panel de búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel searchLabel = new JLabel("Buscar por cédula:");
        searchField = new JTextField(15);
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filterTable(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filterTable(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filterTable(); }
        });
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        
        // Tabla de clientes
        String[] columns = {"Cédula", "Nombre", "Apellido"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        clientTable = new JTable(tableModel);

        // Campo para ingresar el número de tarjeta manualmente
        cardNumberField = new JTextField(20);
        cardNumberField.setEditable(false); // El número se obtiene por escaneo

        // Botones
        selectButton = new JButton("Seleccionar Cliente");
        addCardButton = new JButton("Registrar Tarjeta");
        registerButton = new JButton("Registrar en BD");

        resetComponentStates();

        // Diseño
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de búsqueda y tabla
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(searchPanel, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(clientTable);
        scrollPane.setPreferredSize(new Dimension(405, 200));
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(selectButton);
        buttonPanel.add(addCardButton);
        buttonPanel.add(registerButton);

        JPanel cardInputPanel = new JPanel(new FlowLayout());
        cardInputPanel.add(new JLabel("Número de Tarjeta:"));
        cardInputPanel.add(cardNumberField);

        centerPanel.add(tablePanel, BorderLayout.CENTER);
        centerPanel.add(cardInputPanel, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void filterTable() {
        String searchText = searchField.getText().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) clientTable.getModel();
        model.setRowCount(0); // Limpiar tabla

        try {
            PersonaDAO personaDAO = new PersonaDAO();
            List<PersonaDTO> personas = personaDAO.readFromView();
            
            for (PersonaDTO persona : personas) {
                if (persona.getCedula().toLowerCase().contains(searchText)) {
                    model.addRow(new Object[]{
                        persona.getCedula(),
                        persona.getNombre(),
                        persona.getApellido()
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al filtrar los datos.");
        }
    }

    private void setupListeners() {
        selectButton.addActionListener(e -> {
            int selectedRow = clientTable.getSelectedRow();
            if (selectedRow >= 0) {
                clientSelected = true;
                selectButton.setEnabled(false);
                searchField.setEnabled(false);
                clientTable.setEnabled(false);
                addCardButton.setEnabled(true);
                JOptionPane.showMessageDialog(this, "Cliente seleccionado.");
            }
        });

        addCardButton.addActionListener(e -> {
            if (!clientSelected) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente primero.");
                return;
            }
            
            mostrarVentanaCapturaTarjeta();
        });

        registerButton.addActionListener(e -> {
            registerCard();
        });
    }

    private void resetComponentStates() {
        clientSelected = false;
        cardNumberField.setText("");
        searchField.setEnabled(true);
        clientTable.setEnabled(true);
        selectButton.setEnabled(true);
        addCardButton.setEnabled(false);
        registerButton.setEnabled(false);
    }

    private void loadClientData() {
        try {
            PersonaDAO personaDAO = new PersonaDAO();
            List<PersonaDTO> personas = personaDAO.readFromView();
            
            tableModel.setRowCount(0);
            for (PersonaDTO persona : personas) {
                tableModel.addRow(new Object[]{persona.getCedula(), persona.getNombre(), persona.getApellido()});
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los datos de clientes.");
        }
    }

    private void mostrarVentanaCapturaTarjeta() {
        // Crear una ventana modal sin decoración
        JDialog dialog = new JDialog((Frame)SwingUtilities.getWindowAncestor(this), true);
        dialog.setUndecorated(true); // Elimina los botones de la ventana
        dialog.setLayout(new BorderLayout(10, 10));
        
        // Panel principal con padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Etiqueta de instrucción
        JLabel instructionLabel = new JLabel("Ingrese el número de tarjeta:", SwingConstants.CENTER);
        mainPanel.add(instructionLabel, BorderLayout.NORTH);
        
        // Campo de texto para la tarjeta
        JTextField tarjetaField = new JTextField(20);
        mainPanel.add(tarjetaField, BorderLayout.CENTER);
        
        // Añadir KeyListener para detectar ENTER
        tarjetaField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    String numeroTarjeta = tarjetaField.getText().trim();
                    if (!numeroTarjeta.isEmpty()) {
                        cardNumberField.setText(numeroTarjeta);
                        addCardButton.setEnabled(false);
                        registerButton.setEnabled(true);
                        dialog.dispose();
                    }
                }
            }
        });
        
        // Configurar y mostrar el diálogo
        dialog.add(mainPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void registerCard() {
        try {
            if (!clientSelected) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente primero.");
                return;
            }

            String numeroTarjeta = cardNumberField.getText().trim();
            if (numeroTarjeta.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el número de tarjeta.");
                return;
            }

            int selectedRow = clientTable.getSelectedRow();
            String cedula = (String) tableModel.getValueAt(selectedRow, 0);

            // Registrar la tarjeta en la base de datos
            tarjetaService.crearTarjeta(
                numeroTarjeta,
                Integer.parseInt(cedula),
                1, // cuentaBancariaId (por defecto 1)
                1, // tipo tarjeta (por defecto 1)
                1  // franquicia (por defecto 1)
            );

            // Mostrar mensaje de éxito con detalles
            JOptionPane.showMessageDialog(this,
                "Tarjeta registrada exitosamente\n" +
                "Número: " + numeroTarjeta + "\n" +
                "Cliente: " + cedula,
                "Registro Exitoso",
                JOptionPane.INFORMATION_MESSAGE);

            // Reiniciar el proceso
            resetComponentStates();
            loadClientData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al registrar la tarjeta: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    public JPanel getFormularioPanel() {
        // Create a new instance of the panel with all components
        JPanel formPanel = new JPanel(new BorderLayout());
        
        // Add the top panel
        formPanel.add(topPanel, BorderLayout.NORTH);
        
        // Create and add the center panel with all components
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel for table
        JScrollPane scrollPane = new JScrollPane(clientTable);
        scrollPane.setPreferredSize(new Dimension(405, 200));
        
        // Panel for card input
        JPanel cardInputPanel = new JPanel(new FlowLayout());
        cardInputPanel.add(new JLabel("Número de Tarjeta:"));
        cardInputPanel.add(cardNumberField);
        
        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(selectButton);
        buttonPanel.add(addCardButton);
        buttonPanel.add(registerButton);
        
        // Add all components to center panel
        centerPanel.add(cardInputPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Add center panel to form panel
        formPanel.add(centerPanel, BorderLayout.CENTER);
        
        return formPanel;
    }

}
