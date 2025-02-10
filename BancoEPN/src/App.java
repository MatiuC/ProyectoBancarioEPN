
import UserInterface.Form.LoginPanel;
import BussinesLogic.ApiRequest.GetDatosCedula;
import DataAccess.DAO.CuentaBancariaDAO;
import DataAccess.DAO.TransaccionDAO;
import DataAccess.DTO.CuentaBancariaDTO;
import DataAccess.DTO.TransaccionDTO;
import java.util.List;
import BussinesLogic.Entities.ATM.Retiro;
import DataAccess.DAO.TarjetaDAO;
import BussinesLogic.Entities.BancoLogic.ValidarTransaccion;
import DataAccess.DTO.TarjetaDTO;
public class App {
    public static void main(String[] args) {
        try {
           CuentaBancariaDAO cuentaBancariaDAO = new CuentaBancariaDAO();
           CuentaBancariaDTO cuentaBancaria = cuentaBancariaDAO.readBycta("40344247");
           System.out.println(cuentaBancaria.getId_persona());
           
           ValidarTransaccion validarTransaccion = new ValidarTransaccion();
           validarTransaccion.cuentaDeRecepcionExiste(cuentaBancaria.getId_persona());
           //CuentaBancariaDTO cuentaBancaria2 = cuentaBancariaDAO.readByuser(cuentaBancaria.getId_persona());
           
          // System.out.println(cuentaBancaria2);
        } catch (Exception e) {
            
        }

        try {
            //Inicializar la API
            TransaccionDAO transaccionDAO = new TransaccionDAO();
            List<TransaccionDTO> transacciones = transaccionDAO.obtenerUltimasTransacciones(5);
            System.out.println(transacciones);
            // Crear y mostrar la ventana de login


            LoginPanel loginFrame = new LoginPanel();
            loginFrame.setVisible(true);



        } catch (Exception e) {
            System.out.println("Error al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }


    }
}
