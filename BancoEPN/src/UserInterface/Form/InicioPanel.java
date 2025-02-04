package UserInterface.Form;

import java.awt.*;
import javax.swing.*;

public class InicioPanel extends JPanel {

    public InicioPanel() {
        setLayout(new BorderLayout());
        ImageIcon imageIcon = new ImageIcon("src\\UserInterface\\Resource\\Img\\InicioBank.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        add(imageLabel, BorderLayout.CENTER);
    }
}
