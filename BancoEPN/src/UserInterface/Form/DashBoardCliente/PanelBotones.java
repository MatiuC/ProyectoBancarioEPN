package UserInterface.Form.DashBoardCliente;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import UserInterface.CustomerControl.TransparentButton;


public class PanelBotones extends JPanel{
    public PanelBotones() {
        setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        setOpaque(false);

        // Crear los botones con iconos y texto
        JButton btnConoceLoNuevo = TransparentButton.createButton("Conoce lo nuevo", "ProyectoBancarioEPN/BancoEPN/src/UserInterface/Resource/Icon/IconNew.png");
        JButton btnMostrarSaldos = TransparentButton.createButton("Mostrar saldos", "ProyectoBancarioEPN/BancoEPN/src/UserInterface/Resource/Icon/IconEye.png");

        add(btnConoceLoNuevo);
        add(btnMostrarSaldos);
    }
}
