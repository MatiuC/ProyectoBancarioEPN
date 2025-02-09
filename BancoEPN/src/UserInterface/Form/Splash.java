package UserInterface.Form;

import UserInterface.CustomerControl.BackgroundPanel;
import java.awt.*;
import javax.swing.*;

public class Splash extends JWindow {

    public Splash() {
        // Crear un JLayeredPane para manejar las capas
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(720, 480));

        // Imagen de fondo (BackgroundPanel)
        BackgroundPanel panel = new BackgroundPanel("ProyectoBancarioEPN\\BancoEPN\\src\\UserInterface\\Resource\\Img\\GatoBank.png.jpeg");
        panel.setBounds(0, 0, 720, 480);

        // Barra de carga personalizada
        LoadingBar loadingBar = new LoadingBar();
        loadingBar.setBounds(100, 420, 520, 30); // Ajusta la posición más abajo

        // GIF de la bolsa de dinero
        ImageIcon imageIcon = new ImageIcon("src/UserInterface/Resource/Img/Dinero.gif");
        Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(image);
        
        JLabel moneyBagLabel = new JLabel(scaledIcon);
        moneyBagLabel.setBounds(100, 380, 100, 100);

        // Agregar componentes en capas
        layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(loadingBar, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(moneyBagLabel, JLayeredPane.POPUP_LAYER);

        setContentPane(layeredPane);
        setSize(720, 480);
        setLocationRelativeTo(null);
        setVisible(true);

        // Simular carga de la aplicación del 0 al 100%
        for (int i = 0; i <= 100; i++) {
            loadingBar.setProgress(i);
            moneyBagLabel.setLocation(100 + (int) (4.2 * i), 380); // Mueve el GIF horizontalmente
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Cerrar splash y mostrar la aplicación principal
        dispose(); // Cierra la ventana splash

        // Abrir MainForm
        SwingUtilities.invokeLater(() -> {
            MainForm mainForm = new MainForm();
            mainForm.setVisible(true);
        });
    }

    public static void main(String[] args) {
        new Splash();
    }
}
