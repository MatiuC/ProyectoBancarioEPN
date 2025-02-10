package BussinesLogic.Entities.ATM;

import DataAccess.DAO.TransaccionDAO;
import DataAccess.DAO.CuentaBancariaDAO;
import DataAccess.DAO.TarjetaDAO;
import DataAccess.DTO.TransaccionDTO;
import DataAccess.DTO.CuentaBancariaDTO;
import DataAccess.DTO.TarjetaDTO;
import java.sql.*;

public class Retiro {

    // Método para realizar un retiro
    public boolean realizarRetiro(Connection conn, String numeroTarjeta, String pin, double montoRetiro) {
        try {
            // 1. Obtener el Id_tarjeta a partir del numero_tarjeta
            String query = "SELECT Id_tarjeta FROM Tarjeta WHERE numero_tarjeta = ? AND estado = 'A'";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(2, numeroTarjeta);
            ResultSet rs = stmt.executeQuery();

            System.out.println(numeroTarjeta);
            if (!rs.next()) {
                System.out.println("Tarjeta no registrada o no activa.");
                return false; // Tarjeta no encontrada o inactiva
            }

            int idTarjeta = rs.getInt("Id_tarjeta");

            // 2. Obtener detalles de la tarjeta
            TarjetaDAO tarjetaDAO = new TarjetaDAO(conn);
            TarjetaDTO tarjeta = tarjetaDAO.readBy(idTarjeta);

            if (tarjeta == null) {
                System.out.println("Error al obtener los detalles de la tarjeta.");
                return false;
            }

            int idCuentaBancaria = tarjeta.getId_cuentabancaria();
            String cvv = tarjeta.getCvv();

            // 3. Verificar el PIN (CVV)
            if (!cvv.equals(pin.toString())) {
                System.out.println("PIN incorrecto.");
                return false;
            }

            // 4. Validar saldo suficiente
            CuentaBancariaDAO cuentaDAO = new CuentaBancariaDAO(conn);
            CuentaBancariaDTO cuenta = cuentaDAO.readBy(idCuentaBancaria);

            if (cuenta == null) {
                System.out.println("Cuenta bancaria no encontrada.");
                return false;
            }

            double saldo = cuenta.getSaldo();
            if (saldo < montoRetiro) {
                System.out.println("Saldo insuficiente.");
                return false;
            }

            // 5. Actualizar el saldo de la cuenta bancaria
            query = "UPDATE CuentaBancaria SET saldo = saldo - ? WHERE id_cuentabancaria = ?";
            stmt = conn.prepareStatement(query);
            stmt.setDouble(1, montoRetiro);
            stmt.setInt(2, idCuentaBancaria);
            int rowsUpdated = stmt.executeUpdate();
            System.out.println(idCuentaBancaria);
            if (rowsUpdated == 0) {
                System.out.println("Error al actualizar el saldo.");
                return false;
            }

            // 6. Registrar la transacción
            TransaccionDAO transaccionDAO = new TransaccionDAO(conn);
            TransaccionDTO transaccion = new TransaccionDTO(
                idCuentaBancaria,  // Origen
                1,  // Destino (banco)
                montoRetiro,
                "Retiro",
                obtenerFecha(),
                obtenerHora(),
                "Retiro en cajero automático",
                obtenerFecha(),
                obtenerFecha(),
                true
            );

            boolean transaccionExitosa = transaccionDAO.create(transaccion);

            if (!transaccionExitosa) {
                System.out.println("Error al registrar la transacción.");
                return false;
            }

            System.out.println("Retiro exitoso.");
            return true;

        } catch (Exception e) {
            System.out.println("asdlkajsdlkasjdljas");
            e.printStackTrace();
            return false;
        }
    }

    // Métodos auxiliares para obtener fecha y hora actuales
    private String obtenerFecha() {
        return java.time.LocalDate.now().toString();  // Formato YYYY-MM-DD
    }

    private String obtenerHora() {
        return java.time.LocalTime.now().toString();  // Formato HH:MM:SS
    }
}
