package DataAccess.DAO;
import DataAccess.IDAO;
import DataAccess.SQLiteDataHelper;
import DataAccess.DTO.VistaBalanceDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VistaBalanceDAO {
    private Connection connection;

    public VistaBalanceDAO() throws SQLException {
        this.connection = openConnection();
    }
    public VistaBalanceDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<VistaBalanceDTO> readAll() throws SQLException {
        List<VistaBalanceDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM VistaBalance";
        try (Statement stmt = openConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new VistaBalanceDTO(
                        rs.getInt("persona_id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("balance_total")
                ));
            }
        }catch (SQLException e) {
            throw e;//new Exception( e.getMessage(), getClass().getName(), "delete()");
        }
        return lista;
    }

    @Override
    public VistaBalanceDTO readby(Integer id) throws SQLException {
        VistaBalanceDTO vistaBalance = null;
        String query = "SELECT * FROM VistaBalance WHERE persona_id = ?";
        try (PreparedStatement stmt = openConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new VistaBalanceDTO(
                            rs.getInt("persona_id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("balance_total")
                    );
                }
            }
        }catch (SQLException e) {
            throw e;//new Exception( e.getMessage(), getClass().getName(), "delete()");
        }
        return vistaBalance;
    }
}