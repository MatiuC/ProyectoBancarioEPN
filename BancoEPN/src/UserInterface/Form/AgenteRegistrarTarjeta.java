package UserInterface.Form;

import java.awt.*;

import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DataAccess.DAO.PersonaDAO;
import DataAccess.DTO.PersonaDTO;
import BussinesLogic.Entities.Tarjeta.RegistrarTarjeta;

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

        // Campo de búsqueda
        searchField = new JTextField(15);
        
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

        // Botones
        selectButton = new JButton("Seleccionar Cliente");
        addCardButton = new JButton("Registrar Tarjeta");
        registerButton = new JButton("Registrar en BD");

        resetComponentStates();

        // Diseño
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de búsqueda y tabla
        JScrollPane scrollPane = new JScrollPane(clientTable);
        scrollPane.setPreferredSize(new Dimension(405, 200));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(selectButton);
        buttonPanel.add(addCardButton);
        buttonPanel.add(registerButton);

        JPanel cardInputPanel = new JPanel(new FlowLayout());
        cardInputPanel.add(new JLabel("Número de Tarjeta:"));
        cardInputPanel.add(cardNumberField);

        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(cardInputPanel, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
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

            String numeroTarjeta = cardNumberField.getText().trim();
            if (numeroTarjeta.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el número de tarjeta.");
                return;
            }

            registerButton.setEnabled(true);
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

    private void registerCard() {
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
        int personaId = Integer.parseInt(cedula.substring(cedula.length() - 4)); // Simulación de ID
        int cuentaBancariaId = 1; // Simulado

        // Registrar la tarjeta en la base de datos
        tarjetaService.crearTarjeta(numeroTarjeta, personaId, cuentaBancariaId, 1, 1);

        JOptionPane.showMessageDialog(this, "Tarjeta registrada correctamente.");
        resetComponentStates();
    }
    public JPanel getFormularioPanel() {
        setLayout(new BorderLayout());
        initializeComponents();
        setupListeners();
        loadClientData();
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BorderLayout());
        return formPanel;
    }

}
