package DataAccess.DAO;


import DataAccess.DTO.PersonaDTO;
import DataAccess.IDAO;
import DataAccess.SQLiteDataHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;



public class PersonaDAO extends SQLiteDataHelper implements IDAO<PersonaDTO> {
private Connection connection;

public PersonaDAO() throws SQLException {
    this.connection = openConnection();
}

public PersonaDAO(Connection connection) {
    this.connection = connection;
}
   @Override
   public PersonaDTO readBy(Integer id) throws Exception {
    String query = "SELECT * FROM Persona WHERE id_persona = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return new PersonaDTO(
                        rs.getInt("id_persona"),
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("sexo"),
                        rs.getString("estado_civil"),
                        rs.getString("ciudad"),
                        rs.getString("edad"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("direccion"),
                        rs.getString("correo"),
                        rs.getString("telefono"),
                        rs.getString("fechaCreacion"),
                        rs.getString("fechaModificacion"),
                        rs.getInt("Rol"),
                        rs.getString("estado")
                );
            }
        }
    } catch (SQLException e) {
        throw e;
    }
    return null;
}

    // Método para leer datos de la VISTA en lugar de la tabla completa
    public List<PersonaDTO> readFromView() throws Exception {
        List<PersonaDTO> lista = new ArrayList<>();
        String query = "SELECT cedula, nombre, apellido FROM Vista_Personas"; // Leer desde la vista
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new PersonaDTO(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("apellido")
                ));
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }


@Override
    public List<PersonaDTO>readAll() throws Exception {
        List<PersonaDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM Persona";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new PersonaDTO(
                        rs.getInt("id_persona"),
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("sexo"),
                        rs.getString("estado_civil"),
                        rs.getString("ciudad"),
                        rs.getString("edad"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("direccion"),
                        rs.getString("correo"),
                        rs.getString("telefono"),
                        rs.getString("fechaCreacion"),
                        rs.getString("fechaModificacion"),
                        rs.getInt("Rol"),
                        rs.getString("estado")
                ));
              
            }
        }catch (SQLException e) {
            throw e;
    }
    return lista;
    }
    @Override
    public boolean create(PersonaDTO persona) throws Exception {
        String query = "INSERT INTO Persona (id_persona, cedula, nombre, apellido, sexo, estado_civil, ciudad, edad, fecha_nacimiento, direccion, correo, telefono, fechaCreacion, fechaModificacion, estado, Rol) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(2, persona.getCedula());
            stmt.setString(3, persona.getNombre());
            stmt.setString(4, persona.getApellido());
            stmt.setString(5, persona.getSexo());
            stmt.setString(6, persona.getEstado_civil());
            stmt.setString(7, persona.getCiudad());
            stmt.setString(8, persona.getEdad());
            stmt.setDate(9, persona.getFecha_nacimiento());
            stmt.setString(10, persona.getDireccion());
            stmt.setString(11, persona.getcorreo());
            stmt.setString(12, persona.getTelefono());

            stmt.setString(13, persona.getFechaCreacion());
            stmt.setString(14, persona.getFechaModificacion());
            stmt.setString(15, persona.getEstado());
            stmt.setInt(16, persona.getRol());

            


            stmt.executeUpdate();
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    persona.setPersona_id(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al crear persona: " + e.getMessage());
        }
        return true;
    }
@Override
    public boolean update(PersonaDTO persona) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE Persona SET cedula = ?, nombre = ?, apellido = ?, sexo = ?, estado_civil = ?, ciudad = ?, edad = ?, fecha_nacimiento = ?, direccion = ?, correo = ?, telefono = ?, fechaModificacion = ?, estado = ?, Rol = ? WHERE id_persona = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, persona.getCedula());
            stmt.setString(2, persona.getNombre());
            stmt.setString(3, persona.getApellido());
            stmt.setString(4, persona.getSexo());
            stmt.setString(5, persona.getEstado_civil());
            stmt.setString(6, persona.getCiudad());
            stmt.setString(7, persona.getEdad());
            stmt.setDate(8, persona.getFecha_nacimiento());
            stmt.setString(9, persona.getDireccion());
            stmt.setString(10, persona.getcorreo());
            stmt.setString(11, persona.getTelefono());

            stmt.setString(12, dtf.format(now));
            stmt.setString(13, persona.getEstado());
            stmt.setInt(14, persona.getRol());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
                
    }
    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Persona FROM SET estado = ? WHERE id_persona = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.setString(2, "I");
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
        
    }
}
