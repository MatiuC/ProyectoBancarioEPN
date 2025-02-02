
import DataAccess.SQLiteDataHelper;
import DataAccess.DAO.SesionDAO;
import DataAccess.DAO.TarjetaDAO;
import DataAccess.DAO.TransaccionDAO;
import DataAccess.DTO.SesionDTO;
import DataAccess.DTO.TarjetaDTO;
import DataAccess.DTO.TransaccionDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
       
        try {
            SesionDAO sDAO = new SesionDAO();
            for (SesionDTO s : sDAO.readAll()) {
                System.out.println(s.toString());
            }

            //TarjetaDAO tDAO = new TarjetaDAO();
            //for (TarjetaDTO t : tDAO.readAll()) {
            //    System.out.println(t.toString());
            //}
//
            //TransaccionDAO tDAO = new TransaccionDAO();
            //for (TransaccionDTO tr : tDAO.readAll()) {
            //    System.out.println(tr.toString());
            //}
//
        } catch (SQLException e) {
            System.out.println("Error de conexión con la base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error general: " + e.getMessage());
        }
    }
}
