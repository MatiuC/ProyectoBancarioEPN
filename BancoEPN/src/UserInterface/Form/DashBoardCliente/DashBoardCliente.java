package UserInterface.Form.DashBoardCliente;
import javax.swing.*;
import java.awt.*;

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
    
    add(new Panel(id), BorderLayout.CENTER);
    }


}
  

