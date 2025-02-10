package BussinesLogic.Entities.BancoLogic;

import DataAccess.DAO.CredencialDAO;
import DataAccess.DAO.PersonaDAO;
import DataAccess.DTO.CredencialDTO;
import java.sql.SQLException;
import DataAccess.DTO.PersonaDTO;

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
    public boolean validarCredenciales(String usuario, char[] password) {
        String contraseña = String.valueOf(password);
        try {
            CredencialDTO credencial = credencialDAO.readby(usuario);
            if (credencial != null) {
                // Comparar la contraseña proporcionada con la almacenada
                return credencial.getPass().equals(contraseña); 
            }
        } catch (Exception e) {
            System.err.println("Error al validar credenciales: " + e.getMessage());
        }
        return false; // Si no se encuentran credenciales o no coinciden
    }

    // Método para verificar si la cuenta existe en la base de datos
    public boolean cuentaExiste(String usuario) {
        try {
            CredencialDTO credencial = credencialDAO.readby(usuario); // Buscar por usuario
            return credencial != null; // Si se encuentra, la cuenta existe
        } catch (Exception e) {
            System.err.println("Error al verificar existencia de cuenta: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener el rol del usuario
    public int obtenerRol(String usuario) {
        try {
            CredencialDTO credencial = credencialDAO.readby(usuario); 
            PersonaDAO personaDAO = new PersonaDAO();
            
            return personaDAO.readBy(credencial.getIdPersona()).getRol(); // Devuelve el estado (rol)



        } catch (Exception e) {
            System.err.println("Error al obtener rol: " + e.getMessage());
            return 0;
        }
    }

    //Obtener el id del usuario
    public int obtenerId(String usuario) {
        try {
            CredencialDTO credencial = credencialDAO.readby(usuario);

            PersonaDAO personaDAO = new PersonaDAO();
            return personaDAO.readBy(credencial.getIdPersona()).getPersona_id();

        } catch (Exception e) {
            System.err.println("Error al obtener id: " + e.getMessage());
            return 0;
        }


    }


    // Método para mostrar la ventana adecuada según el rol
    public int mostrarVentanas(int rol) {
        switch (rol) {
            case 2:
                System.out.println("Ventana de Cliente");
                return 2;
            case 3:
                System.out.println("Ventana de Cajero");
                return 3;
            case 1:
                System.out.println("Ventana de Administrador");
                return 1;
            default:
                System.out.println("Rol no reconocido");
                return 0;
        }
    }

    // Método principal para validar el ingreso del usuario
    public void validarIngreso(String usuario, char[] contrasena) {
        if (validarCredenciales(usuario, contrasena)) {
            if (cuentaExiste(usuario)) {
                int rol = obtenerRol(usuario);

                mostrarVentanas(rol); // Mostrar la ventana según el rol
            } else {
                System.out.println("Cuenta no existe.");
            }
        } else {
            System.out.println("Credenciales incorrectas.");
       }
    }
}