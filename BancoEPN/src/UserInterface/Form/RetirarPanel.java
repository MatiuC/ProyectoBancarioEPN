package UserInterface.Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import BussinesLogic.Entities.ATM.Retiro;
import DataAccess.SQLiteDataHelper;

public class RetirarPanel extends JFrame {

    private JTextField txtNumeroTarjeta;
    private JTextField txtCVV;
    private JTextField txtCantidad;
    private JButton btnConfirmar;
    private Connection connection;

    public RetirarPanel() throws SQLException {
        super("Retiro de Efectivo");

        // Conectar a la base de datos
        this.connection = SQLiteDataHelper.openConnection();

        // Configuración de la ventana
        setSize(400, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        // Configuración del Layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Número de Tarjeta
        JLabel lblNumeroTarjeta = new JLabel("Número de Tarjeta:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblNumeroTarjeta, gbc);

        txtNumeroTarjeta = new JTextField(20);
        gbc.gridx = 1;
        add(txtNumeroTarjeta, gbc);

        // Clave CVV
        JLabel lblCVV = new JLabel("Clave CVV:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblCVV, gbc);

        txtCVV = new JTextField(4);
        gbc.gridx = 1;
        add(txtCVV, gbc);

        // Cantidad a Retirar
        JLabel lblCantidad = new JLabel("Cantidad a Retirar:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblCantidad, gbc);

        txtCantidad = new JTextField(10);
        gbc.gridx = 1;
        add(txtCantidad, gbc);

        // Botón Confirmar
        btnConfirmar = new JButton("Confirmar Retiro");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnConfirmar, gbc);

        // Acción del botón Confirmar
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarRetiro();
            }
        });

        setVisible(true);
    }

    /**
     * Método que ejecuta la lógica de retiro.
     */
    private void realizarRetiro() {
        String numeroTarjeta = txtNumeroTarjeta.getText().trim();
        String cvv = txtCVV.getText().trim();
        String cantidadStr = txtCantidad.getText().trim();
    
        if (numeroTarjeta.isEmpty() || cvv.isEmpty() || cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        try {
            double monto = Double.parseDouble(cantidadStr);
            if (monto <= 0) {
                JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // 🔹 **Aquí llamamos a la lógica de retiro**
            Retiro retiro = new Retiro();
            boolean exito = retiro.realizarRetiro(connection, numeroTarjeta, cvv, monto);
    
            if (exito) {
                JOptionPane.showMessageDialog(this, "Retiro de $" + monto + " realizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Cierra la ventana después del retiro exitoso
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo realizar el retiro. Verifique los datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un monto numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
