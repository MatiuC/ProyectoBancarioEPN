package UserInterface.Form.DashBoardCliente;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TransferenciaPanel extends JFrame {
    private JTextField cuentaDestinoField;
    private JTextField montoField;
    private int idUsuario;

    public TransferenciaPanel(int id) {
        this.idUsuario = id;
        setTitle("Transferencia Bancaria");
        
        // Obtener el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.9);
        int height = (int) (screenSize.height * 0.85);
        setSize(width, height);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.decode("#D3D3D3"));

        // Panel para el formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.decode("#D3D3D3"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 30, 20, 30); // Márgenes más amplios
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Título
        JLabel titleLabel = new JLabel("Realizar Transferencia");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.decode("#274156"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 30, 40, 30); // Margen especial para el título
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(titleLabel, gbc);

        // Restaurar márgenes normales
        gbc.insets = new Insets(20, 30, 20, 30);

        // Campo cuenta destino
        JLabel cuentaLabel = new JLabel("Número de Cuenta Destino:");
        cuentaLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST; // Alinear a la derecha
        formPanel.add(cuentaLabel, gbc);

        // Panel para agrupar el campo de cuenta y el botón validar
        JPanel cuentaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        cuentaPanel.setBackground(Color.decode("#D3D3D3"));

        cuentaDestinoField = new JTextField(20);
        cuentaDestinoField.setFont(new Font("Arial", Font.PLAIN, 14));
        cuentaDestinoField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#274156")),
            new EmptyBorder(5, 5, 5, 5)));

        // Botón validar cuenta
        JButton validarButton = new JButton("Validar cuenta");
        validarButton.setFont(new Font("Arial", Font.BOLD, 14));
        validarButton.setBackground(new Color(52, 152, 219));
        validarButton.setForeground(Color.WHITE);
        validarButton.setFocusPainted(false);
        validarButton.setBorderPainted(false);
        validarButton.setPreferredSize(new Dimension(120, 35));
        validarButton.addActionListener(e -> {
            String numeroCuenta = cuentaDestinoField.getText();
            if (numeroCuenta.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Por favor ingrese un número de cuenta",
                    "Campo Vacío",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Aquí iría la lógica de validación de cuenta
            JOptionPane.showMessageDialog(this,
                "Validación de cuenta en desarrollo",
                "Información",
                JOptionPane.INFORMATION_MESSAGE);
        });

        cuentaPanel.add(cuentaDestinoField);
        cuentaPanel.add(validarButton);

        gbc.gridx = 1;
        formPanel.add(cuentaPanel, gbc);

        // Campo monto
        JLabel montoLabel = new JLabel("Monto a Transferir: $");
        montoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST; // Alinear a la derecha
        formPanel.add(montoLabel, gbc);

        montoField = new JTextField(20);
        montoField.setFont(new Font("Arial", Font.PLAIN, 14));
        montoField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#274156")),
            new EmptyBorder(8, 10, 8, 10))); // Padding interno más grande
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST; // Alinear a la izquierda
        formPanel.add(montoField, gbc);

        // Panel para botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        buttonPanel.setBackground(Color.decode("#D3D3D3"));

        // Botón transferir
        JButton transferirButton = new JButton("Transferir");
        transferirButton.setFont(new Font("Arial", Font.BOLD, 16));
        transferirButton.setBackground(new Color(52, 152, 219));
        transferirButton.setForeground(Color.WHITE);
        transferirButton.setFocusPainted(false);
        transferirButton.setBorderPainted(false);
        transferirButton.setPreferredSize(new Dimension(150, 40));
        transferirButton.addActionListener(e -> realizarTransferencia());

        // Botón regresar
        JButton regresarButton = new JButton("Regresar");
        regresarButton.setFont(new Font("Arial", Font.BOLD, 16));
        regresarButton.setBackground(new Color(47, 72, 88));
        regresarButton.setForeground(Color.WHITE);
        regresarButton.setFocusPainted(false);
        regresarButton.setBorderPainted(false);
        regresarButton.setPreferredSize(new Dimension(150, 40));
        regresarButton.addActionListener(e -> regresar());

        buttonPanel.add(transferirButton);
        buttonPanel.add(regresarButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(40, 30, 30, 30); // Más espacio arriba de los botones
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(buttonPanel, gbc);

        // Centra el formulario en el panel principal
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.setBackground(Color.decode("#D3D3D3"));
        wrapperPanel.add(formPanel);
        
        mainPanel.add(wrapperPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void realizarTransferencia() {
        String cuentaDestino = cuentaDestinoField.getText();
        String monto = montoField.getText();

        if (cuentaDestino.isEmpty() || monto.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor complete todos los campos", 
                "Campos Incompletos", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double montoTransferencia = Double.parseDouble(monto);
            if (montoTransferencia <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "El monto debe ser mayor a 0", 
                    "Monto Inválido", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Aquí iría la lógica de transferencia
            JOptionPane.showMessageDialog(this, 
                "Transferencia realizada con éxito", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            regresar();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Por favor ingrese un monto válido", 
                "Monto Inválido", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void regresar() {
        dispose();
        DashBoardCliente dashboard = new DashBoardCliente(idUsuario);
        dashboard.setVisible(true);
    }
}