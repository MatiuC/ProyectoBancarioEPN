package BussinesLogic.Entities.BancoLogic;

import DataAccess.DAO.CredencialDAO;
import DataAccess.DTO.CredencialDTO;
import java.sql.SQLException;

public class ValidarIngreso {

    private CredencialDAO credencialDAO;

    public ValidarIngreso() {
        try {
            this.credencialDAO = new CredencialDAO();
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    // Método para validar las credenciales usando CredencialDAO
    public boolean validarCredenciales(Integer usuario, String contrasena) {
        try {
            // Buscar las credenciales del usuario en la base de datos por nombre de usuario
            CredencialDTO credencial = credencialDAO.readBy(usuario);

            if (credencial != null) {
                // Comparar la contraseña proporcionada con la almacenada
                return credencial.getPass().equals(contrasena); // Si son iguales, credenciales válidas
            }
        } catch (Exception e) {
            System.err.println("Error al validar credenciales: " + e.getMessage());
        }
        return false; // Si no se encuentran credenciales o no coinciden
    }

    // Método para verificar si la cuenta existe en la base de datos
    public boolean cuentaExiste(Integer usuario) {
        try {
            CredencialDTO credencial = credencialDAO.readBy(usuario); // Buscar por usuario
            return credencial != null; // Si se encuentra, la cuenta existe
        } catch (Exception e) {
            System.err.println("Error al verificar existencia de cuenta: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener el rol del usuario
    public String obtenerRol(Integer usuario) {
        try {
            CredencialDTO credencial = credencialDAO.readBy(usuario); // Buscar por usuario
            return credencial != null ? credencial.getEstado() : "No encontrado"; // Devuelve el estado (rol)
        } catch (Exception e) {
            System.err.println("Error al obtener rol: " + e.getMessage());
            return "Error";
        }
    }

    // Método para mostrar la ventana adecuada según el rol
    public void mostrarVentanas(String rol) {
        switch (rol.toLowerCase()) {
            case "cliente":
                System.out.println("Ventana de Cliente");
                break;
            case "cajero":
                System.out.println("Ventana de Cajero");
                break;
            case "administrador":
                System.out.println("Ventana de Administrador");
                break;
            default:
                System.out.println("Rol no reconocido");
                break;
        }
    }

    // Método principal para validar el ingreso del usuario
    public void validarIngreso(Integer usuario, String contrasena) {
        if (validarCredenciales(usuario, contrasena)) {
            if (cuentaExiste(usuario)) {
                String rol = obtenerRol(usuario);
                mostrarVentanas(rol); // Mostrar la ventana según el rol
            } else {
                System.out.println("Cuenta no existe.");
            }
        } else {
            System.out.println("Credenciales incorrectas.");
       }
    }
}