import UserInterface.Form.LoginPanel;
import BussinesLogic.Entities.BancoLogic.ValidarTransaccion;
import DataAccess.DAO.PersonaDAO;
import DataAccess.DTO.PersonaDTO;

import javax.swing.*;
import com.formdev.flatlaf.*;
import UserInterface.Form.RegistroClientesForm;

public class App {
    public static void main(String[] args) {

        try {
            PersonaDAO personaDAO = new PersonaDAO();
            try {
                for (PersonaDTO persona : personaDAO.readAll()) {
                    System.out.println(persona.toString());
                }
            } catch (Exception e) {
                System.out.println("Error al obtener el rol: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println("Error al obtener el rol: " + e.getMessage());
            e.printStackTrace();
        }

        try {

            // Crear y mostrar la ventana de login

            LoginPanel loginFrame = new LoginPanel();
            loginFrame.setVisible(true);

            UIManager.setLookAndFeel(new FlatLightLaf());
            SwingUtilities.invokeLater(() -> {
                try {
                    RegistroClientesForm registroFrame = new RegistroClientesForm();
                    registroFrame.setVisible(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al iniciar la aplicación: " + e.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            System.out.println("Error al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }

        // try {
        // ValidarTransaccion validarTransaccion = new ValidarTransaccion();
        // String resultado = validarTransaccion.procesarTransaccion(1, 3, 100.0,
        // "Transferencia", "Transferencia de 100 dolares", "juan@gmail.com");
        // System.out.println(resultado);
        // } catch (Exception e) {
        // System.out.println("Error al procesar la transacción: " + e.getMessage());
        // e.printStackTrace();
        // }

    }
}