package BussinesLogic.Entities.ATM;

import DataAccess.DAO.CuentaBancariaDAO;
import DataAccess.DAO.TarjetaDAO;
import DataAccess.DAO.TransaccionDAO;
import DataAccess.DTO.CuentaBancariaDTO;
import DataAccess.DTO.TarjetaDTO;
import DataAccess.DTO.TransaccionDTO;

public class Retiro {

    // Método para realizar un retiro
    public boolean realizarRetiro(String numeroTarjeta, String pin, double montoRetiro) {
        try {
           TarjetaDAO tarjetaDAO = new TarjetaDAO();
           TarjetaDTO tarjeta = tarjetaDAO.ReadBy(numeroTarjeta);

            int idCuentaBancaria = tarjeta.getId_cuentabancaria();
            String cvv = tarjeta.getCvv();

            // 3. Verificar el PIN (CVV) de 3 dígitos
            if (pin.length() != 3 || !cvv.equals(pin)) {
                System.out.println("PIN incorrecto. Debe ser de 3 dígitos.");
                return false;
            }

            // 4. Validar saldo suficiente
            CuentaBancariaDAO cuentaDAO = new CuentaBancariaDAO();
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
            cuentaDAO.actualizarSaldo(idCuentaBancaria, montoRetiro);


            // 6. Registrar la transacción
            TransaccionDAO transaccionDAO = new TransaccionDAO();
            TransaccionDTO transaccion = new TransaccionDTO(
                idCuentaBancaria,  // Origen
                1,  // Destino (banco)
                montoRetiro,
                1,
                obtenerFecha(),
                obtenerHora(),
                "Retiro en cajero automático",
                obtenerFecha(),
                obtenerHora(),
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
