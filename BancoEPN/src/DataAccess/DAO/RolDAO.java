package DataAccess.DAO;

import DataAccess.IDAO;
import DataAccess.SQLiteDataHelper;
import DataAccess.DTO.RolDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolDAO extends SQLiteDataHelper implements IDAO<RolDTO> {
    private Connection connection;

    public RolDAO() throws SQLException {
        this.connection = openConnection();
    }

    public RolDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public RolDTO readBy(Integer id) throws Exception {
        RolDTO rol = null;
        String query = "SELECT * FROM Rol WHERE id_rol = ? AND estado = 'A'";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                rol = new RolDTO(
                    rs.getInt("id_rol"),
                    rs.getString("nombre_rol"),
                    rs.getString("fechaCreacion"),
                    rs.getString("fechaModificacion"),
                    rs.getString("estado")
                );
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer rol: " + e.getMessage());
        }
        return rol;
    }

    @Override
    public List<RolDTO> readAll() throws Exception {
        List<RolDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM Rol WHERE estado = 'A'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new RolDTO(
                    rs.getInt("id_rol"),
                    rs.getString("nombre_rol"),
                    rs.getString("fechaCreacion"),
                    rs.getString("fechaModificacion"),
                    rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            throw new Exception("Error al listar roles: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean create(RolDTO rol) throws Exception {
        String query = "INSERT INTO Rol (nombre_rol, fechaCreacion, fechaModificacion, estado) VALUES (?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A')";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, rol.getNombre_rol());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al insertar rol: " + e.getMessage());
        }
    }

    @Override
    public boolean update(RolDTO rol) throws Exception {
        String query = "UPDATE Rol SET nombre_rol = ?, fechaModificacion = CURRENT_TIMESTAMP WHERE id_rol = ? AND estado = 'A'";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, rol.getNombre_rol());
            stmt.setInt(2, rol.getId_rol());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar rol: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Rol SET estado = 'I', fechaModificacion = CURRENT_TIMESTAMP WHERE id_rol = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar rol: " + e.getMessage());
        }
    }
}