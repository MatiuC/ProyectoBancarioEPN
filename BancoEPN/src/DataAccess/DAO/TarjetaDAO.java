package DataAccess.DAO;

import DataAccess.IDAO;
import DataAccess.SQLiteDataHelper;
import DataAccess.DTO.TarjetaDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarjetaDAO extends SQLiteDataHelper implements IDAO<TarjetaDTO> {
    private Connection connection;

    public TarjetaDAO() throws SQLException {
        this.connection = openConnection();
    }

    public TarjetaDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public TarjetaDTO readBy(Integer id) throws Exception {
        TarjetaDTO tarjeta = null;
        String query = "SELECT * FROM Tarjeta WHERE tarjeta_id = ? AND activo = 1";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tarjeta = new TarjetaDTO(
                        rs.getInt("tarjeta_id"),
                        rs.getInt("persona_id"),
                        rs.getString("numero_tarjeta"),
                        rs.getString("fecha_expiracion"),
                        rs.getString("pin"),
                        rs.getDouble("saldo"),
                        rs.getString("estado"),
                        rs.getString("fechaCreacion"),
                        rs.getString("fechaModificacion"),
                        rs.getBoolean("activo")
                );
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer tarjeta: " + e.getMessage());
        }
        return tarjeta;
    }

    @Override
    public List<TarjetaDTO> readAll() throws Exception {
        List<TarjetaDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM Tarjeta WHERE activo = 1";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new TarjetaDTO(
                        rs.getInt("tarjeta_id"),
                        rs.getInt("persona_id"),
                        rs.getString("numero_tarjeta"),
                        rs.getString("fecha_expiracion"),
                        rs.getString("pin"),
                        rs.getDouble("saldo"),
                        rs.getString("estado"),
                        rs.getString("fechaCreacion"),
                        rs.getString("fechaModificacion"),
                        rs.getBoolean("activo")
                ));
            }
        } catch (SQLException e) {
            throw new Exception("Error al listar tarjetas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean create(TarjetaDTO tarjeta) throws Exception {
        String query = "INSERT INTO Tarjeta (persona_id, numero_tarjeta, fecha_expiracion, pin, saldo, estado, fechaCreacion, fechaModificacion, activo) VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, tarjeta.getPersonaId());
            stmt.setString(2, tarjeta.getNumeroTarjeta());
            stmt.setString(3, tarjeta.getFechaExpiracion());
            stmt.setString(4, tarjeta.getPin());
            stmt.setDouble(5, tarjeta.getSaldo());
            stmt.setString(6, tarjeta.getEstado());
            stmt.setBoolean(7, tarjeta.getActivo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al insertar tarjeta: " + e.getMessage());
        }
    }

    @Override
    public boolean update(TarjetaDTO tarjeta) throws Exception {
        String query = "UPDATE Tarjeta SET persona_id = ?, numero_tarjeta = ?, fecha_expiracion = ?, pin = ?, saldo = ?, estado = ?, fechaModificacion = CURRENT_TIMESTAMP, activo = ? WHERE tarjeta_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, tarjeta.getPersonaId());
            stmt.setString(2, tarjeta.getNumeroTarjeta());
            stmt.setString(3, tarjeta.getFechaExpiracion());
            stmt.setString(4, tarjeta.getPin());
            stmt.setDouble(5, tarjeta.getSaldo());
            stmt.setString(6, tarjeta.getEstado());
            stmt.setBoolean(7, tarjeta.getActivo());
            stmt.setInt(8, tarjeta.getTarjetaId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar tarjeta: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Tarjeta SET activo = 0, fechaModificacion = CURRENT_TIMESTAMP WHERE tarjeta_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar tarjeta: " + e.getMessage());
        }
    }
}
