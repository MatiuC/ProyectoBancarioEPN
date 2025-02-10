import java.sql.SQLException;

import BussinesLogic.Entities.Tarjeta.RegistrarTarjeta;

public class App {
    public static void main(String[] args) throws SQLException {
        // try {
            // // Crear y mostrar la ventana de login
            // LoginPanel loginFrame = new LoginPanel();
            // loginFrame.setVisible(true);
        // } catch (Exception e) {
            // System.out.println("Error al iniciar la aplicación: " + e.getMessage());
            // e.printStackTrace();
        // }


        // Crear una instancia de RegistrarTarjeta
        RegistrarTarjeta registrarTarjeta = new RegistrarTarjeta();

        // Crear y registrar la tarjeta
        registrarTarjeta.crearTarjeta(1,1,1,1);
        
        // Enviar correo de confirmación (opcional)
        registrarTarjeta.enviarCorreoConfirmacion("usuario@correo.com");

       
        
    }
}