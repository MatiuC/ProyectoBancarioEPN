package UserInterface.CustomerControl;

import javax.swing.*;
import java.awt.Component;

public class InputValidationUtil {

    public static boolean validarFormatoCedula(String cedula, Component parentComponent) {
        if (!cedula.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(parentComponent,
                "La cédula debe contener 10 dígitos numéricos",
                "Formato Inválido",
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean validarFormatoEdad(String edad, Component parentComponent, JComponent focusComponent) {
        try {
            int edadNum = Integer.parseInt(edad);
            if (edadNum < 18 || edadNum > 120) {
                JOptionPane.showMessageDialog(parentComponent,
                    "La edad debe estar entre 18 y 120 años",
                    "Edad Inválida",
                    JOptionPane.WARNING_MESSAGE);
                focusComponent.requestFocus();
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parentComponent,
                "Por favor ingrese una edad válida",
                "Formato Inválido",
                JOptionPane.WARNING_MESSAGE);
            focusComponent.requestFocus();
            return false;
        }
    }

    public static boolean validarFormatoTelefono(String telefono, Component parentComponent, JComponent focusComponent) {
        String telefonoRegex = "^\\(?(0\\d{2})\\)?[-\\s]?(\\d{3})[-\\s]?(\\d{4})$|^0\\d{9}$";
        if (!telefono.matches(telefonoRegex)) {
            JOptionPane.showMessageDialog(parentComponent,
                "Por favor ingrese un número de teléfono válido\n" +
                "Formatos aceptados:\n" +
                "- 0987654321\n" +
                "- 098-765-4321\n" +
                "- (098) 765-4321",
                "Formato Inválido",
                JOptionPane.WARNING_MESSAGE);
            focusComponent.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean validarFormatoEmail(String email, Component parentComponent, JComponent focusComponent) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!email.matches(emailRegex)) {
            JOptionPane.showMessageDialog(parentComponent,
                "Por favor ingrese un correo electrónico válido",
                "Formato Inválido",
                JOptionPane.WARNING_MESSAGE);
            focusComponent.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean validarCamposRequeridos(Component parentComponent, String... campos) {
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(parentComponent,
                    "Por favor complete todos los campos requeridos",
                    "Campos Incompletos",
                    JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return true;
    }
}