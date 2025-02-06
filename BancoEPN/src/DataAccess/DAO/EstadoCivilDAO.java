package DataAccess.DAO;

import DataAccess.IDAO;
import DataAccess.SQLiteDataHelper;
import DataAccess.DTO.EstadoCivilDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoCivilDAO extends SQLiteDataHelper implements IDAO<EstadoCivilDTO> {
    private Connection connection;

    public EstadoCivilDAO() throws SQLException {
        this.connection = openConnection();
    }

    public EstadoCivilDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public EstadoCivilDTO readBy(Integer id) throws Exception {
        EstadoCivilDTO estadoCivil = null;
        String query = "SELECT * FROM EstadoCivil WHERE id_estado_civil = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                estadoCivil = new EstadoCivilDTO(
                    rs.getInt("id_estado_civil"),
                    rs.getString("nombre_estado_civil")
                );
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer estado civil: " + e.getMessage());
        }
        return estadoCivil;
    }

    @Override
    public List<EstadoCivilDTO> readAll() throws Exception {
        List<EstadoCivilDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM EstadoCivil";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new EstadoCivilDTO(
                    rs.getInt("id_estado_civil"),
                    rs.getString("nombre_estado_civil")
                ));
            }
        } catch (SQLException e) {
            throw new Exception("Error al listar estados civiles: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean create(EstadoCivilDTO estadoCivil) throws Exception {
        String query = "INSERT INTO EstadoCivil (nombre_estado_civil) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, estadoCivil.getNombre_estado_civil());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al insertar estado civil: " + e.getMessage());
        }
    }

    @Override
    public boolean update(EstadoCivilDTO estadoCivil) throws Exception {
        String query = "UPDATE EstadoCivil SET nombre_estado_civil = ? WHERE id_estado_civil = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, estadoCivil.getNombre_estado_civil());
            stmt.setInt(2, estadoCivil.getId_estado_civil());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar estado civil: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "DELETE FROM EstadoCivil WHERE id_estado_civil = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar estado civil: " + e.getMessage());
        }
    }
}