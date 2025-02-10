import DataAccess.DAO.TarjetaDAO;
import DataAccess.DTO.TarjetaDTO;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        // Datos de la tarjeta que vamos a crear
        String numeroTarjeta = "1234567890123456"; // Número de tarjeta
        String fechaExpedicion = "2023-01-01";     // Fecha de expedición
        String fechaVencimiento = "2025-01-01";    // Fecha de vencimiento
        String cvv = "123";                        // CVV
        int tipoTarjeta = 1;                       // Tipo de tarjeta (ej. 1 = débito, 2 = crédito)
        int idFranquicia = 1;                      // ID de la franquicia (ej. 1 = Visa)
        String estado = "1";                       // Estado de la tarjeta (1 = activa)
        int personaId = 1001;                      // ID de la persona a la que pertenece la tarjeta
        int cuentaBancariaId = 9876;               // ID de la cuenta bancaria asociada

        try {
            // Crear una instancia de TarjetaDAO
            TarjetaDAO tarjetaDAO = new TarjetaDAO();

            // Crear un objeto de tipo TarjetaDTO con los datos de la tarjeta
            TarjetaDTO nuevaTarjeta = new TarjetaDTO(
                    0,                             // ID de la tarjeta (se genera automáticamente)
                    numeroTarjeta, 
                    fechaExpedicion,
                    fechaVencimiento,
                    cvv,
                    tipoTarjeta,
                    idFranquicia,
                    null, // fechaCreacion será manejada por la base de datos
                    null, // fechaModificacion también será manejada por la base de datos
                    estado,
                    personaId,
                    cuentaBancariaId
            );

            // Insertar la tarjeta en la base de datos
            boolean tarjetaCreada = tarjetaDAO.create(nuevaTarjeta);

            if (tarjetaCreada) {
                System.out.println("Tarjeta creada correctamente.");

                // Verificar si la tarjeta está registrada en la base de datos
                TarjetaDTO tarjetaBuscada = tarjetaDAO.readBy(numeroTarjeta);
                
                if (tarjetaBuscada != null) {
                    System.out.println("Tarjeta encontrada: ");
                    System.out.println("Número de tarjeta: " + tarjetaBuscada.getNumero_tarjeta());
                    System.out.println("Fecha de vencimiento: " + tarjetaBuscada.getFecha_vencimiento());
                    System.out.println("Estado: " + tarjetaBuscada.getEstado());
                } else {
                    System.out.println("Tarjeta no encontrada.");
                }
            } else {
                System.out.println("Error al crear la tarjeta.");
            }

        } catch (Exception e) {
            System.err.println("Error en la operación: " + e.getMessage());
        }
    }
}
