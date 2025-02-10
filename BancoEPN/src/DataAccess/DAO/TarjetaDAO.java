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
        String query = "SELECT * FROM Tarjeta WHERE Id_tarjeta = ? AND estado = 1";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tarjeta = new TarjetaDTO(
                        rs.getInt("Id_tarjeta"),
                        rs.getString("numero_tarjeta"),
                        rs.getString("fecha_expedicion"),
                        rs.getString("fecha_vencimiento"),
                        rs.getString("cvv"),
                        rs.getInt("tipo_tarjeta"),
                        rs.getInt("id_franquicia"),
                        rs.getString("fechaCreacion"),
                        rs.getString("fechaModificacion"),
                        rs.getString("estado"),
                        rs.getInt("Persona"),
                        rs.getInt("id_cuentabancaria")
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
        String query = "SELECT * FROM Tarjeta WHERE estado = 1";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new TarjetaDTO(
                        rs.getInt("Id_tarjeta"),
                        rs.getString("numero_tarjeta"),
                        rs.getString("fecha_expedicion"),
                        rs.getString("fecha_vencimiento"),
                        rs.getString("cvv"),
                        rs.getInt("tipo_tarjeta"),
                        rs.getInt("id_franquicia"),
                        rs.getString("fechaCreacion"),
                        rs.getString("fechaModificacion"),
                        rs.getString("estado"),
                        rs.getInt("Persona"),
                        rs.getInt("id_cuentabancaria")
                ));
            }
        } catch (SQLException e) {
            throw new Exception("Error al listar tarjetas: " + e.getMessage());
        }
        return lista;
    }

        @Override
    public boolean create(TarjetaDTO tarjeta) throws Exception {
        String query = "INSERT INTO Tarjeta (numero_tarjeta, fecha_expedicion, fecha_vencimiento, cvv, tipo_tarjeta, franquicia, fechaCreacion, fechaModificacion, estado, Persona, id_cuentabancaria) VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tarjeta.getNumero_tarjeta());
            stmt.setString(2, tarjeta.getFecha_expedicion());
            stmt.setString(3, tarjeta.getFecha_vencimiento());
            stmt.setString(4, tarjeta.getCvv());
            stmt.setInt(5, tarjeta.getTipo_tarjeta());
            stmt.setInt(6, tarjeta.getId_franquicia());  // Aquí es donde estamos pasando el valor de franquicia
            stmt.setString(7, tarjeta.getEstado());
            stmt.setInt(8, tarjeta.getPersona());
            stmt.setInt(9, tarjeta.getId_cuentabancaria());  // Asegúrate de pasar el valor de id_cuentabancaria
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al insertar tarjeta: " + e.getMessage());
        }
    }

    @Override
    public boolean update(TarjetaDTO tarjeta) throws Exception {
        String query = "UPDATE Tarjeta SET numero_tarjeta = ?, fecha_expedicion = ?, fecha_vencimiento = ?, cvv = ?, tipo_tarjeta = ?, id_franquicia = ?, fechaCreacion = CURRENT_TIMESTAMP, fechaModificacion = CURRENT_TIMESTAMP, estado = ?, Persona = ?, id_cuentabancaria = ? WHERE Id_tarjeta = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tarjeta.getNumero_tarjeta());
            stmt.setString(2, tarjeta.getFecha_expedicion());
            stmt.setString(3, tarjeta.getFecha_vencimiento());
            stmt.setString(4, tarjeta.getCvv());
            stmt.setInt(5, tarjeta.getTipo_tarjeta());
            stmt.setInt(6, tarjeta.getId_franquicia());  // Asegúrate de que id_franquicia se pasa correctamente
            stmt.setString(7, tarjeta.getFechaCreacion());
            stmt.setString(8, tarjeta.getFechaModificacion());
            stmt.setString(9, tarjeta.getEstado());
            stmt.setInt(10, tarjeta.getPersona());
            stmt.setInt(11, tarjeta.getId_cuentabancaria());  // id_cuentabancaria
            stmt.setInt(12, tarjeta.getId_tarjeta());  // ID de la tarjeta para actualizar
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar tarjeta: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Tarjeta SET estado = 0, fechaModificacion = CURRENT_TIMESTAMP WHERE Id_tarjeta = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar tarjeta: " + e.getMessage());
        }
    }
}
