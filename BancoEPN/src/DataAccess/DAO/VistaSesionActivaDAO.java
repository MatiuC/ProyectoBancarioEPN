package DataAccess.DAO;
import DataAccess.IDAO;
import DataAccess.SQLiteDataHelper;
import DataAccess.DTO.VistaSesionActivaDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VistaSesionActivaDAO {

    private Connection connection;

    public VistaSesionActivaDAO() throws SQLException {
        this.connection = openConnection();
    }

    public VistaSesionActivaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<VistaSesionActivaDTO> readAll() throws SQLException {
        List<VistaSesionActivaDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM VistaSesionActiva";
        try (Statement stmt = openConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new VistaSesionActivaDTO(
                        rs.getInt("persona_id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("fecha_inicio"),
                        rs.getString("estado")
                ));
            }
        }catch (SQLException e) {
            throw e;//new Exception( e.getMessage(), getClass().getName(), "delete()");
        }
        return lista;
    }

    public VistaSesionActivaDTO readby(Integer id) throws SQLException {
        VistaSesionActivaDTO vistaSesionActiva = null;
        String query = "SELECT * FROM VistaSesionActiva WHERE persona_id = ?";
        try (PreparedStatement stmt = openConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return  new VistaSesionActivaDTO(
                            rs.getInt("persona_id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("fecha_inicio"),
                            rs.getString("estado")
                    );
                }
            }
        }catch (SQLException e) {
            throw e;//new Exception( e.getMessage(), getClass().getName(), "delete()");
        }
        return vistaSesionActiva;
    }

    

}
