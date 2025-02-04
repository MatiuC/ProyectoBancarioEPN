
import DataAccess.DAO.CuentaBancariaDAO;
import DataAccess.DAO.PersonaDAO;
import DataAccess.DAO.cuentaCreditoDAO;
import DataAccess.DTO.CuentaBancariaDTO;
import DataAccess.DTO.PersonaDTO;
import DataAccess.DTO.cuentaCreditoDTO;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
       
      

    try {
        CuentaBancariaDAO vDAO = new CuentaBancariaDAO();
        for (CuentaBancariaDTO s : vDAO.readAll()) {
            System.out.println(s.toString());
        }

    } catch (SQLException e) {
        System.out.println("Error de conexión con la base de datos: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Error general: " + e.getMessage());
    }

    try {
        PersonaDAO pDAO = new PersonaDAO();
        for (PersonaDTO p : pDAO.readAll()) {
            System.out.println(p.toString());
        }

    } catch (SQLException e) {
        System.out.println("Error de conexión con la base de datos: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Error general: " + e.getMessage());
    }

    
    try {
        cuentaCreditoDAO cDAO = new cuentaCreditoDAO();
        for (cuentaCreditoDTO c : cDAO.readAll()) {
            System.out.println(c.toString());
        }
    } catch (SQLException e) {
        System.out.println("Error de conexión con la base de datos: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Error general: " + e.getMessage());
    }

}
}