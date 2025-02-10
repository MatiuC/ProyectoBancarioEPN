import javax.swing.*;
import com.formdev.flatlaf.*;
import UserInterface.Form.RegistroClientesForm;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            SwingUtilities.invokeLater(() -> {
                try {
                    RegistroClientesForm registroFrame = new RegistroClientesForm();
                    registroFrame.setVisible(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al iniciar la aplicación: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            System.out.println("Error al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}