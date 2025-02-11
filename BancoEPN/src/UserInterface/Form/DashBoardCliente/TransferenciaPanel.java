package UserInterface.Form.DashBoardCliente;

import BussinesLogic.Entities.BancoLogic.ValidarTransaccion;
import java.awt.*;
import javax.swing.*;

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
        gbc.insets = new Insets(20, 30, 20, 30);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Panel para el título
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.decode("#D3D3D3"));
        JLabel titleLabel = new JLabel("Realizar Transferencia");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.decode("#274156"));
        titlePanel.add(titleLabel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 30, 40, 30);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(titlePanel, gbc);

        // Campo cuenta destino
        JLabel cuentaLabel = new JLabel("Número de Cuenta Destino:");
        cuentaLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(cuentaLabel, gbc);

        cuentaDestinoField = new JTextField(12);
        cuentaDestinoField.setFont(new Font("Arial", Font.PLAIN, 14));
        cuentaDestinoField.setBorder(BorderFactory.createLineBorder(Color.decode("#274156")));
        gbc.gridx = 1;
        formPanel.add(cuentaDestinoField, gbc);

        // Campo monto
        JLabel montoLabel = new JLabel("Monto a Transferir: $");
        montoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(montoLabel, gbc);

        montoField = new JTextField();
        montoField.setFont(new Font("Arial", Font.PLAIN, 14));
        montoField.setBorder(BorderFactory.createLineBorder(Color.decode("#274156")));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(montoField, gbc);

        // Botón transferir
        JButton transferirButton = new JButton("Transferir");
        transferirButton.addActionListener(e -> realizarTransferencia());

        // Botón regresar
        JButton regresarButton = new JButton("Regresar");
        regresarButton.addActionListener(e -> regresar());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(transferirButton);
        buttonPanel.add(regresarButton);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(buttonPanel, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void realizarTransferencia() {
        try {
            String cuentaDestino = cuentaDestinoField.getText();
            double monto = Double.parseDouble(montoField.getText());

            if (cuentaDestino.isEmpty() || monto <= 0) {
                JOptionPane.showMessageDialog(this, "Ingrese un número de cuenta válido y un monto mayor a 0");
                return;
            }

            ValidarTransaccion validarTransaccion = new ValidarTransaccion();
            String resultado = validarTransaccion.procesarTransaccion(idUsuario, Integer.parseInt(cuentaDestino), monto, 2, "Transferencia bancaria", "");

            JOptionPane.showMessageDialog(this, resultado);
            if (resultado.equals("Transacción exitosa")) {
                regresar();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese valores válidos");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al procesar la transacción: " + e.getMessage());
        }
    }

    private void regresar() {
        dispose();
        DashBoardCliente dashboard = new DashBoardCliente(idUsuario);
        dashboard.setVisible(true);
    }
}
