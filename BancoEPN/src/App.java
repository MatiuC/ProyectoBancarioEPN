package UserInterface;

import UserInterface.Form.DashBoardCliente.TransferenciaPanel;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int idUsuario = 1; // Simulación de un usuario con ID 1 (puedes cambiarlo según el usuario real)
            TransferenciaPanel transferenciaPanel = new TransferenciaPanel(idUsuario);
            transferenciaPanel.setVisible(true);
        });
    }
}
