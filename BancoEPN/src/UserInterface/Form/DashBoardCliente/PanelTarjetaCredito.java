package UserInterface.Form.DashBoardCliente;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.Image;

import java.awt.event.MouseAdapter;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


import java.awt.event.MouseEvent;


public class PanelTarjetaCredito extends JPanel {
    public PanelTarjetaCredito() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#2F4858"));
        setPreferredSize(new Dimension(350, 250));

        // Panel superior para el número de tarjeta
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel cardNumberLabel = new JLabel("**** **** **** 1234");
        cardNumberLabel.setFont(new Font("Arial", Font.BOLD, 18));
        cardNumberLabel.setForeground(Color.BLACK);  // Aseguramos que el texto sea visible
        topPanel.add(cardNumberLabel);

        // Panel para mostrar la tarjeta
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        // Cargar la imagen de la tarjeta desde el archivo y ajustar su tamaño
        ImageIcon tarjetaImage = new ImageIcon("src\\UserInterface\\Resource\\Icon\\Tarjeta.png");  // Ruta de la imagen
        Image image = tarjetaImage.getImage(); // Convertir la imagen a objeto Image
        Image scaledImage = image.getScaledInstance(300, 260, Image.SCALE_SMOOTH); // Redimensionar imagen
        tarjetaImage = new ImageIcon(scaledImage); // Volver a convertir a ImageIcon

        // Crear JLabel con la imagen ajustada y centrarla
        JLabel cardLabel = new JLabel(tarjetaImage);
        cardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(cardLabel, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));  // Añadimos espacio entre botones

        // Botón "Ver tarjetas" con color de fondo azul
        JButton viewButton = new JButton("Ver tarjetas");
        viewButton.setFont(new Font("Arial", Font.BOLD, 14));
        viewButton.setBackground(Color.decode("#D3D3D3"));
        viewButton.setForeground(Color.BLACK);  // Color del texto del botón
        viewButton.setFocusPainted(false);
        viewButton.setPreferredSize(new Dimension(150, 40));
        viewButton.setContentAreaFilled(true);  // Rellenar el área con color
        viewButton.setOpaque(true);  // Hace que el botón sea opaco

        // Efecto de hover para "Ver tarjetas"
        viewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                viewButton.setBackground(Color.decode("#2F4858")); // Cambio de color al pasar el mouse
            }
            @Override
            public void mouseExited(MouseEvent e) {
                viewButton.setBackground(Color.decode("#FFFFFF")); // Vuelve al color original
            }
        });

        // Botón "Pagar tarjeta" con color de fondo naranja
        JButton payButton = new JButton("Pagar Tarjeta");
        payButton.setFont(new Font("Arial", Font.BOLD, 16));
        payButton.setBackground(Color.decode("#D3D3D3"));
        payButton.setForeground(Color.BLACK);  // Color del texto del botón
        payButton.setFocusPainted(false);
        payButton.setPreferredSize(new Dimension(150, 40));
        payButton.setContentAreaFilled(true);  // Rellenar el área con color
        payButton.setOpaque(true);  // Hace que el botón sea opaco

        // Efecto de hover para "Pagar tarjeta"
        payButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                payButton.setBackground(Color.decode("#2F4858")); // Cambio de color al pasar el mouse
            }
            @Override
            public void mouseExited(MouseEvent e) {
                payButton.setBackground(Color.decode("#FFFFFF")); // Vuelve al color original
            }
        });

        // Añadir los botones al panel de botones
        buttonPanel.add(viewButton);
        buttonPanel.add(payButton);

        // Añadiendo los componentes al panel principal
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
