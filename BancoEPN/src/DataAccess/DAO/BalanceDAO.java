package DataAccess.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DataAccess.IDAO;
import DataAccess.SQLiteDataHelper;
import DataAccess.DTO.BalanceDTO;


public class BalanceDAO extends SQLiteDataHelper implements IDAO<BalanceDTO> {
    private Connection connection;

public BalanceDAO() throws Exception {
    this.connection = openConnection();
}
public BalanceDAO(Connection connection) {
    this.connection = connection;
}
   @Override
   public BalanceDTO readBy(Integer id) throws Exception {
    String query = "SELECT * FROM balance WHERE balance_id = ?";
    try (PreparedStatement stmt = openConnection().prepareStatement(query)) {
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return new BalanceDTO(
                        rs.getInt("balance_id"),
                        rs.getInt("persona_id"),
                        rs.getDouble("balance_to"),
                        rs.getString("fecha_act")
                 
                );
            }
        }
    } catch (SQLException e) {
        throw e;
    }
    return null;
}
@Override
    public List<BalanceDTO>readAll() throws Exception {
        List<BalanceDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM Balance";
        try (Statement stmt = openConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new BalanceDTO(
                        rs.getInt("balance_id"),
                        rs.getInt("persona_id"),
                        rs.getDouble("balance_to"),
                        rs.getString("fecha_act")
                ));
              
            }
        }catch (SQLException e) {
            throw e;//new Exception( e.getMessage(), getClass().getName(), "delete()");
    }
    return lista;
    }
    @Override
    public boolean create(BalanceDTO balance) throws Exception {
        String query = "INSERT INTO Balance (balance_id, persona_id, balance_to, fecha_act) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = openConnection().prepareStatement(query)) {
            stmt.setInt(1, balance.getBalance_id());
            stmt.setInt(2, balance.getPersona_id());
            stmt.setDouble(3, balance.getBalance_to());
            stmt.setString(4, balance.getFecha_act());

            stmt.executeUpdate();
            return true;
        }catch (SQLException e) {
            throw e;//new Exception( e.getMessage(), getClass().getName(), "delete()");
    }
}
@Override
    public boolean update(BalanceDTO balance) throws Exception {
        String query = "UPDATE Balance SET persona_id = ? , balance_to = ? , fecha_act = ?  WHERE balance_id = ?";
        try (PreparedStatement stmt =openConnection().prepareStatement(query)) {
            stmt.setInt(1, balance.getBalance_id());
            stmt.setInt(2, balance.getPersona_id());
            stmt.setDouble(3, balance.getBalance_to());
            stmt.setString(4, balance.getFecha_act());
            stmt.executeUpdate();
            return false;
        } catch (SQLException e) {
            throw e;//new Exception( e.getMessage(), getClass().getName(), "delete()");
        }
                
    }
    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Balance SET Estado = ? WHERE balance_id = ?";
        try {
            Connection        conn  = this.connection;
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "X");
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            throw e;//new Exception( e.getMessage(), getClass().getName(), "delete()");
        }
        
    }
}
