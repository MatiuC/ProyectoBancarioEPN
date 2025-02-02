package DataAccess.DAO;

import DataAccess.IDAO;
import DataAccess.SQLiteDataHelper;
import DataAccess.DTO.TransaccionDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaccionDAO extends SQLiteDataHelper implements IDAO<TransaccionDTO> {
    private Connection connection;

    public TransaccionDAO() throws SQLException {
        this.connection = openConnection();
    }

    public TransaccionDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public TransaccionDTO readBy(Integer id) throws Exception {
        TransaccionDTO transaccion = null;
        String query = "SELECT * FROM Transaccion WHERE transaccion_id = ? AND activo = 1";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                transaccion = new TransaccionDTO(
                        rs.getInt("transaccion_id"),
                        rs.getInt("origen_tarjeta_id"),
                        rs.getInt("destino_tarjeta_id"),
                        rs.getDouble("monto"),
                        rs.getString("tipo_transaccion"),
                        rs.getString("fecha_transaccion"),
                        rs.getString("fechaCreacion"),
                        rs.getString("fechaModificacion"),
                        rs.getBoolean("activo")
                );
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer transacción: " + e.getMessage());
        }
        return transaccion;
    }

    @Override
    public List<TransaccionDTO> readAll() throws Exception {
        List<TransaccionDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM Transaccion WHERE activo = 1";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new TransaccionDTO(
                        rs.getInt("transaccion_id"),
                        rs.getInt("origen_tarjeta_id"),
                        rs.getInt("destino_tarjeta_id"),
                        rs.getDouble("monto"),
                        rs.getString("tipo_transaccion"),
                        rs.getString("fecha_transaccion"),
                        rs.getString("fechaCreacion"),
                        rs.getString("fechaModificacion"),
                        rs.getBoolean("activo")
                ));
            }
        } catch (SQLException e) {
            throw new Exception("Error al listar transacciones: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean create(TransaccionDTO transaccion) throws Exception {
        String query = "INSERT INTO Transaccion (origen_tarjeta_id, destino_tarjeta_id, monto, tipo_transaccion, fecha_transaccion, fechaCreacion, fechaModificacion, activo) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, transaccion.getOrigenTarjetaId());
            stmt.setInt(2, transaccion.getDestinoTarjetaId());
            stmt.setDouble(3, transaccion.getMonto());
            stmt.setString(4, transaccion.getTipoTransaccion());
            stmt.setString(5, transaccion.getFechaTransaccion());
            stmt.setBoolean(6, transaccion.getActivo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al insertar transacción: " + e.getMessage());
        }
    }

    @Override
    public boolean update(TransaccionDTO transaccion) throws Exception {
        String query = "UPDATE Transaccion SET origen_tarjeta_id = ?, destino_tarjeta_id = ?, monto = ?, tipo_transaccion = ?, fechaModificacion = CURRENT_TIMESTAMP, activo = ? WHERE transaccion_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, transaccion.getOrigenTarjetaId());
            stmt.setInt(2, transaccion.getDestinoTarjetaId());
            stmt.setDouble(3, transaccion.getMonto());
            stmt.setString(4, transaccion.getTipoTransaccion());
            stmt.setBoolean(5, transaccion.getActivo());
            stmt.setInt(6, transaccion.getTransaccionId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar transacción: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Transaccion SET activo = 0, fechaModificacion = CURRENT_TIMESTAMP WHERE transaccion_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar transacción: " + e.getMessage());
        }
    }
}
