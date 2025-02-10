package UserInterface.Form;

import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import DataAccess.DTO.PersonaDTO;
import DataAccess.DAO.PersonaDAO;
import BussinesLogic.BLFactory;
import BussinesLogic.ApiRequest.GetDatosCedula;
import java.sql.Date;
import BussinesLogic.Entities.Registro.GenerarCredenciales;
import BussinesLogic.Entities.Registro.RegistrarPersona;
import UserInterface.CustomerControl.RoundedTextField;




public class RegistroClientesForm extends JFrame {

    private RoundedTextField fieldCedula, fieldNombre, fieldApellido, fieldCiudad, fieldEdad, fieldDireccion, fieldCorreo, fieldTelefono;
    private JComboBox<String> comboSexo, comboEstadoCivil;
    private JDateChooser dateChooserFechaNacimiento;

    private BLFactory<PersonaDTO> personaBL;
    private GetDatosCedula apiRequest;

    public RegistroClientesForm() {
        try {
            initializeBusinessLogic();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al inicializar la lógica de negocio: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        setTitle("Registro de Clientes");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(getFormularioPanel());
    }

    private void initializeBusinessLogic() throws SQLException {
        try {
            personaBL = new BLFactory<>(() -> {
                try {
                    return new PersonaDAO();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            apiRequest = new GetDatosCedula();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al inicializar la lógica de negocio: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


        // Método para crear el panel del formulario
        public JPanel FormularioPanel () {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            panel.setBackground(new Color(0xFF, 0xFF, 0xFF)); // White

            // Establecer fuentes y colores para las etiquetas
            Font labelFont = new Font("Arial", Font.BOLD, 14);
            Color labelColor = new Color(0x2F, 0x48, 0x58); // Charcoal

            // Configurar las restricciones del GridBagLayout
            GridBagConstraints labelConstraints = new GridBagConstraints();
            labelConstraints.gridx = 0;
            labelConstraints.fill = GridBagConstraints.HORIZONTAL;
            labelConstraints.insets = new Insets(5, 20, 5, 10); // top, left, bottom, right
            labelConstraints.anchor = GridBagConstraints.WEST;

            GridBagConstraints fieldConstraints = new GridBagConstraints();
            fieldConstraints.gridx = 1;
            fieldConstraints.fill = GridBagConstraints.HORIZONTAL;
            fieldConstraints.weightx = 1.0;
            fieldConstraints.insets = new Insets(5, 10, 5, 20); // top, left, bottom, right


            JLabel labelCedula = new JLabel("Cédula:");
            labelCedula.setFont(labelFont);
            labelCedula.setForeground(labelColor);
            fieldCedula = new RoundedTextField(20);


            JLabel labelNombre = new JLabel("Nombre:");
            labelNombre.setFont(labelFont);
            labelNombre.setForeground(labelColor);
            fieldNombre = new RoundedTextField(20);

            JLabel labelApellido = new JLabel("Apellido:");
            labelApellido.setFont(labelFont);
            labelApellido.setForeground(labelColor);
            fieldApellido = new RoundedTextField(20);

            JLabel labelSexo = new JLabel("Sexo:");
            labelSexo.setFont(labelFont);
            labelSexo.setForeground(labelColor);
            comboSexo = new JComboBox<>(new String[] {"MASCULINO", "FEMENINO", "Otro"});

            JLabel labelEstadoCivil = new JLabel("Estado Civil:");
            labelEstadoCivil.setFont(labelFont);
            labelEstadoCivil.setForeground(labelColor);
            comboEstadoCivil = new JComboBox<>(new String[] {"Soltero", "Casado", "Divorciado", "Viudo"});

            JLabel labelCiudad = new JLabel("Ciudad:");
            labelCiudad.setFont(labelFont);
            labelCiudad.setForeground(labelColor);
            fieldCiudad = new RoundedTextField(20);

            JLabel labelEdad = new JLabel("Edad:");
            labelEdad.setFont(labelFont);
            labelEdad.setForeground(labelColor);
            fieldEdad = new RoundedTextField(20);

            JLabel labelFechaNacimiento = new JLabel("Fecha de Nacimiento:");
            labelFechaNacimiento.setFont(labelFont);
            labelFechaNacimiento.setForeground(labelColor);
            dateChooserFechaNacimiento = new JDateChooser();

            JLabel labelDireccion = new JLabel("Dirección:");
            labelDireccion.setFont(labelFont);
            labelDireccion.setForeground(labelColor);
            fieldDireccion = new RoundedTextField(20);

            JLabel labelCorreo = new JLabel("Correo:");
            labelCorreo.setFont(labelFont);
            labelCorreo.setForeground(labelColor);
            fieldCorreo = new RoundedTextField(20);

            JLabel labelTelefono = new JLabel("Teléfono:");
            labelTelefono.setFont(labelFont);
            labelTelefono.setForeground(labelColor);
            fieldTelefono = new RoundedTextField(20);

            // Agregar componentes con las restricciones
            // Agregar cédula con botón consultar
            labelConstraints.gridy = 0;
            panel.add(labelCedula, labelConstraints);

            // Panel para campo de cédula y botón consultar
            JPanel cedulaPanel = new JPanel(new BorderLayout(5, 0));
            cedulaPanel.setBackground(new Color(0xFF, 0xFF, 0xFF)); // White

            // Crear y configurar el botón consultar
            JButton buttonConsultar = new JButton("Consultar");
            buttonConsultar.setBackground(new Color(0x27, 0x41, 0x56)); // Charcoal
            buttonConsultar.setForeground(new Color(0xFF, 0xFF, 0xFF)); // White
            buttonConsultar.setFont(new Font("Arial", Font.BOLD, 14));
            buttonConsultar.addActionListener(e -> consultarCedula());

            // Agregar campo y botón al panel
            cedulaPanel.add(fieldCedula, BorderLayout.CENTER);
            cedulaPanel.add(buttonConsultar, BorderLayout.EAST);

            // Configurar y agregar el panel de cédula
            GridBagConstraints fieldPanelConstraints = new GridBagConstraints();
            fieldPanelConstraints.gridx = 1;
            fieldPanelConstraints.gridy = 0;
            fieldPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
            fieldPanelConstraints.weightx = 1.0;
            fieldPanelConstraints.insets = new Insets(5, 10, 5, 20);
            panel.add(cedulaPanel, fieldPanelConstraints);

            // Ajustar la posición de los demás campos para empezar después del panel de cédula
            labelConstraints.gridwidth = 1;
            fieldConstraints.gridwidth = 1;

            labelConstraints.gridy = 1;
            fieldConstraints.gridy = 1;
            panel.add(labelNombre, labelConstraints);
            panel.add(fieldNombre, fieldConstraints);

            labelConstraints.gridy = 2;
            fieldConstraints.gridy = 2;
            panel.add(labelApellido, labelConstraints);
            panel.add(fieldApellido, fieldConstraints);

            labelConstraints.gridy = 3;
            fieldConstraints.gridy = 3;
            panel.add(labelSexo, labelConstraints);
            panel.add(comboSexo, fieldConstraints);

            labelConstraints.gridy = 4;
            fieldConstraints.gridy = 4;
            panel.add(labelEstadoCivil, labelConstraints);
            panel.add(comboEstadoCivil, fieldConstraints);

            labelConstraints.gridy = 5;
            fieldConstraints.gridy = 5;
            panel.add(labelCiudad, labelConstraints);
            panel.add(fieldCiudad, fieldConstraints);

            labelConstraints.gridy = 6;
            fieldConstraints.gridy = 6;
            panel.add(labelEdad, labelConstraints);
            panel.add(fieldEdad, fieldConstraints);

            labelConstraints.gridy = 7;
            fieldConstraints.gridy = 7;
            panel.add(labelFechaNacimiento, labelConstraints);
            panel.add(dateChooserFechaNacimiento, fieldConstraints);

            labelConstraints.gridy = 8;
            fieldConstraints.gridy = 8;
            panel.add(labelDireccion, labelConstraints);
            panel.add(fieldDireccion, fieldConstraints);

            labelConstraints.gridy = 9;
            fieldConstraints.gridy = 9;
            panel.add(labelCorreo, labelConstraints);
            panel.add(fieldCorreo, fieldConstraints);

            labelConstraints.gridy = 10;
            fieldConstraints.gridy = 10;
            panel.add(labelTelefono, labelConstraints);
            panel.add(fieldTelefono, fieldConstraints);
            return panel;
        }

        // Método para crear el panel de botones
        public JPanel createButtonPanel() {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
            panel.setBackground(new Color(0xD3, 0xD3, 0xD3)); // Timberwolf

            // Crear botones con colores personalizados
            JButton buttonGuardar = new JButton("Guardar");
            buttonGuardar.setBackground(new Color(0x27, 0x41, 0x56)); // Charcoal
            buttonGuardar.setForeground(new Color(0xFF, 0xFF, 0xFF)); // White
            buttonGuardar.setFont(new Font("Arial", Font.BOLD, 14));

            // Acción para el botón "Guardar"
            buttonGuardar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (isFormValid()) {
                        guardarPersona();
                        //JOptionPane.showMessageDialog(null, "¡Registro guardado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Faltan llenar campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            JButton buttonNuevoRegistro = new JButton("Nuevo Registro");
            buttonNuevoRegistro.setBackground(new Color(0x27, 0x41, 0x56)); // Charcoal
            buttonNuevoRegistro.setForeground(new Color(0xFF, 0xFF, 0xFF)); // White
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
        
        private boolean validarCedula(String cedula) {
            return cedula != null && cedula.matches("\\d{10}");
        }

        private void consultarCedula() {
            String cedula = fieldCedula.getText().trim();
            if (!validarCedula(cedula)) {
                JOptionPane.showMessageDialog(this,
                    "Por favor ingrese una cédula válida de 10 dígitos",
                    "Error de Validación",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            //buttonConsultar.setEnabled(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    



            SwingWorker<PersonaDTO, Void> worker = new SwingWorker<PersonaDTO, Void>() {
                @Override
                protected PersonaDTO doInBackground() throws Exception {
                    return apiRequest.sendPostRequest(cedula);
                }
    
                @Override
                protected void done() {
                    try {
                        PersonaDTO persona = get();
                        if (persona != null) {
                            fieldCedula.setText(persona.getCedula());
                            fieldNombre.setText(persona.getNombre());
                            fieldApellido.setText(persona.getApellido());
                            fieldCiudad.setText(persona.getCiudad());
                            fieldEdad.setText(persona.getEdad());
                            dateChooserFechaNacimiento.setDate(persona.getFecha_nacimiento());
                            fieldDireccion.setText(persona.getDireccion());
                            fieldCorreo.setText(persona.getcorreo());
                            fieldTelefono.setText(persona.getTelefono());
                            
                            
    
                            // Seleccionar el estado civil correspondiente
                            for (int i = 0; i < comboEstadoCivil.getItemCount(); i++) {
                                if (comboEstadoCivil.getItemAt(i).equals(persona.getEstado_civil())) {
                                    comboEstadoCivil.setSelectedIndex(i);
                                    break;
                                }
                            }

    
                           dateChooserFechaNacimiento.setDate(persona.getFecha_nacimiento());
                        } else {
                            JOptionPane.showMessageDialog(RegistroClientesForm.this,
                                "No se encontraron datos para la cédula ingresada",
                                "Sin Resultados",
                                JOptionPane.INFORMATION_MESSAGE);
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(RegistroClientesForm.this,
                            "Error al consultar la cédula: " + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    } finally {
                       //buttonConsultar.setEnabled(true);
                        setCursor(Cursor.getDefaultCursor());
                    }
                }

            };
    
            worker.execute();
        }

        private void guardarPersona() {
            try {
                if (!isFormValid()) {
                    JOptionPane.showMessageDialog(this,
                        "Por favor complete todos los campos requeridos",
                        "Campos Incompletos",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
    
                PersonaDTO persona = new PersonaDTO();

                persona.setCedula(fieldCedula.getText());
                persona.setNombre(fieldNombre.getText());
                persona.setApellido(fieldApellido.getText());
                persona.setSexo(comboSexo.getSelectedItem().toString());
                persona.setEstado_civil(comboEstadoCivil.getSelectedItem().toString());
                persona.setCiudad(fieldCiudad.getText());
                persona.setEdad(fieldEdad.getText());
                java.util.Date utilDate = dateChooserFechaNacimiento.getDate();
                if (utilDate != null) {
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    persona.setFecha_nacimiento(sqlDate);
                } else {
                    persona.setFecha_nacimiento(null);
                }
                persona.setDireccion(fieldDireccion.getText());
                persona.setcorreo(fieldCorreo.getText());
                persona.setTelefono(fieldTelefono.getText());
                persona.setRol(2);
                persona.setEstado("A");
                    
                // Guardar la persona y obtener el ID generado
                if (personaBL.add(persona)) {
                    // Usar directamente el ID de la persona que acabamos de crear
                    RegistrarPersona registrar = new RegistrarPersona();
                    registrar.registrarPersona(persona.getPersona_id(), persona.getNombre(), persona.getApellido(), persona.getEdad(), persona.getcorreo());
         
                    JOptionPane.showMessageDialog(this,
                        "Persona guardada exitosamente. Se han enviado las credenciales al correo registrado.\nBono de bienvenida de 15 dolares",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                    clearForm();

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Error al guardar: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
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
            formPanel.add(FormularioPanel(), BorderLayout.CENTER);
            formPanel.add(createButtonPanel(), BorderLayout.SOUTH);
            return formPanel;
        }
        
}

