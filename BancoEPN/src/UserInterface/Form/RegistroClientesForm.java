package UserInterface.Form;

import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroClientesForm {

    private JTextField fieldCedula, fieldNombre, fieldApellido, fieldCiudad, fieldEdad, fieldDireccion, fieldCorreo, fieldTelefono;
    private JComboBox<String> comboSexo, comboEstadoCivil;
    private JDateChooser dateChooserFechaNacimiento;

    // Constructor para inicializar los componentes sin mostrar una ventana
    public RegistroClientesForm() {
        // No creamos un JFrame, solo retornamos el panel
    }

    // Método para crear el panel del formulario
    public JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(11, 2, 10, 10)); // 11 filas, 2 columnas
        panel.setBackground(Color.white); // Fondo gris claro

        // Establecer fuentes y colores para las etiquetas
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Color labelColor = new Color(0, 51, 102); // Azul oscuro

        JLabel labelCedula = new JLabel("Cédula:");
        labelCedula.setFont(labelFont);
        labelCedula.setForeground(labelColor);
        fieldCedula = new JTextField(20);

        JLabel labelNombre = new JLabel("Nombre:");
        labelNombre.setFont(labelFont);
        labelNombre.setForeground(labelColor);
        fieldNombre = new JTextField(20);

        JLabel labelApellido = new JLabel("Apellido:");
        labelApellido.setFont(labelFont);
        labelApellido.setForeground(labelColor);
        fieldApellido = new JTextField(20);

        JLabel labelSexo = new JLabel("Sexo:");
        labelSexo.setFont(labelFont);
        labelSexo.setForeground(labelColor);
        comboSexo = new JComboBox<>(new String[] {"Masculino", "Femenino", "Otro"});

        JLabel labelEstadoCivil = new JLabel("Estado Civil:");
        labelEstadoCivil.setFont(labelFont);
        labelEstadoCivil.setForeground(labelColor);
        comboEstadoCivil = new JComboBox<>(new String[] {"Soltero", "Casado", "Divorciado", "Viudo"});

        JLabel labelCiudad = new JLabel("Ciudad:");
        labelCiudad.setFont(labelFont);
        labelCiudad.setForeground(labelColor);
        fieldCiudad = new JTextField(20);

        JLabel labelEdad = new JLabel("Edad:");
        labelEdad.setFont(labelFont);
        labelEdad.setForeground(labelColor);
        fieldEdad = new JTextField(20);

        JLabel labelFechaNacimiento = new JLabel("Fecha de Nacimiento:");
        labelFechaNacimiento.setFont(labelFont);
        labelFechaNacimiento.setForeground(labelColor);
        dateChooserFechaNacimiento = new JDateChooser();

        JLabel labelDireccion = new JLabel("Dirección:");
        labelDireccion.setFont(labelFont);
        labelDireccion.setForeground(labelColor);
        fieldDireccion = new JTextField(20);

        JLabel labelCorreo = new JLabel("Correo:");
        labelCorreo.setFont(labelFont);
        labelCorreo.setForeground(labelColor);
        fieldCorreo = new JTextField(20);

        JLabel labelTelefono = new JLabel("Teléfono:");
        labelTelefono.setFont(labelFont);
        labelTelefono.setForeground(labelColor);
        fieldTelefono = new JTextField(20);

        panel.add(labelCedula);
        panel.add(fieldCedula);
        panel.add(labelNombre);
        panel.add(fieldNombre);
        panel.add(labelApellido);
        panel.add(fieldApellido);
        panel.add(labelSexo);
        panel.add(comboSexo);
        panel.add(labelEstadoCivil);
        panel.add(comboEstadoCivil);
        panel.add(labelCiudad);
        panel.add(fieldCiudad);
        panel.add(labelEdad);
        panel.add(fieldEdad);
        panel.add(labelFechaNacimiento);
        panel.add(dateChooserFechaNacimiento);
        panel.add(labelDireccion);
        panel.add(fieldDireccion);
        panel.add(labelCorreo);
        panel.add(fieldCorreo);
        panel.add(labelTelefono);
        panel.add(fieldTelefono);

        return panel;
    }

    // Método para crear el panel de botones
    public JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panel.setBackground(Color.WHITE); // Fondo gris claro

        // Crear botones con colores personalizados
        JButton buttonGuardar = new JButton("Guardar");
        buttonGuardar.setBackground(new Color(0, 102, 204)); // Azul
        buttonGuardar.setForeground(Color.WHITE);
        buttonGuardar.setFont(new Font("Arial", Font.BOLD, 14));

        // Acción para el botón "Guardar"
        buttonGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isFormValid()) {
                    JOptionPane.showMessageDialog(null, "¡Registro guardado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Faltan llenar campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonNuevoRegistro = new JButton("Nuevo Registro");
        buttonNuevoRegistro.setBackground(new Color(204, 0, 0)); // Rojo
        buttonNuevoRegistro.setForeground(Color.WHITE);
        buttonNuevoRegistro.setFont(new Font("Arial", Font.BOLD, 14));

        // Acción para el botón "Nuevo Registro"
        buttonNuevoRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearForm(); // Limpiar los campos del formulario
            }
        });

        panel.add(buttonGuardar);
        panel.add(buttonNuevoRegistro);

        return panel;
    }

    // Método para validar que todos los campos están llenos
    private boolean isFormValid() {
        return !fieldCedula.getText().isEmpty() && !fieldNombre.getText().isEmpty() && !fieldApellido.getText().isEmpty()
               && comboSexo.getSelectedItem() != null && comboEstadoCivil.getSelectedItem() != null
               && !fieldCiudad.getText().isEmpty() && !fieldEdad.getText().isEmpty()
               && dateChooserFechaNacimiento.getDate() != null && !fieldDireccion.getText().isEmpty()
               && !fieldCorreo.getText().isEmpty() && !fieldTelefono.getText().isEmpty();
    }

    // Método para limpiar los campos del formulario
    private void clearForm() {
        fieldCedula.setText("");
        fieldNombre.setText("");
        fieldApellido.setText("");
        comboSexo.setSelectedIndex(0); // Resetea a la opción por defecto
        comboEstadoCivil.setSelectedIndex(0); // Resetea a la opción por defecto
        fieldCiudad.setText("");
        fieldEdad.setText("");
        dateChooserFechaNacimiento.setDate(null);
        fieldDireccion.setText("");
        fieldCorreo.setText("");
        fieldTelefono.setText("");
    }

    public JPanel getFormularioPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BorderLayout());
        formPanel.add(createFormPanel(), BorderLayout.CENTER);
        formPanel.add(createButtonPanel(), BorderLayout.SOUTH);
        return formPanel;
    }
    
    public static void main(String[] args) {
        // Ejecutar el formulario
        new RegistroClientesForm();
    }
}
