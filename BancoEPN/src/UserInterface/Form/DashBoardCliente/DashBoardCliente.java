package UserInterface.Form.DashBoardCliente;
import javax.swing.*;

import UserInterface.Form.LoginPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Capturar el id de la persona que esta logueado y mostrar su nombre 

//Llamar la cuenta del usuario logueado y mostrarla en el dashboard con su saldo

//Llamar a la tabla de transacciones filtrada por el usuario el id de la persona que esta logueado

//Llamar a la logica de ver las tarjetas del usuario logueado

public class DashBoardCliente extends JFrame {
 public DashBoardCliente(int id) {
        setTitle("Dashboard Cliente");

        // Obtener el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        // Establecer un tamaño grande pero con margen (Ejemplo: 90% del ancho y 85% del alto)
        int width = (int) (screenSize.width * 0.9);
        int height = (int) (screenSize.height * 0.85);
        
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        setLayout(new BorderLayout());
        
        // Agregar el panel de contenido
        add(new Panel(id), BorderLayout.CENTER);

        // Crear el botón de Cerrar sesión
        JButton logoutButton = new JButton("Cerrar Sesión");
        logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        logoutButton.setForeground(new Color(0xD3D3D3));
        logoutButton.setBackground(new Color(47, 72, 88)); // Color similar al CHARCOAL
        logoutButton.setPreferredSize(new Dimension(150, 40));
        
        // Añadir acción al botón
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana actual y regresar al LoginPanel
                dispose();  // Cierra la ventana actual
                LoginPanel loginPanel = new LoginPanel();  // Crear la ventana de login
                loginPanel.setVisible(true);  // Mostrar el panel de inicio de sesión
            }
        });

        // Añadir el botón en la parte superior
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(new Color(0xD3D3D3));
        topPanel.add(logoutButton, BorderLayout.EAST);  // Coloca el botón a la derecha

        // Añadir el panel superior al layout
        add(topPanel, BorderLayout.NORTH);
    }
  
// public static void main(String[] args) {
//    // Establecer el ID del cliente (puede ser dinámico según el usuario)
// int clientId = 123; // Cambia este valor al ID del cliente que desees
    
//   //Crear e iniciar el Dashboard del Cliente
//  DashBoardCliente dashBoard = new DashBoardCliente(clientId);
//  dashBoard.setVisible(true); // Hacer visible el Dashboard
// }
    

}
  

