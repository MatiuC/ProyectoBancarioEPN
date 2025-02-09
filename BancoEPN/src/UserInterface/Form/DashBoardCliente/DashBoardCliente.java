package UserInterface.Form.DashBoardCliente;
import javax.swing.*;
import java.awt.*;


public class DashBoardCliente extends JFrame {
    public DashBoardCliente() {
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
    
    add(new Panel(), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DashBoardCliente frame = new DashBoardCliente();
            frame.setVisible(true);
        });
    }
}
  

