
package UserInterface.Form;
import javax.swing.*;
import java.awt.*;

public class MainContentPanel extends JPanel {

    public MainContentPanel() {
        setLayout(new BorderLayout());

        // Panel para el portafolio
        JPanel portfolioPanel = new JPanel();
        portfolioPanel.setLayout(new BoxLayout(portfolioPanel, BoxLayout.Y_AXIS));
        portfolioPanel.setBackground(Color.WHITE);
        portfolioPanel.setPreferredSize(new Dimension(500, 100));

        JLabel portfolioLabel = new JLabel("Portfolio");
        portfolioLabel.setFont(new Font("Arial", Font.BOLD, 24));
        portfolioPanel.add(portfolioLabel);

        // Muestra el balance de portafolio
        JLabel balanceLabel = new JLabel("$17,643.41");
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 32));
        portfolioPanel.add(balanceLabel);

        // Panel para mostrar los activos
        JPanel assetsPanel = new JPanel();
        assetsPanel.setLayout(new GridLayout(1, 3));  // Tres activos a la vez
        assetsPanel.setBackground(Color.WHITE);
        assetsPanel.setPreferredSize(new Dimension(500, 150));

        // Crear las tarjetas para los activos
        assetsPanel.add(createCard("BTC", "1.25", "$29,840.04", Color.PINK));
        assetsPanel.add(createCard("LTC", "0.32", "$2,940.04", Color.GREEN));
        assetsPanel.add(createCard("ETH", "1.25", "$29,840.04", Color.YELLOW));

        // Crear el gráfico de portafolio (simulado aquí como un panel)
        JPanel chartPanel = new JPanel();
        chartPanel.setBackground(Color.GRAY);
        chartPanel.setPreferredSize(new Dimension(700, 300));

        // Agregar todo a la ventana principal
        add(portfolioPanel, BorderLayout.NORTH);
        add(assetsPanel, BorderLayout.CENTER);
        add(chartPanel, BorderLayout.SOUTH);
    }

    private JPanel createCard(String title, String amount, String value, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(color);
        card.setPreferredSize(new Dimension(150, 100));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);

        JLabel amountLabel = new JLabel(amount);
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        amountLabel.setForeground(Color.WHITE);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        valueLabel.setForeground(Color.WHITE);

        card.add(titleLabel);
        card.add(amountLabel);
        card.add(valueLabel);

        return card;
    }
}
