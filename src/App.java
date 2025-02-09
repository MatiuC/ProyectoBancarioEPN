import DataAccess.DAO.PersonaDAO;
import DataAccess.DTO.PersonaDTO;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
       
      

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

    

}
}