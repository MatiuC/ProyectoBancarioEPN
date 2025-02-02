package DataAccess.DAO;


import java.sql.*;
import DataAccess.IDAO;
import DataAccess.SQLiteDataHelper;
import DataAccess.DTO.PersonaDTO;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO extends SQLiteDataHelper implements IDAO<PersonaDTO> {
private Connection connection;

public PersonaDAO() throws Exception {
    this.connection = openConnection();
}
public PersonaDAO(Connection connection) {
    this.connection = connection;
}
   @Override
   public PersonaDTO readBy(Integer id) throws Exception {
    String query = "SELECT * FROM Persona WHERE persona_id = ?";
    try (PreparedStatement stmt = openConnection().prepareStatement(query)) {
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return new PersonaDTO(
                        rs.getInt("persona_id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("direccion"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getBoolean("estado")
                );
            }
        }
    } catch (SQLException e) {
        throw e;
    }
    return null;
}
@Override
    public List<PersonaDTO>readAll() throws Exception {
        List<PersonaDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM Persona";
        try (Statement stmt = openConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new PersonaDTO(
                    rs.getInt("persona_id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("fecha_nacimiento"),
                    rs.getString("direccion"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getBoolean("estado")
                ));
              
            }
        }catch (SQLException e) {
            throw e;//new Exception( e.getMessage(), getClass().getName(), "delete()");
    }
    return lista;
    }
    @Override
    public boolean create(PersonaDTO persona) throws Exception {
        String query = "INSERT INTO Persona (persona_id, nombre, apellido, fecha_nacimiento, direccion, email, telefono, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = openConnection().prepareStatement(query)) {
            stmt.setInt(1, persona.getPersona_id());
            stmt.setString(2, persona.getNombre());
            stmt.setString(3, persona.getApellido());
            stmt.setString(4, persona.getFecha_nacimiento());
            stmt.setString(5, persona.getDireccion());
            stmt.setString(6, persona.getTelefono());
            stmt.setBoolean(7, persona.isEstado());
            stmt.executeUpdate();
            return true;
        }catch (SQLException e) {
            throw e;//new Exception( e.getMessage(), getClass().getName(), "delete()");
    }
}
@Override
    public boolean update(PersonaDTO persona) throws Exception {
        String query = "UPDATE Persona SET nombre = ? , apellido = ? , fecha_nacimiento = ? , direccion = ? , email = ? , telefono = ? , estado = ? WHERE persona_id = ?";
        try (PreparedStatement stmt =openConnection().prepareStatement(query)) {
            stmt.setString(2, persona.getNombre());
            stmt.setString(3, persona.getApellido());
            stmt.setString(4, persona.getFecha_nacimiento());
            stmt.setString(5, persona.getDireccion());
            stmt.setString(6, persona.getTelefono());
            stmt.setBoolean(7, persona.isEstado());
            stmt.executeUpdate();
            return false;
        } catch (SQLException e) {
            throw e;//new Exception( e.getMessage(), getClass().getName(), "delete()");
        }
                
    }
    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Persona SET Estado = ? WHERE persona_id = ?";
        try {
            Connection        conn  = this.connection;
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "X");
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            throw e;//new Exception( e.getMessage(), getClass().getName(), "delete()");
        }
        
    }
}
