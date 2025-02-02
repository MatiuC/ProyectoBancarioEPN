
import DataAccess.DAO.VistaSesionActivaDAO;
import DataAccess.DTO.VistaSesionActivaDTO;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
       
      /*  try {
            SesionDAO sDAO = new SesionDAO();
            for (SesionDTO s : sDAO.readAll()) {
                System.out.println(s.toString());
            }

        } catch (SQLException e) {
            System.out.println("Error de conexión con la base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error general: " + e.getMessage());
        }
    */

    try {
        VistaSesionActivaDAO vDAO = new VistaSesionActivaDAO();
        for (VistaSesionActivaDTO s : vDAO.readAll()) {
            System.out.println(s.toString());
        }

    } catch (SQLException e) {
        System.out.println("Error de conexión con la base de datos: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Error general: " + e.getMessage());
    }

}
}