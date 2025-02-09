import UserInterface.Form.LoginPanel;

public class App {
    public static void main(String[] args) {
        try {
            // Crear y mostrar la ventana de login
            LoginPanel loginFrame = new LoginPanel();
            loginFrame.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}