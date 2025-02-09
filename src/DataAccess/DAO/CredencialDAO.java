package DataAccess.DAO;

import DataAccess.IDAO;
import DataAccess.SQLiteDataHelper;
import DataAccess.DTO.CredencialDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CredencialDAO extends SQLiteDataHelper implements IDAO<CredencialDTO> {
    private Connection connection;

    public CredencialDAO() throws SQLException {
        this.connection = openConnection();
    }

    public CredencialDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public CredencialDTO readBy(Integer id) throws Exception {
        CredencialDTO credencial = null;
        String query = "SELECT * FROM credenciales WHERE id_credenciales = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                credencial = new CredencialDTO(
                        rs.getInt("id_credenciales"),
                        rs.getInt("id_persona"),
                        rs.getString("usuario"),
                        rs.getString("pass"),
                        rs.getString("fechaCreacion"),
                        rs.getString("fechaModificacion"),
                        rs.getString("estado")
                );
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer credenciales: " + e.getMessage());
        }
        return credencial;
    }

    @Override
    public List<CredencialDTO> readAll() throws Exception {
        List<CredencialDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM credenciales";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new CredencialDTO(
                        rs.getInt("id_credenciales"),
                        rs.getInt("id_persona"),
                        rs.getString("usuario"),
                        rs.getString("pass"),
                        rs.getString("fechaCreacion"),
                        rs.getString("fechaModificacion"),
                        rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            throw new Exception("Error al listar credenciales: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean create(CredencialDTO credencial) throws Exception {
        String query = "INSERT INTO credenciales (id_persona, usuario, pass, fechaCreacion, fechaModificacion, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, credencial.getIdPersona());
            stmt.setString(2, credencial.getUsuario());
            stmt.setString(3, credencial.getPass());
            stmt.setString(4, credencial.getFechaCreacion());
            stmt.setString(5, credencial.getFechaModificacion());
            stmt.setString(6, credencial.getEstado());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al insertar credenciales: " + e.getMessage());
        }
    }

    @Override
    public boolean update(CredencialDTO credencial) throws Exception {
        String query = "UPDATE credenciales SET usuario = ?, pass = ?, fechaModificacion = ?, estado = ? WHERE id_credenciales = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, credencial.getUsuario());
            stmt.setString(2, credencial.getPass());
            stmt.setString(3, credencial.getFechaModificacion());
            stmt.setString(4, credencial.getEstado());
            stmt.setInt(5, credencial.getIdCredenciales());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar credenciales: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "DELETE FROM credenciales WHERE id_credenciales = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar credenciales: " + e.getMessage());
        }
    }
}
