package DataAccess.DAO;

import DataAccess.IDAO;
import DataAccess.SQLiteDataHelper;
import DataAccess.DTO.SesionDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SesionDAO extends SQLiteDataHelper implements IDAO<SesionDTO> {
    private Connection connection;

    public SesionDAO() throws SQLException {
        this.connection = openConnection();
    }

    public SesionDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public SesionDTO readBy(Integer id) throws Exception {
        SesionDTO sesion = null;
        String query = "SELECT * FROM Sesion WHERE sesion_id = ? AND activo = 1";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                sesion = new SesionDTO(
                        rs.getInt("sesion_id"),
                        rs.getInt("persona_id"),
                        rs.getString("usuario"),
                        rs.getString("contrasenia"),
                        rs.getString("fecha_inicio"),
                        rs.getString("fecha_fin"),
                        rs.getString("ip_origen"),
                        rs.getString("estado"),
                        rs.getString("fechaCreacion"),
                        rs.getString("fechaModificacion"),
                        rs.getBoolean("activo")
                );
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer sesión: " + e.getMessage());
        }
        return sesion;
    }

    @Override
    public List<SesionDTO> readAll() throws Exception {
        List<SesionDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM Sesion WHERE activo = 1";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new SesionDTO(
                        rs.getInt("sesion_id"),
                        rs.getInt("persona_id"),
                        rs.getString("usuario"),
                        rs.getString("contrasenia"),
                        rs.getString("fecha_inicio"),
                        rs.getString("fecha_fin"),
                        rs.getString("ip_origen"),
                        rs.getString("estado"),
                        rs.getString("fechaCreacion"),
                        rs.getString("fechaModificacion"),
                        rs.getBoolean("activo")
                ));
            }
        } catch (SQLException e) {
            throw new Exception("Error al listar sesiones: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean create(SesionDTO sesion) throws Exception {
        String query = "INSERT INTO Sesion (persona_id, usuario, contrasenia, fecha_inicio, fecha_fin, ip_origen, estado, fechaCreacion, fechaModificacion, activo) VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, sesion.getPersonaId());
            stmt.setString(2, sesion.getUsuario());
            stmt.setString(3, sesion.getContrasenia());
            stmt.setString(4, sesion.getFechaInicio());
            stmt.setString(5, sesion.getFechaFin());
            stmt.setString(6, sesion.getIpOrigen());
            stmt.setString(7, sesion.getEstado());
            stmt.setBoolean(8, sesion.getActivo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al insertar sesión: " + e.getMessage());
        }
    }

    @Override
    public boolean update(SesionDTO sesion) throws Exception {
        String query = "UPDATE Sesion SET usuario = ?, contrasenia = ?, fecha_fin = ?, ip_origen = ?, estado = ?, fechaModificacion = CURRENT_TIMESTAMP, activo = ? WHERE sesion_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, sesion.getUsuario());
            stmt.setString(2, sesion.getContrasenia());
            stmt.setString(3, sesion.getFechaFin());
            stmt.setString(4, sesion.getIpOrigen());
            stmt.setString(5, sesion.getEstado());
            stmt.setBoolean(6, sesion.getActivo());
            stmt.setInt(7, sesion.getSesionId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar sesión: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Sesion SET activo = 0, fechaModificacion = CURRENT_TIMESTAMP WHERE sesion_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar sesión: " + e.getMessage());
        }
    }
}
