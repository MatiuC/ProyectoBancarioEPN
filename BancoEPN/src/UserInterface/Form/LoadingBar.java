package UserInterface.Form;

import java.awt.*;
import javax.swing.*;

class LoadingBar extends JPanel {
    private int progress = 0; // Valor del 0 al 100
    private final Image moneyBagImage; // Imagen escalada

    public LoadingBar() {
        setPreferredSize(new Dimension(720, 60));
        setBackground(Color.black);

        // Cargar la imagen original y escalarla
        ImageIcon originalIcon = new ImageIcon("src/UserInterface/Resource/Img/giphy.gif");

        // Escalar la imagen (ajusta los valores para el tamaño deseado)
        moneyBagImage = originalIcon.getImage().getScaledInstance(5, 5, Image.SCALE_SMOOTH);
    }

    /**
     * Método para actualizar el progreso (0 - 100).
     */
    public void setProgress(int progress) {
        this.progress = Math.min(100, Math.max(0, progress)); // Limita entre 0 y 100
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dimensiones para la barra de progreso
        int barWidth = getWidth() - 100;   // margen de 50px a cada lado aprox.
        int barHeight = 20;
        int x = (getWidth() - barWidth) / 2;
        int y = (getHeight() - barHeight) / 2;

        // Dibuja barra de fondo
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRoundRect(x, y, barWidth, barHeight, 10, 10);

        // Dibuja barra de progreso en verde
        int fillWidth = (int) (barWidth * (progress / 100.0));
        g2d.setColor(Color.GRAY);
        g2d.fillRoundRect(x, y, fillWidth, barHeight, 10, 10);

        // Coordenadas del GIF: al final del tramo verde
        int gifX = x + fillWidth - 25; // Ajusta la posición horizontal (mitad del tamaño)
        int gifY = y - 45; // Ajusta la posición para que no se cubra con la barra

        // Dibujar la imagen escalada
        g2d.drawImage(moneyBagImage, gifX, gifY, this);

        g2d.dispose();
    }
}
