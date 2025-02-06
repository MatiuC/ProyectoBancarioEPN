package BussinesLogic.Entities.Registro;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import DataAccess.DAO.CredencialDAO;
import DataAccess.DTO.CredencialDTO;
import BussinesLogic.ApiRequest.EnviarMail;

public class GenerarCredenciales {
    
    
    public CredencialDTO generarCredenciales(int idPersona, String nombre, String apellido, String edad, String email) throws Exception {
        String usuario = generarUsuario(nombre, apellido, edad);
        String clave = generarPassword();
        
        CredencialDTO credenciales = new CredencialDTO();
        credenciales.setIdPersona(idPersona);
        credenciales.setUsuario(usuario);
        credenciales.setPass(clave);
        credenciales.setFechaCreacion(obtenerFechaActual());
        credenciales.setFechaModificacion(obtenerFechaActual());
        credenciales.setEstado("A");
        

        // Guardar en la base de datos
        CredencialDAO dao = new CredencialDAO();
        if (dao.create(credenciales)) {
            // Enviar por correo
            EnviarMail mailer = new EnviarMail();
            mailer.enviarCredenciales(email, usuario, clave);
        }
        
        return credenciales;
    }
    
    private String generarUsuario(String nombre, String apellido, String edad) {
        // Obtener solo el primer nombre y primer apellido
        String primerNombre = nombre.split(" ")[0].toLowerCase();
        String primerApellido = apellido.split(" ")[0].toLowerCase();
        
        // Genera un usuario con formato primernombre.primerapellido+edad
        return (primerNombre + "." + primerApellido + edad).replaceAll("\\s+", "");
    }
    
    
    /**
     * Genera una contraseña aleatoria de 8 caracteres alfanuméricos
     */
    public String generarPassword() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder(8);
        java.util.Random random = new java.util.Random();
        
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(caracteres.length());
            password.append(caracteres.charAt(index));
        }
        
        return password.toString();
    }
    
    /**
     * Obtiene la fecha actual en formato yyyy-MM-dd HH:mm:ss
     */
    public String obtenerFechaActual() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return ahora.format(formatter);
    }
}
