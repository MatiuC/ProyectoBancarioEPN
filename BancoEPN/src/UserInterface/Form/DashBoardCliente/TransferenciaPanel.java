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

        // Panel para el título
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.decode("#D3D3D3"));
        JLabel titleLabel = new JLabel("Realizar Transferencia");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.decode("#274156"));
        titlePanel.add(titleLabel);

        // Agregar el panel del título
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 30, 40, 30);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(titlePanel, gbc);

        // Restaurar márgenes y configuraciones normales
        gbc.insets = new Insets(20, 15, 20, 30); // Reducir el margen izquierdo de 30 a 15
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo cuenta destino
        JLabel cuentaLabel = new JLabel("Número de Cuenta Destino:");
        cuentaLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST; // Alinear a la derecha
        formPanel.add(cuentaLabel, gbc);

        // Panel para agrupar el campo de cuenta y el botón validar
        JPanel cuentaPanel = new JPanel();
        cuentaPanel.setLayout(new BoxLayout(cuentaPanel, BoxLayout.X_AXIS));
        cuentaPanel.setBackground(Color.decode("#D3D3D3"));

        cuentaDestinoField = new JTextField(12);
        cuentaDestinoField.setFont(new Font("Arial", Font.PLAIN, 14));
        cuentaDestinoField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#274156")),
            new EmptyBorder(8, 10, 8, 10)));
        cuentaDestinoField.setPreferredSize(new Dimension(cuentaDestinoField.getPreferredSize().width, 35));

        // Botón validar cuenta
        JButton validarButton = new JButton("Validar cuenta");
        validarButton.setFont(new Font("Arial", Font.BOLD, 14));
        validarButton.setBackground(new Color(52, 152, 219));
        validarButton.setForeground(Color.WHITE);
        validarButton.setFocusPainted(false);
        validarButton.setBorderPainted(false);
        validarButton.setPreferredSize(new Dimension(150, 40));
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

        // Agregar un espacio rígido entre el campo y el botón
        cuentaPanel.add(cuentaDestinoField);
        cuentaPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        cuentaPanel.add(validarButton);

        // Establecer el tamaño preferido del panel para que coincida con el campo de monto
        cuentaPanel.setPreferredSize(new Dimension(350, 35));

        gbc.gridx = 1;
        formPanel.add(cuentaPanel, gbc);

        // Campo monto
        JLabel montoLabel = new JLabel("Monto a Transferir: $");
        montoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(montoLabel, gbc);

        // Configurar el campo de monto para que coincida exactamente con el panel de cuenta
        montoField = new JTextField();
        montoField.setFont(new Font("Arial", Font.PLAIN, 14));
        montoField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#274156")),
            new EmptyBorder(8, 10, 8, 10)));
        montoField.setPreferredSize(new Dimension(350, 35));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
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