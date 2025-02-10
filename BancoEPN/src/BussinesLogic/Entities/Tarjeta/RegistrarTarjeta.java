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
    public void crearTarjeta(Integer persona, Integer cuentaBancariaId, Integer tipoTarjeta, Integer franquicia) {
        // Crear valores aleatorios para los atributos
        String numeroTarjeta = "4" + (1000000000000000L + new Random().nextLong() % 9000000000000000L);  // Tarjeta Visa con 14 dígitos
        String cvv = generarCCV();  // Generamos el CCV
        String fechaExpedicion = generarFechaExpedicion();  // Fecha de expedición (hoy)
        String fechaVencimiento = generarFechaExpiracion();  // Fecha de expiración generada aleatoriamente
    
        // Creamos una nueva instancia de TarjetaDTO
        TarjetaDTO tarjeta = new TarjetaDTO(
            null,  // El ID será generado automáticamente por la base de datos
            numeroTarjeta,
            fechaExpedicion,
            fechaVencimiento,
            cvv,
            tipoTarjeta,  // Suponiendo que el tipo de tarjeta es un número (por ejemplo: 1 = Débito, 2 = Crédito)
            franquicia,  // ID de la franquicia (por ejemplo: 1 = Visa, 2 = MasterCard)
            "",  // Se generará automáticamente la fecha de creación
            "",  // Se generará automáticamente la fecha de modificación
            "A",  // Estado activo por defecto
            persona,
            cuentaBancariaId
        );
    
        try {
            // Llamamos al DAO para guardar la tarjeta en la base de datos
            TarjetaDAO tarjetaDAO = new TarjetaDAO();
            boolean result = tarjetaDAO.create(tarjeta);  // Guarda la tarjeta en la base de datos
            if (result) {
                System.out.println("Tarjeta registrada con éxito!");
            } else {
                System.out.println("Error al registrar la tarjeta.");
            }
        } catch (SQLException e) {
            // Aquí manejamos específicamente las excepciones SQL
            System.err.println("Error al crear la tarjeta: " + e.getMessage());
        } catch (Exception e) {
            // Aquí manejamos otras excepciones generales
            System.err.println("Error al crear la tarjeta: " + e.getMessage());
        }
    }
    

    // Método para enviar un correo de confirmación (simulado)
    public void enviarCorreoConfirmacion(String email) {
        System.out.println("Enviando correo de confirmación a: " + email);
        // Aquí puedes integrar la lógica para enviar un correo real (JavaMail)
    }
}