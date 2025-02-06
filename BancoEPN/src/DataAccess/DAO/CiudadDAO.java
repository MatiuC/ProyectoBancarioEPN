package DataAccess.DAO;

import DataAccess.IDAO;
import DataAccess.SQLiteDataHelper;
import DataAccess.DTO.CiudadDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CiudadDAO extends SQLiteDataHelper implements IDAO<CiudadDTO> {
    private Connection connection;

    public CiudadDAO() throws SQLException {
        this.connection = openConnection();
    }

    public CiudadDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public CiudadDTO readBy(Integer id) throws Exception {
        CiudadDTO ciudad = null;
        String query = "SELECT * FROM Ciudad WHERE id_ciudad = ? AND estado = 'A'";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ciudad = new CiudadDTO(
                    rs.getInt("id_ciudad"),
                    rs.getString("nombre_ciudad"),
                    rs.getString("fechaCreacion"),
                    rs.getString("fechaModificacion"),
                    rs.getString("estado")
                );
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer ciudad: " + e.getMessage());
        }
        return ciudad;
    }

    @Override
    public List<CiudadDTO> readAll() throws Exception {
        List<CiudadDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM Ciudad WHERE estado = 'A'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new CiudadDTO(
                    rs.getInt("id_ciudad"),
                    rs.getString("nombre_ciudad"),
                    rs.getString("fechaCreacion"),
                    rs.getString("fechaModificacion"),
                    rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            throw new Exception("Error al listar ciudades: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean create(CiudadDTO ciudad) throws Exception {
        String query = "INSERT INTO Ciudad (nombre_ciudad, fechaCreacion, fechaModificacion, estado) VALUES (?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A')";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, ciudad.getNombre_ciudad());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al insertar ciudad: " + e.getMessage());
        }
    }

    @Override
    public boolean update(CiudadDTO ciudad) throws Exception {
        String query = "UPDATE Ciudad SET nombre_ciudad = ?, fechaModificacion = CURRENT_TIMESTAMP WHERE id_ciudad = ? AND estado = 'A'";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, ciudad.getNombre_ciudad());
            stmt.setInt(2, ciudad.getId_ciudad());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar ciudad: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Ciudad SET estado = 'I', fechaModificacion = CURRENT_TIMESTAMP WHERE id_ciudad = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar ciudad: " + e.getMessage());
        }
    }
}