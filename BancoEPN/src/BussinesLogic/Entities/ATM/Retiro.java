package BussinesLogic.Entities.ATM;

import DataAccess.DAO.TransaccionDAO;
import DataAccess.DAO.TarjetaDAO;
import DataAccess.DTO.TransaccionDTO;
import DataAccess.DTO.TarjetaDTO;
import java.sql.*;

public class Retiro {

    private TarjetaDAO tarjetaDAO;
    private TransaccionDAO transaccionDAO;

    public Retiro() throws SQLException {
        this.tarjetaDAO = new TarjetaDAO();
        this.transaccionDAO = new TransaccionDAO();
    }

    // Método para realizar un retiro
    public String realizarRetiro(String numeroTarjeta, String pin, double monto) {
        try {
            // Validar si la tarjeta está registrada
            TarjetaDTO tarjeta = tarjetaDAO.readBy(numeroTarjeta);
            if (tarjeta == null) {
                return "Tarjeta no registrada.";
            }

            // Verificar el PIN de la tarjeta
            if (!validarPin(tarjeta, pin)) {
                return "PIN incorrecto.";
            }

            // Validar si el saldo es suficiente
            double saldoCuenta = obtenerSaldoCuenta(tarjeta.getId_cuentabancaria());
            if (saldoCuenta < monto) {
                return "Saldo insuficiente.";
            }

            // Actualizar el saldo de la cuenta bancaria
            actualizarSaldoCuenta(tarjeta.getId_cuentabancaria(), saldoCuenta - monto);

            // Registrar la transacción (tipo: Retiro)
            TransaccionDTO transaccion = new TransaccionDTO(
                    0, // Origen (Cuenta de la tarjeta)
                    1, // Destino (El banco, por ejemplo)
                    monto,
                    "Retiro",
                    obtenerFecha(),
                    obtenerHora(),
                    "Retiro desde el cajero",
                    obtenerFecha(),
                    obtenerFecha(),
                    true
            );
            transaccionDAO.create(transaccion);

            return "Retiro realizado con éxito.";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error al procesar el retiro: " + e.getMessage();
        }
    }

    // Método para validar el PIN de la tarjeta
    private boolean validarPin(TarjetaDTO tarjeta, String pin) {
        // Aquí se compara el PIN (en este caso, se asume que el pin es el CVV)
        return tarjeta.getCvv().equals(pin);
    }

    // Método para obtener el saldo de la cuenta bancaria
    private double obtenerSaldoCuenta(int idCuentaBancaria) throws SQLException {
        // Aquí debes implementar la lógica para obtener el saldo real desde la base de datos
        // Utilizando un DAO de cuenta bancaria, por ejemplo:
        // CuentaBancariaDAO cuentaBancariaDAO = new CuentaBancariaDAO();
        // CuentaBancariaDTO cuenta = cuentaBancariaDAO.readById(idCuentaBancaria);
        // return cuenta.getSaldo();

        // En este ejemplo, devolveremos un saldo simulado para ilustrar el proceso:
        return 5000.0;  // Simulación de saldo
    }

    // Método para actualizar el saldo de la cuenta bancaria
    private void actualizarSaldoCuenta(int idCuentaBancaria, double nuevoSaldo) throws SQLException {
        // Aquí deberías implementar la actualización del saldo en la base de datos
        // Usando un DAO para cuentas bancarias, algo como:
        // CuentaBancariaDAO cuentaBancariaDAO = new CuentaBancariaDAO();
        // cuentaBancariaDAO.actualizarSaldo(idCuentaBancaria, nuevoSaldo);

        // Ejemplo simulado de actualización del saldo:
        System.out.println("Saldo actualizado a: " + nuevoSaldo);
    }

    // Métodos para obtener la fecha y la hora actuales
    private String obtenerFecha() {
        // Utiliza las clases de fecha y hora para obtener la fecha actual
        // Ejemplo con LocalDate:
        return java.time.LocalDate.now().toString();  // Formato "YYYY-MM-DD"
    }

    private String obtenerHora() {
        // Ejemplo con LocalTime:
        return java.time.LocalTime.now().toString();  // Formato "HH:MM:SS"
    }
}
