
import DataAccess.DAO.SesionDAO;
import DataAccess.DTO.SesionDTO;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
       
        try {
            SesionDAO sDAO = new SesionDAO();
            for (SesionDTO s : sDAO.readAll()) {
                System.out.println(s.toString());
            }

        } catch (SQLException e) {
            System.out.println("Error de conexión con la base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error general: " + e.getMessage());
        }
    }
}
