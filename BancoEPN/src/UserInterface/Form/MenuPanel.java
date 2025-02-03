package UserInterface.Form;

import UserInterface.CustomerControl.*; // Adjust the package path as necessary

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private MainForm parentFrame;

    public MenuPanel(MainForm parentFrame) {
        this.parentFrame = parentFrame;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Organiza los elementos en vertical
        setPreferredSize(new Dimension(200, 80));
        setBackground(new Color(0xEE6C4D)); // Fondo Burnt Sienna

        // Cargar el logo en la esquina superior izquierda
        ImageIcon icon = new ImageIcon("ProyectoBancarioEPN\\BancoEPN\\src\\UserInterface\\Resource\\Img\\LogoEPN.jpg"); // Cambia la ruta si es necesario
        Image scaledImage = icon.getImage().getScaledInstance(120, 130, Image.SCALE_SMOOTH); // Escalar la imagen
        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el logo en el panel

        // Espacio entre el logo y los botones
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(logoLabel);
        add(Box.createRigidArea(new Dimension(0, 30)));

        // Botones de navegación
        RoundedButton inicioButton = new RoundedButton("Inicio");
        RoundedButton registroButton = new RoundedButton("Registro");

        inicioButton.addActionListener(e -> parentFrame.cambiarVista("Inicio"));
        registroButton.addActionListener(e -> parentFrame.cambiarVista("RegistroClientes"));

        inicioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registroButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(inicioButton);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(registroButton);
    }
}
