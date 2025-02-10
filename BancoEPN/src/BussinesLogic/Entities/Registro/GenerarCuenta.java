package BussinesLogic.Entities.Registro;

public class GenerarCuenta {

        //Genera numero de cuenta con 8 digitos aleatorios
        public String generarNumeroCuenta() {
            return String.format("%08d", (int)(Math.random() * 100000000));
        }
        
}
