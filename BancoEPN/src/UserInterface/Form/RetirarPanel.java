package UserInterface.Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;

import BussinesLogic.Entities.ATM.Retiro;
import DataAccess.SQLiteDataHelper;

public class RetirarPanel extends JFrame {
    private JTextField txtNumeroTarjeta;
    private JTextField txtCVV;
    private JTextField txtCantidad;
    private JButton btnConfirmar;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private Connection connection;

    public RetirarPanel() throws SQLException {
        setTitle("Retiro de Efectivo");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        // Conectar a la base de datos
        this.connection = SQLiteDataHelper.openConnection();
        
        // Panel 1: Escanear tarjeta
        JPanel panelEscaneo = crearPanelEscaneo();
        
        // Panel 2: Ingresar PIN
        JPanel panelPIN = crearPanelPIN();
        
        // Panel 3: Seleccionar monto
        JPanel panelMonto = crearPanelMonto();
        
        cardPanel.add(panelEscaneo, "ESCANEO");
        cardPanel.add(panelPIN, "PIN");
        cardPanel.add(panelMonto, "MONTO");
        
        add(cardPanel);
        cardLayout.show(cardPanel, "ESCANEO");
        setVisible(true);
    }
    
    private JPanel crearPanelEscaneo() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel lblInstruccion = new JLabel("Por favor, escanee su tarjeta");
        lblInstruccion.setFont(new Font("Arial", Font.BOLD, 18));
        
        txtNumeroTarjeta = new JTextField(20);
        txtNumeroTarjeta.requestFocus();
        
        txtNumeroTarjeta.setForeground(panel.getBackground());
        txtNumeroTarjeta.setCaretColor(panel.getBackground());
        txtNumeroTarjeta.setBorder(null);
        
        txtNumeroTarjeta.addActionListener(e -> {
            if (!txtNumeroTarjeta.getText().trim().isEmpty()) {
                cardLayout.show(cardPanel, "PIN");
                txtCVV.requestFocus();
            }
        });
        
        panel.add(lblInstruccion, gbc);
        panel.add(txtNumeroTarjeta, gbc);
        
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtNumeroTarjeta.requestFocus();
            }
        });
        
        return panel;
    }
    
    private JPanel crearPanelPIN() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel lblPIN = new JLabel("Ingrese su PIN");
        lblPIN.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(lblPIN, gbc);
        
        txtCVV = new JPasswordField(4);
        txtCVV.setHorizontalAlignment(JTextField.CENTER);
        txtCVV.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridy = 1;
        panel.add(txtCVV, gbc);
        
        // Limitar a 4 dígitos
        txtCVV.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txtCVV.getText().length() >= 4 || 
                    !Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
            }
        });
        
        txtCVV.addActionListener(e -> {
            if (txtCVV.getText().length() == 4) {
                cardLayout.show(cardPanel, "MONTO");
            }
        });
        
        JButton btnCancelar = new JButton("Cancelar");
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        btnCancelar.addActionListener(e -> cancelarOperacion());
        panel.add(btnCancelar, gbc);
        
        return panel;
    }
    
    private JPanel crearPanelMonto() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel lblMonto = new JLabel("Seleccione el monto a retirar");
        lblMonto.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblMonto, gbc);
        
        String[] montos = {"$20", "$50", "$100", "$200", "$500", "Otro monto"};
        gbc.gridwidth = 1;
        
        for (int i = 0; i < montos.length; i++) {
            JButton btnMonto = new JButton(montos[i]);
            gbc.gridx = i % 2;
            gbc.gridy = (i / 2) + 1;
            btnMonto.setPreferredSize(new Dimension(120, 40));
            
            btnMonto.addActionListener(e -> {
                if (btnMonto.getText().equals("Otro monto")) {
                    String monto = JOptionPane.showInputDialog("Ingrese el monto a retirar:");
                    if (monto != null && !monto.isEmpty()) {
                        txtCantidad.setText(monto);
                    }
                } else {
                    txtCantidad.setText(btnMonto.getText().replace("$", ""));
                }
                finalizarRetiro();
            });
            
            panel.add(btnMonto, gbc);
        }
        
        txtCantidad = new JTextField(10);
        txtCantidad.setVisible(false);
        panel.add(txtCantidad);
        
        JButton btnCancelar = new JButton("Cancelar");
        gbc.gridx = 0;
        gbc.gridy = (montos.length / 2) + 2;
        gbc.gridwidth = 2;
        btnCancelar.addActionListener(e -> cancelarOperacion());
        panel.add(btnCancelar, gbc);
        
        return panel;
    }
    
    private void finalizarRetiro() {
        String numeroTarjeta = txtNumeroTarjeta.getText().trim();
        String cvv = txtCVV.getText().trim();
        String cantidadStr = txtCantidad.getText().trim();
    
        if (numeroTarjeta.isEmpty() || cvv.isEmpty() || cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Error en la operación", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        try {
            double monto = Double.parseDouble(cantidadStr);
            if (monto <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "Monto inválido", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            Retiro retiro = new Retiro();
            boolean exito = retiro.realizarRetiro( numeroTarjeta, cvv, monto);
    
            if (exito) {
                JOptionPane.showMessageDialog(this, 
                    "Retiro exitoso por $" + monto, 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
                regresarATM();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "No se pudo realizar el retiro", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Monto inválido", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cancelarOperacion() {
        int respuesta = JOptionPane.showConfirmDialog(this,
            "¿Está seguro que desea cancelar la operación?",
            "Confirmar",
            JOptionPane.YES_NO_OPTION);
            
        if (respuesta == JOptionPane.YES_OPTION) {
            regresarATM();
        }
    }
    
    private void regresarATM() {
        dispose();
        ATMPanel atmPanel = new ATMPanel();
        atmPanel.setVisible(true);
    }
}
