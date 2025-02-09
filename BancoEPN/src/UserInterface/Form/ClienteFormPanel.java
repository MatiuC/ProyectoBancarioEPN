package UserInterface.Form;

import UserInterface.CustomerControl.RoundedTextField;
import UserInterface.CustomerControl.RoundedButton;
import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class ClienteFormPanel extends JPanel {

    private RoundedTextField txtNombre, txtApellido, txtCelular, txtCorreo, txtDireccion;
    private JDateChooser dateChooser;
    private JComboBox<String> comboSexo;
    private MainForm parentFrame;

    public ClienteFormPanel(MainForm parentFrame) {
        this.parentFrame = parentFrame;
        
        setLayout(new GridBagLayout());
        setBackground(new Color(0xD3D3D3)); // Fondo gris claro
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Registro de Clientes");
        lblTitulo.setFont(new Font("Montserrat", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(0x003459));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        add(createStyledLabel("Nombre:"), gbc);
        txtNombre = new RoundedTextField(25);
        gbc.gridx = 1;
        add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createStyledLabel("Apellido:"), gbc);
        txtApellido = new RoundedTextField(25);
        gbc.gridx = 1;
        add(txtApellido, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createStyledLabel("Número de Celular:"), gbc);
        txtCelular = new RoundedTextField(25);
        gbc.gridx = 1;
        add(txtCelular, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createStyledLabel("Fecha de Nacimiento:"), gbc);
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setPreferredSize(new Dimension(230, 40));
        gbc.gridx = 1;
        add(dateChooser, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createStyledLabel("Sexo:"), gbc);
        comboSexo = new JComboBox<>(new String[]{"Seleccionar", "Masculino", "Femenino"});
        comboSexo.setFont(new Font("Montserrat", Font.PLAIN, 16));
        comboSexo.setPreferredSize(new Dimension(230, 40));
        gbc.gridx = 1;
        add(comboSexo, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createStyledLabel("Correo:"), gbc);
        txtCorreo = new RoundedTextField(25);
        gbc.gridx = 1;
        add(txtCorreo, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createStyledLabel("Dirección:"), gbc);
        txtDireccion = new RoundedTextField(25);
        gbc.gridx = 1;
        add(txtDireccion, gbc);

        // Botones redondeados
        gbc.gridx = 0;
        gbc.gridy++;
        RoundedButton btnRegistrar = new RoundedButton("Registrar Cliente");
        RoundedButton btnRefrescar = new RoundedButton("Refrescar");
        RoundedButton btnVolver = new RoundedButton("Volver");

        btnRegistrar.addActionListener(e -> registrarCliente());
        btnRefrescar.addActionListener(e -> refrescarCampos());
        btnVolver.addActionListener(e -> parentFrame.cambiarVista("Inicio"));

        gbc.gridwidth = 1;
        add(btnRegistrar, gbc);
        gbc.gridx = 1;
        add(btnRefrescar, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(btnVolver, gbc);
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Montserrat", Font.BOLD, 16));
        label.setForeground(new Color(0x003459)); // Azul prusiano
        return label;
    }

    private void registrarCliente() {
        if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtCelular.getText().isEmpty() ||
                dateChooser.getDate() == null || comboSexo.getSelectedIndex() == 0 || 
                txtCorreo.getText().isEmpty() || txtDireccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String fechaNacimiento = dateFormat.format(dateChooser.getDate());
            String sexo = (String) comboSexo.getSelectedItem();

            JOptionPane.showMessageDialog(this, "Cliente registrado con éxito.\n"
                    + "Nombre: " + txtNombre.getText() + "\n"
                    + "Apellido: " + txtApellido.getText() + "\n"
                    + "Celular: " + txtCelular.getText() + "\n"
                    + "Fecha de Nacimiento: " + fechaNacimiento + "\n"
                    + "Sexo: " + sexo + "\n"
                    + "Correo: " + txtCorreo.getText() + "\n"
                    + "Dirección: " + txtDireccion.getText(),
                    "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void refrescarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtCelular.setText("");
        dateChooser.setDate(null);
        comboSexo.setSelectedIndex(0);
        txtCorreo.setText("");
        txtDireccion.setText("");
    }
}
