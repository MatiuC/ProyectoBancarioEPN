package UserInterface.Form.DashBoardCliente;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BussinesLogic.BLFactory;
import DataAccess.DTO.PersonaDTO;

public class PanelBienvenida extends JPanel{ 
    public PanelBienvenida() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        setOpaque(false);

        //private BLFactory<PersonaDTO> personaBL = new BLFactory<PersonaDTO>(conn -> new PersonaDAO(conn));

        //Mostar al usuario el nombre de la persona que esta logueado

        //public String nombre;

        // Ícono de Usuario y Mensaje de Bienvenida
        ImageIcon userIconRaw = new ImageIcon("src/UserInterface/Resource/Icon/IconUser.png");
        Image userIconImg = userIconRaw.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);//tamaño iconuser
        JLabel userIcon = new JLabel(new ImageIcon(userIconImg));
        JLabel welcomeLabel = new JLabel("Bienvenido" );
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20)); //tamaño letras
        welcomeLabel.setForeground(Color.decode("#2F4858"));

        add(userIcon);
        add(welcomeLabel);
        
    }
}
