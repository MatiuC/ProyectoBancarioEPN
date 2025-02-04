
import DataAccess.DAO.CredencialDAO;
import DataAccess.DAO.SesionDAO;
import DataAccess.DAO.TipoTransaccionDAO;
import DataAccess.DAO.TransaccionDAO;
import DataAccess.DAO.VistaSesionActivaDAO;
import DataAccess.DTO.CredencialDTO;
import DataAccess.DTO.SesionDTO;
import DataAccess.DTO.TipoTransaccionDTO;
import DataAccess.DTO.TransaccionDTO;
import DataAccess.DTO.VistaSesionActivaDTO;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
    try {
        TransaccionDAO sDAO = new TransaccionDAO();
        for (TransaccionDTO s : sDAO.readAll()) {
            System.out.println(s.toString());
        }
         TipoTransaccionDAO vDAO = new TipoTransaccionDAO();
        for (TipoTransaccionDTO v : vDAO.readAll()) {
            System.out.println(v.toString());
        }
 
        CredencialDAO cDAO = new CredencialDAO();
        for (CredencialDTO c : cDAO.readAll()) {
            System.out.println(c.toString());
        }

    } catch (SQLException e) {
        System.out.println("Error de conexión con la base de datos: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Error general: " + e.getMessage());
    }
 }

}