package UserInterface.Form.DashboardClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import UserInterface.CustomerControl.TransparentButton;




public class Panel extends JPanel {
    
    public Panel() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#FFFFFF")); // Fondo blanco

        // ============================
        // Encabezado Superior
        // ============================
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.decode("#FFFFFF"));
        headerPanel.setPreferredSize(new Dimension(0, 60));

        // Parte Izquierda: Ícono de usuario y bienvenida
        JPanel leftHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        leftHeader.setOpaque(false);
        ImageIcon userIconRaw = new ImageIcon("BancoEPN\\src\\UserInterface\\Resource\\Icon\\IconUser.png");
        Image userIconImg = userIconRaw.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JLabel userIcon = new JLabel(new ImageIcon(userIconImg));
        JLabel welcomeLabel = new JLabel("Bienvenido, Usuario");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setForeground(Color.decode("#2F4858")); // Color de acento principal
        leftHeader.add(userIcon);
        leftHeader.add(welcomeLabel);
        headerPanel.add(leftHeader, BorderLayout.WEST);

        // Parte Derecha: Botones "Conoce lo nuevo" y "Mostrar saldos" con íconos pequeños
        JPanel rightHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        rightHeader.setOpaque(false);
        JButton btnNovedades = TransparentButton.createButton("Conoce lo nuevo");
        btnNovedades.setFont(new Font("Arial", Font.PLAIN, 14));
        btnNovedades.setForeground(Color.decode("#2F4858"));
        ImageIcon newIcon = new ImageIcon("BancoEPN\\src\\UserInterface\\Resource\\Icon\\new.png");
        Image newImg = newIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        btnNovedades.setIcon(new ImageIcon(newImg));

        JButton btnMostrarSaldos = TransparentButton.createButton("Mostrar saldos");
        btnMostrarSaldos.setFont(new Font("Arial", Font.PLAIN, 14));
        btnMostrarSaldos.setForeground(Color.decode("#2F4858"));
        ImageIcon balanceIcon = new ImageIcon("BancoEPN\\src\\UserInterface\\Resource\\Icon\\balance.png");
        Image balanceImg = balanceIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        btnMostrarSaldos.setIcon(new ImageIcon(balanceImg));

        rightHeader.add(btnNovedades);
        rightHeader.add(btnMostrarSaldos);
        headerPanel.add(rightHeader, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        // ============================
        // Panel Principal con secciones
        // ============================
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.decode("#FFFFFF"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.BOTH;

        // Tarjeta de Saldo Principal (se ubica en la parte superior, abarcando dos columnas)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(new PanelSaldo(), gbc);

        // Tarjeta de Crédito (a la derecha de la Tarjeta de Saldo)
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        mainPanel.add(new PanelTarjetaCredito(), gbc);

        // Actividad Reciente (segunda fila, abarcando dos columnas)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(new PanelActividadReciente(), gbc);

        // Interacciones (segunda fila, columna derecha)
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(new PanelInteracciones(), gbc);

        // Servicios Financieros (sección inferior abarcando todo el ancho)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        mainPanel.add(new PanelServiciosFinancieros(), gbc);

        add(mainPanel, BorderLayout.CENTER);
    }
}

// =====================================================
// Panel Tarjeta de Saldo Principal (Diseño Mejorado)
// =====================================================
class PanelSaldo extends JPanel {
    private boolean balanceVisible = false;
    private JButton btnToggleVisibility;

    public PanelSaldo() {
        // Fondo rosado de acuerdo a la paleta (B68E6D)
        setLayout(new BorderLayout());
        setBackground(Color.decode("#B68E6D"));
        setPreferredSize(new Dimension(300, 250));

        // --- Parte Superior: Ícono de Alcancía ---
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setOpaque(false);
        ImageIcon piggyIcon = new ImageIcon("BancoEPN\\src\\UserInterface\\Resource\\Icon\\PiggyBank.png");
        Image piggyImg = piggyIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel piggyLabel = new JLabel(new ImageIcon(piggyImg));
        topPanel.add(piggyLabel);
        add(topPanel, BorderLayout.NORTH);

        // --- Parte Central: Saldo oculto con ícono de ojo ---
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setOpaque(false);
        JLabel saldoTextLabel = new JLabel("Saldo: ");
        saldoTextLabel.setFont(new Font("Arial", Font.BOLD, 24));
        saldoTextLabel.setForeground(Color.decode("#2F4858"));
        JLabel lblAmount = new JLabel("••••");
        lblAmount.setFont(new Font("Arial", Font.BOLD, 24));
        lblAmount.setForeground(Color.decode("#2F4858"));

        btnToggleVisibility = new JButton();
        ImageIcon eyeIcon = new ImageIcon("BancoEPN\\src\\UserInterface\\Resource\\Icon\\eye.png");
        Image eyeImg = eyeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        btnToggleVisibility.setIcon(new ImageIcon(eyeImg));
        btnToggleVisibility.setContentAreaFilled(false);
        btnToggleVisibility.setBorderPainted(false);
        btnToggleVisibility.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                balanceVisible = !balanceVisible;
                lblAmount.setText(balanceVisible ? "$5000" : "••••");
            }
        });

        centerPanel.add(saldoTextLabel);
        centerPanel.add(lblAmount);
        centerPanel.add(btnToggleVisibility);
        add(centerPanel, BorderLayout.CENTER);

        // --- Parte Inferior: Botones de "Transferir", "Pagar" y "Recargas" ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bottomPanel.setOpaque(false);
        JButton btnTransferir = createActionButton("Transferir", "BancoEPN\\src\\UserInterface\\Resource\\Icon\\transfer.png");
        JButton btnPagar = createActionButton("Pagar", "BancoEPN\\src\\UserInterface\\Resource\\Icon\\pay.png");
        JButton btnRecargas = createActionButton("Recargas", "BancoEPN\\src\\UserInterface\\Resource\\Icon\\recharge.png");
        bottomPanel.add(btnTransferir);
        bottomPanel.add(btnPagar);
        bottomPanel.add(btnRecargas);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JButton createActionButton(String text, String iconPath) {
        JButton button = TransparentButton.createButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));
        button.setForeground(Color.decode("#274156")); // acento (puede interpretarse como azul oscuro)
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        return button;
    }
}

// =====================================================
// Panel Actividad Reciente (Diseño Mejorado)
// =====================================================
class PanelActividadReciente extends JPanel {
    public PanelActividadReciente() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#FFFFFF"));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#D3D3D3"), 1, true),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        String[] columnas = {"Fecha", "Descripción", "Monto"};
        Object[][] datos = {
            {"31 Ene", "Transferencia a banco", "$200"},
            {"25 Ene", "Pago en tienda", "$50"},
            {"15 Ene", "Recarga móvil", "$30"}
        };
        JTable tabla = new JTable(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla.setFont(new Font("Arial", Font.PLAIN, 14));
        tabla.setRowHeight(30);
        // Renderizador para la columna "Monto" (índice 2) en negrita y alineado a la derecha
        tabla.getColumnModel().getColumn(2).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
            JLabel label = new JLabel(value.toString(), SwingConstants.RIGHT);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            label.setForeground(Color.decode("#2F4858"));
            return label;
        });

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnMostrarMas = TransparentButton.createButton("Mostrar Más");
        btnMostrarMas.setFont(new Font("Arial", Font.PLAIN, 14));
        btnMostrarMas.setForeground(Color.decode("#2F4858"));
        btnMostrarMas.setContentAreaFilled(false);
        btnMostrarMas.setFocusPainted(false);
        add(btnMostrarMas, BorderLayout.SOUTH);
    }
}

// =====================================================
// Panel Tarjeta de Crédito (Diseño Mejorado)
// =====================================================
class PanelTarjetaCredito extends JPanel {
    public PanelTarjetaCredito() {
        setLayout(new BorderLayout());
        // Fondo verde para la tarjeta (se eligió un tono de verde)
        setBackground(Color.decode("#4CAF50"));
        setPreferredSize(new Dimension(350, 220));
        setMinimumSize(new Dimension(300, 180));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#D3D3D3"), 1, true),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        // Parte Superior: Título o logotipo (Ejemplo: American Express)
        JLabel titleLabel = new JLabel("American Express", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        // Parte Central: Número de tarjeta (parcialmente oculto) y consumo
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        JLabel cardNumberLabel = new JLabel("**** **** **** 1234");
        cardNumberLabel.setFont(new Font("Arial", Font.BOLD, 18));
        cardNumberLabel.setForeground(Color.WHITE);
        cardNumberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel consumptionLabel = new JLabel("Consumo a la fecha: $300");
        consumptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        consumptionLabel.setForeground(Color.WHITE);
        consumptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(cardNumberLabel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(consumptionLabel);
        add(centerPanel, BorderLayout.CENTER);

        // Parte Inferior: Botón "Pagar tarjeta" con fondo rosado
        JButton btnPagarTarjeta = TransparentButton.createButton("Pagar tarjeta");
        btnPagarTarjeta.setFont(new Font("Arial", Font.BOLD, 14));
        btnPagarTarjeta.setBackground(Color.decode("#B68E6D"));
        btnPagarTarjeta.setForeground(Color.WHITE);
        btnPagarTarjeta.setFocusPainted(false);
        btnPagarTarjeta.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setOpaque(false);
        bottomPanel.add(btnPagarTarjeta);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}

// =====================================================
// Panel Servicios Financieros (Diseño Mejorado)
// =====================================================
class PanelServiciosFinancieros extends JPanel {
    public PanelServiciosFinancieros() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#FFFFFF"));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel serviciosLabel = new JLabel("Servicios Financieros", SwingConstants.LEFT);
        serviciosLabel.setFont(new Font("Arial", Font.BOLD, 18));
        serviciosLabel.setForeground(Color.decode("#2F4858"));
        add(serviciosLabel, BorderLayout.NORTH);

        // Cuatro módulos en cuadrícula
        JPanel gridPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        gridPanel.setOpaque(false);

        gridPanel.add(createServiceModule("Ahorros", "Objetivo: iPad", "BancoEPN\\src\\UserInterface\\Resource\\Icon\\IconAhorro.png"));
        gridPanel.add(createServiceModule("Inversiones", "Pólizas y más", "BancoEPN\\src\\UserInterface\\Resource\\Icon\\IconInversion.png"));
        gridPanel.add(createServiceModule("Seguros", "Protección financiera", "BancoEPN\\src\\UserInterface\\Resource\\Icon\\IconSeguro.png"));
        gridPanel.add(createServiceModule("Préstamos", "Créditos y tasas", "BancoEPN\\src\\UserInterface\\Resource\\Icon\\IconPrestamo.png"));

        add(gridPanel, BorderLayout.CENTER);
    }

    private JPanel createServiceModule(String title, String description, String iconPath) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.decode("#F9F9F9")); // Fondo muy claro
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#D3D3D3"), 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JLabel iconLabel = new JLabel();
        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        iconLabel.setIcon(new ImageIcon(img));
        panel.add(iconLabel, BorderLayout.WEST);

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(Color.decode("#2F4858"));
        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descLabel.setForeground(Color.decode("#2F4858"));
        textPanel.add(titleLabel);
        textPanel.add(descLabel);

        panel.add(textPanel, BorderLayout.CENTER);
        return panel;
    }
}

// =====================================================
// Panel Interacciones (Diseño Mejorado)
// =====================================================
class PanelInteracciones extends JPanel {
    public PanelInteracciones() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#FFFFFF"));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#D3D3D3"), 1, true),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        JLabel interaccionesLabel = new JLabel("Interacciones", SwingConstants.CENTER);
        interaccionesLabel.setFont(new Font("Arial", Font.BOLD, 18));
        interaccionesLabel.setForeground(Color.decode("#2F4858"));
        add(interaccionesLabel, BorderLayout.CENTER);

        JButton btnVerInteracciones = TransparentButton.createButton("Ver Interacciones");
        btnVerInteracciones.setFont(new Font("Arial", Font.PLAIN, 14));
        btnVerInteracciones.setForeground(Color.decode("#2F4858"));
        btnVerInteracciones.setContentAreaFilled(false);
        btnVerInteracciones.setFocusPainted(false);
        add(btnVerInteracciones, BorderLayout.SOUTH);
    }
}
