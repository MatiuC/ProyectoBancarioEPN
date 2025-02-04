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
import DataAccess.DTO.FranquiciaDTO;


public class FranquiciaDAO extends SQLiteDataHelper implements IDAO<FranquiciaDTO> {
     private Connection connection;

    public FranquiciaDAO() throws SQLException {
        this.connection = openConnection();
    }

    public FranquiciaDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public FranquiciaDTO readBy(Integer id) throws Exception {
        FranquiciaDTO franquicia = null;
        String query = "SELECT * FROM Franquicia WHERE id_franquicia = ? AND estado = 1";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                franquicia = new FranquiciaDTO(
                        rs.getInt("id_franquicia"),
                        rs.getString("nombre_franquicia"),
                        rs.getString("fechaCreacion"),
                        rs.getString("fechaModificacion"),
                        rs.getString("estado") 
                );
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer franquicia: " + e.getMessage());
        }
        return franquicia;
    }

    @Override
    public List<FranquiciaDTO> readAll() throws Exception {
        List<FranquiciaDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM Franquicia WHERE estado = 1";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new FranquiciaDTO(
                    rs.getInt("id_franquicia"),
                    rs.getString("nombre_franquicia"),
                    rs.getString("fechaCreacion"),
                    rs.getString("fechaModificacion"),
                    rs.getString("estado") 
                ));
            }
        } catch (SQLException e) {
            throw new Exception("Error al lista franquicia: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean create(FranquiciaDTO franquicia) throws Exception {
        String query = "INSERT INTO Franquicia (nombre_franquicia, fechaCreacion, fechaModificacion, estado) VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, franquicia.getNombre_franquicia());
            stmt.setString(5, franquicia.getFechaCreacion());
            stmt.setString(5, franquicia.getFechaModificacion());
            stmt.setString(6, franquicia.getEstado());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al insertar franquicia: " + e.getMessage());
        }
    }

    @Override
    public boolean update(FranquiciaDTO franquicia) throws Exception {
        String query = "UPDATE Franquicia SET nombre_franquicia = ?,  fechaCreacion = CURRENT_TIMESTAMP, fechaModificacion = CURRENT_TIMESTAMP, estado = ?  WHERE id_franquicia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, franquicia.getNombre_franquicia());
            stmt.setString(5, franquicia.getFechaCreacion());
            stmt.setString(5, franquicia.getFechaModificacion());
            stmt.setString(6, franquicia.getEstado());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar Franquicia: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Franquicia SET estado = 0, fechaModificacion = CURRENT_TIMESTAMP WHERE id_franquicia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar franquicia: " + e.getMessage());
        }
    }
}
