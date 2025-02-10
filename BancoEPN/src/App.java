import javax.swing.*;
import com.formdev.flatlaf.FlatLightLaf;
import UserInterface.Form.RegistroClientesForm;

public class App {
    public static void main(String[] args) {
        try {
            // Configurar el Look and Feel
            UIManager.setLookAndFeel(new FlatLightLaf());
            SwingUtilities.invokeLater(() -> {
                try {
                    // Crear y mostrar la ventana de registro de clientes
                    RegistroClientesForm registroFrame = new RegistroClientesForm();
                    registroFrame.getContentPane().add(registroFrame.getFormularioPanel());
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