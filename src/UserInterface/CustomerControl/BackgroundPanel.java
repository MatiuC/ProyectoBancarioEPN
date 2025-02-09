package UserInterface.CustomerControl;

import java.awt.*;
import javax.swing.*;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String filePath) {
        backgroundImage = new ImageIcon(filePath).getImage();
        setLayout(null); // Permite posicionamiento absoluto en los componentes
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
