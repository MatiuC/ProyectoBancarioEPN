package UserInterface.Form.DashBoardCliente;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import DataAccess.DAO.PersonaDAO;
import DataAccess.DTO.PersonaDTO;



public class PanelBienvenida extends JPanel{ 
    public PanelBienvenida(int id) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        setOpaque(false);

        // Ícono de Usuario y Mensaje de Bienvenida
        ImageIcon userIconRaw = new ImageIcon("src/UserInterface/Resource/Icon/IconUser.png");
        Image userIconImg = userIconRaw.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel userIcon = new JLabel(new ImageIcon(userIconImg));

        try {
            PersonaDAO personaDAO = new PersonaDAO();
            PersonaDTO persona = personaDAO.readBy(id);
            JLabel welcomeLabel = new JLabel("Bienvenido " + persona.getNombre()); //+nombre
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20)); //tamaño letras
            welcomeLabel.setForeground(Color.decode("#2F4858"));
            add(userIcon);
            add(welcomeLabel);
        } catch (Exception e) {
            System.err.println("Error al obtener nombre: " + e.getMessage());
        }






        
    }
}
