
import UserInterface.Form.LoginPanel;
import BussinesLogic.ApiRequest.GetDatosCedula;
import DataAccess.DAO.CuentaBancariaDAO;
import DataAccess.DAO.TransaccionDAO;
import DataAccess.DTO.CuentaBancariaDTO;
import DataAccess.DTO.TransaccionDTO;
import java.util.List;
public class App {
    public static void main(String[] args) {


        try {
            CuentaBancariaDAO c = new CuentaBancariaDAO();
            CuentaBancariaDTO ct = c.readByuser(3);
            // System.out.println(ct);
            System.out.println(ct.getSaldo());
        } catch (Exception e) {
            // TODO: handle exception
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
