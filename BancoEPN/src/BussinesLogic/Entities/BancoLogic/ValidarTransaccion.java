package BussinesLogic.Entities.BancoLogic;

import DataAccess.DAO.TransaccionDAO;
import DataAccess.DAO.CuentaBancariaDAO;
import DataAccess.DTO.TransaccionDTO;
import DataAccess.DTO.CuentaBancariaDTO;
import java.sql.SQLException;

public class ValidarTransaccion {

    private TransaccionDAO transaccionDAO;
    private CuentaBancariaDAO cuentaBancariaDAO;

    public ValidarTransaccion() throws Exception {
        this.transaccionDAO = new TransaccionDAO();
        this.cuentaBancariaDAO = new CuentaBancariaDAO(); // Instanciamos el DAO para las cuentas bancarias
    }

    // Validar si la cuenta de envío existe
    public boolean cuentaDeEnvioExiste(Integer cuentaEnvio) throws Exception {
        try {
            CuentaBancariaDTO cuenta = cuentaBancariaDAO.readBy(cuentaEnvio); // Verificamos si la cuenta de envío existe
            return cuenta != null; // Si la cuenta existe, retorna true
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de la cuenta de envío: " + e.getMessage());
            return false;
        }
    }

    // Validar si la cuenta de recepción existe
    public boolean cuentaDeRecepcionExiste(Integer cuentaRecepcion) throws Exception {
        try {
            CuentaBancariaDTO cuenta = cuentaBancariaDAO.readBy(cuentaRecepcion); // Verificamos si la cuenta de recepción existe
            return cuenta != null; // Si la cuenta existe, retorna true
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de la cuenta de recepción: " + e.getMessage());
            return false;
        }
    }

    // Validar si el saldo es suficiente en la cuenta de envío
    public boolean saldoSuficiente(Integer cuentaEnvio, Double monto) throws Exception {
        try {
            CuentaBancariaDTO cuenta = cuentaBancariaDAO.readBy(cuentaEnvio); // Obtenemos la cuenta de envío
            return cuenta != null && cuenta.getSaldo() >= monto; // Verificamos si el saldo es suficiente para realizar la transacción
        } catch (SQLException e) {
            System.err.println("Error al verificar el saldo de la cuenta de envío: " + e.getMessage());
            return false;
        }
    }

    // Transferir el saldo de la cuenta de envío a la cuenta de recepción
    public boolean transferirSaldo(Integer cuentaEnvio, Integer cuentaRecepcion, Double monto) throws Exception {
        try {
            if (saldoSuficiente(cuentaEnvio, monto)) {
                // Restamos el monto de la cuenta de envío
                cuentaBancariaDAO.actualizarSaldo(cuentaEnvio, -monto);
                // Sumamos el monto a la cuenta de recepción
                cuentaBancariaDAO.actualizarSaldo(cuentaRecepcion, monto);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar la transferencia: " + e.getMessage());
        }
        return false; // Si el saldo no es suficiente, no realizamos la transferencia
    }

    // Crear un registro de la transacción en la base de datos
    public void registrarTransaccion(Integer cuentaEnvio, Integer cuentaRecepcion, Double monto, String tipoTransaccion, String descripcion) {
        TransaccionDTO transaccion = new TransaccionDTO(
                cuentaEnvio,
                cuentaRecepcion,
                monto,
                tipoTransaccion,
                "2025-02-09", // Fecha de la transacción (puedes generar dinámicamente la fecha actual)
                "12:00", // Hora de la transacción (puedes generar dinámicamente la hora actual)
                descripcion,
                "2025-02-09", // Fecha de creación
                "2025-02-09", // Fecha de modificación
                true // Estado activo
        );
        try {
            transaccionDAO.create(transaccion); // Guardamos la transacción en la base de datos
        } catch (Exception e) {
            System.err.println("Error al registrar la transacción: " + e.getMessage());
        }
    }

    // Enviar un correo de confirmación de la transacción
    public void enviarCorreoConfirmacion(String email) {
        // Aquí puedes integrar la lógica para enviar un correo, por ejemplo, usando JavaMail
        System.out.println("Enviando correo de confirmación a: " + email);
    }

    // Método principal que procesa la transacción
    public String procesarTransaccion(Integer cuentaEnvio, Integer cuentaRecepcion, Double monto, String tipoTransaccion, String descripcion, String email) throws Exception {
        if (transferirSaldo(cuentaEnvio, cuentaRecepcion, monto)) {
            registrarTransaccion(cuentaEnvio, cuentaRecepcion, monto, tipoTransaccion, descripcion); // Registrar la transacción
            enviarCorreoConfirmacion(email); // Enviar un correo de confirmación
            return "Transacción exitosa"; // Devolver mensaje de éxito
        } else {
            return "Error en la transacción: saldo insuficiente o cuenta no válida"; // Devolver mensaje de error
        }
    }

    // Método para realizar transacción con tarjeta
    public boolean transaccionATarjeta(String numeroTarjeta, Double monto) {
        // Lógica para realizar una transacción a una tarjeta
        // Aquí puedes integrar la validación de la tarjeta y verificar si el saldo es suficiente
        System.out.println("Procesando transacción a tarjeta...");
        return true; // Suponiendo que la transacción es exitosa
    }

    // Método para realizar un ciclo repetido si es necesario
    public void repetirCiclo() {
        // Lógica para repetir un ciclo si es necesario
        // Esto puede incluir reintentos o revalidaciones en caso de fallos
        System.out.println("Repetir ciclo de transacción...");
    }
}