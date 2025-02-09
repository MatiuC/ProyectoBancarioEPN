package UserInterface.Form;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.*;
import UserInterface.CustomerControl.CustomTransactionTable;

public class ClienteTransacciones extends JPanel {
    private CustomTransactionTable tablaTransacciones;
    
    public ClienteTransacciones() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        //Llamar a la tabla de transacciones filtrada por el usuario el id de la persona que esta logueado
        
        // Crear la tabla personalizada
        tablaTransacciones = new CustomTransactionTable();
        tablaTransacciones.setPreferredSize(new Dimension(800, 400));
        
        // Panel contenedor con padding
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBackground(Color.WHITE);
        contenedor.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contenedor.add(tablaTransacciones, BorderLayout.CENTER);
        
        // Agregar el contenedor al panel principal
        add(contenedor, BorderLayout.CENTER);
        
        // Asegurar que el panel tenga un tamaño mínimo
        setPreferredSize(new Dimension(900, 500));
    }
}