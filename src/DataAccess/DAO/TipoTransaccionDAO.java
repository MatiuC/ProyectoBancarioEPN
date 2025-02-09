package DataAccess.DAO;

import DataAccess.IDAO;
import DataAccess.SQLiteDataHelper;
import DataAccess.DTO.TipoTransaccionDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoTransaccionDAO extends SQLiteDataHelper implements IDAO<TipoTransaccionDTO> {
    private Connection connection;

    public TipoTransaccionDAO() throws SQLException {
        this.connection = openConnection();
    }

    public TipoTransaccionDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public TipoTransaccionDTO readBy(Integer id) throws Exception {
        TipoTransaccionDTO tipoTransaccion = null;
        String query = "SELECT * FROM TipoTransaccion WHERE id_tipo_transaccion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tipoTransaccion = new TipoTransaccionDTO(
                        rs.getInt("id_tipo_transaccion"),
                        rs.getString("nombre_tipo_transaccion")
                );
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer tipo de transacción: " + e.getMessage());
        }
        return tipoTransaccion;
    }

    @Override
    public List<TipoTransaccionDTO> readAll() throws Exception {
        List<TipoTransaccionDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM TipoTransaccion";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new TipoTransaccionDTO(
                        rs.getInt("id_tipo_transaccion"),
                        rs.getString("nombre_tipo_transaccion")
                ));
            }
        } catch (SQLException e) {
            throw new Exception("Error al listar tipos de transacción: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean create(TipoTransaccionDTO tipoTransaccion) throws Exception {
        String query = "INSERT INTO TipoTransaccion (nombre_tipo_transaccion) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tipoTransaccion.getNombreTipoTransaccion());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al insertar tipo de transacción: " + e.getMessage());
        }
    }

    @Override
    public boolean update(TipoTransaccionDTO tipoTransaccion) throws Exception {
        String query = "UPDATE TipoTransaccion SET nombre_tipo_transaccion = ? WHERE id_tipo_transaccion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tipoTransaccion.getNombreTipoTransaccion());
            stmt.setInt(2, tipoTransaccion.getIdTipoTransaccion());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar tipo de transacción: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "DELETE FROM TipoTransaccion WHERE id_tipo_transaccion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar tipo de transacción: " + e.getMessage());
        }
    }
}
