package UserInterface.Form;

import javax.swing.*;
import java.awt.*;

public class InicioPanel extends JPanel {

    public InicioPanel() {
        setLayout(new BorderLayout());
        ImageIcon imageIcon = new ImageIcon("src\\UserInterface\\Resource\\Img\\InicioBank.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        add(imageLabel, BorderLayout.CENTER);
    }
}
