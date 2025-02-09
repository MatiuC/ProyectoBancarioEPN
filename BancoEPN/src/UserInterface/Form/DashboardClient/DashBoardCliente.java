package UserInterface.Form.DashboardClient;
import javax.swing.*;
import java.awt.*;


public class DashBoardCliente extends JFrame {
    public DashBoardCliente() {
        setTitle("Dashboard Cliente");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
  

