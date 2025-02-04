package DataAccess.DAO;
import DataAccess.DTO.cuentaCreditoDTO;
import DataAccess.IDAO;
import DataAccess.SQLiteDataHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class cuentaCreditoDAO extends SQLiteDataHelper implements IDAO<cuentaCreditoDTO>{
    private Connection connection;

    public cuentaCreditoDAO() throws Exception {
    this.connection = openConnection();
    }
    public cuentaCreditoDAO(Connection connection) {
    this.connection = connection;
    }

    @Override
    public cuentaCreditoDTO readBy(Integer id) throws Exception {
        cuentaCreditoDTO cuentaCredito = null;
        String query = "SELECT * FROM cuentaCredito WHERE id_cuentaCredito = ?" + id.toString();
        try (PreparedStatement stmt = openConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cuentaCredito = new cuentaCreditoDTO(
                        rs.getInt("id_cuentaCredito"),
                        rs.getString("numeroCuenta"),
                        rs.getInt("id_persona"),
                        rs.getFloat("saldo_usado"),
                        rs.getFloat("limiteCredito"),
                        rs.getString("fechaCreacion"),
                        rs.getString("fechaModificacion"),
                        rs.getString("estado")
                    );
                }
            }
        } catch (SQLException e) {
            throw e;
        }    
        return cuentaCredito;
    }

    @Override
    public List<cuentaCreditoDTO> readAll() throws Exception {
        List<cuentaCreditoDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM cuentaCredito";
        try (Statement stmt = openConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new cuentaCreditoDTO(
                    rs.getInt("id_cuentaCredito"),
                    rs.getString("numeroCuenta"),
                    rs.getInt("id_persona"),
                    rs.getFloat("saldo_usado"),
                    rs.getFloat("limiteCredito"),
                    rs.getString("fechaCreacion"),
                    rs.getString("fechaModificacion"),
                    rs.getString("estado")
                ));
              
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    @Override
    public boolean create(cuentaCreditoDTO cuentaCredito) throws Exception {
        String query = "INSERT INTO cuentaCredito (numeroCuenta, id_persona, saldo_usado, limiteCredito, fechaCreacion, fechaModificacion, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = openConnection().prepareStatement(query)) {
            stmt.setString(1, cuentaCredito.getnumeroCuenta());
            stmt.setInt(2, cuentaCredito.getId_persona());
            stmt.setFloat(3, cuentaCredito.getsaldo_usado());
            stmt.setFloat(4, cuentaCredito.getlimiteCredito());
            stmt.setString(5, cuentaCredito.getfechaCreacion());
            stmt.setString(6, cuentaCredito.getfechaModificacion());
            stmt.setString(7, cuentaCredito.getEstado());
            stmt.execute();
        } catch (SQLException e) {
            throw e;
        }
        return true;
    }

    @Override
    public boolean update(cuentaCreditoDTO cuentaCredito) throws Exception {
        String query = "UPDATE cuentaCredito SET numeroCuenta = ? , id_persona = ? , saldo_usado = ? , limiteCredito = ? , fechaCreacion = ? , fechaModificacion = ? , estado = ?  WHERE id_cuentaCredito = ?";
        try (PreparedStatement stmt = openConnection().prepareStatement(query)) {
            stmt.setString(1, cuentaCredito.getnumeroCuenta());
            stmt.setInt(2, cuentaCredito.getId_persona());
            stmt.setFloat(3, cuentaCredito.getsaldo_usado());
            stmt.setFloat(4, cuentaCredito.getlimiteCredito());
            stmt.setString(5, cuentaCredito.getfechaCreacion());
            stmt.setString(6, cuentaCredito.getfechaModificacion());
            stmt.setString(7, cuentaCredito.getEstado());
            stmt.setInt(8, cuentaCredito.getId_cuentaCredito());
            stmt.execute();
        } catch (SQLException e) {
            throw e;
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE cuentaCredito SET estado = 'I' WHERE id_cuentaCredito = ?";
        try (PreparedStatement stmt = openConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw e;
        }
        return true;
    }

}
