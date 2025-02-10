package BussinesLogic.Entities.Tarjeta;

import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.SQLException;
import DataAccess.DAO.TarjetaDAO;
import DataAccess.DTO.TarjetaDTO;

public class RegistrarTarjeta {

    // Método para generar un CCV aleatorio (3 dígitos)
    private String generarCCV() {
        Random random = new Random();
        int ccv = 100 + random.nextInt(900);  // Genera un número aleatorio entre 100 y 999
        return String.valueOf(ccv);  // Convertimos el número a String
    }

    // Método para generar una fecha de expiración aleatoria
    private String generarFechaExpiracion() {
        Random random = new Random();
        int aniosValido = 5 + random.nextInt(5);  // Fecha de expiración entre 5 y 9 años a partir del año actual
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, aniosValido);  // Sumamos el número aleatorio de años
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        return sdf.format(calendar.getTime());  // Devolvemos la fecha con formato MM/yyyy
    }

    // Método para generar la fecha de expedición (hoy)
    private String generarFechaExpedicion() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        return sdf.format(Calendar.getInstance().getTime());  // Fecha actual
    }


    // Método principal para crear y registrar la tarjeta
    // Método principal para crear y registrar la tarjeta
    public void crearTarjeta(String numeroTarjeta, Integer persona, Integer cuentaBancariaId, Integer tipoTarjeta, Integer franquicia) {
        // Validar que el número de tarjeta no esté vacío
        if (numeroTarjeta == null || numeroTarjeta.trim().isEmpty()) {
            System.err.println("Error: El número de tarjeta no puede estar vacío.");
            return;
        }
    
        // Crear valores aleatorios para los atributos restantes
        String cvv = generarCCV();
        String fechaExpedicion = generarFechaExpedicion();
        String fechaVencimiento = generarFechaExpiracion();
    
        // Creamos una nueva instancia de TarjetaDTO
        TarjetaDTO tarjeta = new TarjetaDTO(
            null,  // El ID será generado automáticamente por la base de datos
            numeroTarjeta,  // Ahora recibimos el número desde la interfaz gráfica
            fechaExpedicion,
            fechaVencimiento,
            cvv,
            tipoTarjeta = 1,
            franquicia = 2,
            "",
            "",
            "A",
            persona,
            cuentaBancariaId
        );
    
        try {
            // Llamamos al DAO para guardar la tarjeta en la base de datos
            TarjetaDAO tarjetaDAO = new TarjetaDAO();
            boolean result = tarjetaDAO.create(tarjeta);
            if (result) {
                System.out.println("Tarjeta registrada con éxito!");
            } else {
                System.out.println("Error al registrar la tarjeta.");
            }
        } catch (SQLException e) {
            System.err.println("Error al crear la tarjeta: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al crear la tarjeta: " + e.getMessage());
        }
    }  
}
