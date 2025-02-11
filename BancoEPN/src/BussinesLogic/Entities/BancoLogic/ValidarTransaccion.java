package BussinesLogic.Entities.BancoLogic;

import DataAccess.DAO.CuentaBancariaDAO;
import DataAccess.DAO.TransaccionDAO;
import DataAccess.DTO.CuentaBancariaDTO;
import DataAccess.DTO.TransaccionDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ValidarTransaccion {

    private TransaccionDAO transaccionDAO;
    private CuentaBancariaDAO cuentaBancariaDAO;

    public ValidarTransaccion() throws Exception {
        this.transaccionDAO = new TransaccionDAO();
        this.cuentaBancariaDAO = new CuentaBancariaDAO();
    }

    public boolean saldoSuficiente(Integer cuentaEnvio, Double monto) throws Exception {
        CuentaBancariaDTO cuenta = cuentaBancariaDAO.readBy(cuentaEnvio);
        return cuenta != null && cuenta.getSaldo() >= monto;
    }

    public boolean transferirSaldo(Integer cuentaEnvio, Integer cuentaRecepcion, Double monto) throws Exception {
        if (saldoSuficiente(cuentaEnvio, monto)) {
            cuentaBancariaDAO.actualizarSaldo(cuentaEnvio, -monto);
            cuentaBancariaDAO.actualizarSaldo(cuentaRecepcion, monto);
            return true;
        }
        return false;
    }

    public void registrarTransaccion(Integer cuentaEnvio, Integer cuentaRecepcion, Double monto, Integer tipoTransaccion, String descripcion) {
        LocalDateTime now = LocalDateTime.now();
        String fecha = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String hora = now.format(DateTimeFormatter.ofPattern("HH:mm"));

        TransaccionDTO transaccion = new TransaccionDTO(
                cuentaEnvio, cuentaRecepcion, monto, tipoTransaccion, fecha, hora, descripcion, fecha, fecha, true);

        try {
            transaccionDAO.create(transaccion);
        } catch (Exception e) {
            System.err.println("Error al registrar la transacción: " + e.getMessage());
        }
    }

    public String procesarTransaccion(Integer cuentaEnvio, Integer cuentaRecepcion, Double monto, Integer tipoTransaccion, String descripcion, String email) throws Exception {
        if (transferirSaldo(cuentaEnvio, cuentaRecepcion, monto)) {
            registrarTransaccion(cuentaEnvio, cuentaRecepcion, monto, tipoTransaccion, descripcion);
            return "Transacción exitosa";
        }
        return "Error en la transacción: saldo insuficiente o cuenta no válida";
    }
}
