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
import DataAccess.DTO.TipoTarjetaDTO;

public class TipoTarjetaDAO extends SQLiteDataHelper implements IDAO<TipoTarjetaDTO> {
    private Connection connection;

    public TipoTarjetaDAO() throws SQLException {
        this.connection = openConnection();
    }

    public TipoTarjetaDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public TipoTarjetaDTO readBy(Integer id) throws Exception {
        TipoTarjetaDTO tipotarjeta = null;
        String query = "SELECT * FROM TipoTarjeta WHERE id_tipo_tarjeta = ? AND estado = 1";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tipotarjeta = new TipoTarjetaDTO(
                        rs.getInt("id_tipo_tarjeta"),
                        rs.getString("nombre_tipo_tarjeta"),
                        rs.getString("fechaCreacion"),
                        rs.getString("fechaModificacion"),
                        rs.getString("estado") 
                );
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer tipotarjeta: " + e.getMessage());
        }
        return tipotarjeta;
    }

    @Override
    public List<TipoTarjetaDTO> readAll() throws Exception {
        List<TipoTarjetaDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM TipoTarjeta WHERE estado = 1";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new TipoTarjetaDTO(
                    rs.getInt("id_tipo_tarjeta"),
                    rs.getString("nombre_tipo_tarjeta"),
                    rs.getString("fechaCreacion"),
                    rs.getString("fechaModificacion"),
                    rs.getString("estado") 
                ));
            }
        } catch (SQLException e) {
            throw new Exception("Error al lista tipotarjetas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean create(TipoTarjetaDTO tarjeta) throws Exception {
        String query = "INSERT INTO Tarjeta (nombre_tipo_tarjeta, fechaCreacion, fechaModificacion, estado) VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tarjeta.getNombre_tipo_tarjeta());
            stmt.setString(5, tarjeta.getFechaCreacion());
            stmt.setString(5, tarjeta.getFechaModificacion());
            stmt.setString(6, tarjeta.getEstado());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al insertar tarjeta: " + e.getMessage());
        }
    }

    @Override
    public boolean update(TipoTarjetaDTO tarjeta) throws Exception {
        String query = "UPDATE TipoTarjeta SET nombre_tipo_tarjeta = ?,  fechaCreacion = CURRENT_TIMESTAMP, fechaModificacion = CURRENT_TIMESTAMP, estado = ?  WHERE id_tipo_tarjeta = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tarjeta.getNombre_tipo_tarjeta());
            stmt.setString(5, tarjeta.getFechaCreacion());
            stmt.setString(5, tarjeta.getFechaModificacion());
            stmt.setString(6, tarjeta.getEstado());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar tipotarjeta: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE TipoTarjeta SET estado = 0, fechaModificacion = CURRENT_TIMESTAMP WHERE id_tipo_tarjeta = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar Tipotarjeta: " + e.getMessage());
        }
    }
}
