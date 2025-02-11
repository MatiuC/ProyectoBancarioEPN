package src.BussinesLogic.Entities.BancoLogic;

public class TestValidarTransaccion {
    public static void main(String[] args) {
        try {
            ValidarTransaccion validador = new ValidarTransaccion();
            
            // Prueba 1: Verificar saldo suficiente
            Integer cuentaOrigen = 1; // Asumiendo que existe esta cuenta en la base de datos
            Double montoTransferencia = 100.0;
            
            System.out.println("Prueba 1 - Verificar saldo suficiente:");
            boolean tieneSaldo = validador.saldoSuficiente(cuentaOrigen, montoTransferencia);
            System.out.println("¿Tiene saldo suficiente? " + tieneSaldo);
            
            // Prueba 2: Realizar transferencia
            Integer cuentaDestino = 2; // Asumiendo que existe esta cuenta
            System.out.println("\nPrueba 2 - Realizar transferencia:");
            boolean transferenciaExitosa = validador.transferirSaldo(cuentaOrigen, cuentaDestino, montoTransferencia);
            System.out.println("¿Transferencia exitosa? " + transferenciaExitosa);
            
            // Prueba 3: Procesar transacción completa
            System.out.println("\nPrueba 3 - Procesar transacción completa:");
            String resultado = validador.procesarTransaccion(
                cuentaOrigen, 
                cuentaDestino, 
                montoTransferencia,
                1, // Tipo de transacción (asumiendo 1 = transferencia)
                "Prueba de transferencia",
                "correo@ejemplo.com"
            );
            System.out.println("Resultado: " + resultado);
            
        } catch (Exception e) {
            System.err.println("Error en la prueba: " + e.getMessage());
            e.printStackTrace();
        }
    }
}