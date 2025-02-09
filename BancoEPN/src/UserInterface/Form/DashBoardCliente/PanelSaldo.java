package UserInterface.Form.DashBoardCliente;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

import UserInterface.CustomerControl.RoundedButton;

public class PanelSaldo extends JPanel {
    private boolean balanceVisible = false; // Control de visibilidad del saldo
    private JLabel saldoLabel;
    private JButton btnToggleVisibility;

    //Llamar a la cuenta del usuario logueado y mostrar su saldo
    public PanelSaldo() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#274156")); 
        setPreferredSize(new Dimension(350, 250)); // Ancho 350, Alto 250

        // Crear el Compound Border con LineBorder (para el borde) y EmptyBorder (para el espaciado interno)
        LineBorder lineBorder = new LineBorder(Color.decode("#D3D3D3"), 2); // Borde de 2px
        EmptyBorder emptyBorder = new EmptyBorder(10, 10, 10, 10); // Espaciado interno
        CompoundBorder compoundBorder = new CompoundBorder(lineBorder, emptyBorder);

        // Aplicar el Compound Border con bordes redondeados al panel
        setBorder(compoundBorder);

        // Panel principal para todo
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Alineamos todo en columna
        mainPanel.setOpaque(false);

        // Añadir el saldo con el icono "eye" a la derecha
        JPanel saldoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Para alinearlo a la izquierda
        saldoPanel.setOpaque(false);

        saldoLabel = new JLabel("Saldo: $*****");
        saldoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        saldoLabel.setForeground(Color.decode("#FFFFFF"));
        saldoPanel.add(saldoLabel);

        // Añadir el botón "eye" al lado derecho del saldo
        btnToggleVisibility = new JButton();
        ImageIcon eyeIcon = new ImageIcon("src/UserInterface/Resource/Icon/IconEye.png");
        Image eyeImg = eyeIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        btnToggleVisibility.setIcon(new ImageIcon(eyeImg));
        btnToggleVisibility.setContentAreaFilled(false);
        btnToggleVisibility.setBorderPainted(false);
        btnToggleVisibility.setFocusPainted(false);
        saldoPanel.add(btnToggleVisibility);

        btnToggleVisibility.addActionListener(e -> toggleSaldoVisibility()); 

        
        add(saldoPanel, BorderLayout.NORTH);

        // Crear el panel para los botones
        JPanel bottomButtonsPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        bottomButtonsPanel.setOpaque(false);

        // Crear los botones con iconos encima del texto (sin la clase Button)
        JButton btnTransferir = new JButton("Transferir");
        JButton btnPagar = new JButton("Pagar");
        JButton btnRecargar = new JButton("Recargar");

        // Establecer el icono encima del texto para cada botón
        setButtonIcon(btnTransferir, "src/UserInterface/Resource/Icon/IconTransferir.png");
        setButtonIcon(btnPagar, "src/UserInterface/Resource/Icon/IconPagar.png");
        setButtonIcon(btnRecargar, "src/UserInterface/Resource/Icon/IconRecargar.png");

        // Añadir los botones al panel inferior
        bottomButtonsPanel.add(btnTransferir);
        bottomButtonsPanel.add(btnPagar);
        bottomButtonsPanel.add(btnRecargar);
        add(bottomButtonsPanel, BorderLayout.SOUTH);

        // Crear el icono de piggy (alcancía)
        JPanel piggyPanel = new JPanel();
        piggyPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        piggyPanel.setOpaque(false); 

        ImageIcon piggyIcon = new ImageIcon("src/UserInterface/Resource/Icon/Iconpiggy.png");
        Image piggyImg = piggyIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH); // Tamaño del icono
        JLabel piggyLabel = new JLabel(new ImageIcon(piggyImg));
        piggyPanel.add(piggyLabel);

        // Establecer la posición del icono piggy encima del saldo y los botones
        piggyPanel.setBounds(115, 100, 120, 120);  // Ajusta la posición del icono de piggy
        add(piggyPanel, BorderLayout.CENTER);  
    }

    // Método para alternar la visibilidad del saldo
    private void toggleSaldoVisibility() {
        balanceVisible = !balanceVisible;
        saldoLabel.setText(balanceVisible ? "Saldo: $5000" : "Saldo: $*****");
    }

    // Método para agregar un icono encima del texto en los botones
    private void setButtonIcon(JButton button, String iconPath) {
        // Cargar el icono y ajustarlo al tamaño del botón
        ImageIcon icon = new ImageIcon(iconPath);
        Image iconImg = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH); // Tamaño ajustado
        button.setIcon(new ImageIcon(iconImg));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }
}
