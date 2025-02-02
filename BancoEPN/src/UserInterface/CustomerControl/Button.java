package UserInterface.CustomerControl;


import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Button extends JButton {
   // Constructor principal con parámetros básicos
    public Button(String text) {
        this(text, new Color(52, 152, 219), new Color(255, 255, 255)); // Azul de fondo, blanco de texto
    }
    
    // Constructor adicional con colores personalizables
    public Button(String text, Color backgroundColor, Color textColor) {
        super(text);
        setFont(new Font("Arial", Font.BOLD, 14));
        setForeground(textColor); // Cambia el color del texto
        setBackground(backgroundColor); // Cambia el color de fondo
        setFocusPainted(false); // Elimina el borde cuando el botón tiene foco
        setBorderPainted(false); // Elimina el borde por defecto
        setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor cuando pasa sobre el botón
    }
    
    // Método para cambiar el color de fondo
    public void setCustomBackgroundColor(Color color) {
        setBackground(color);
    }
    
    // Método para cambiar el color del texto
    public void setCustomTextColor(Color color) {
        setForeground(color);
    }
    
    // Método para cambiar el texto del botón
    public void setCustomText(String text) {
        setText(text);
    }
    
    // Método para agregar un icono al botón
    public void setCustomIcon(Icon icon) {
        setIcon(icon);
    }

    // Método para agregar una acción personalizada
    public void addActionListener(ActionListener listener) {
        super.addActionListener(listener);
    }
}
