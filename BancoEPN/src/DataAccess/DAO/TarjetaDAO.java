package DataAccess.DAO;

import DataAccess.IDAO;
import DataAccess.SQLiteDataHelper;
import DataAccess.DTO.TarjetaDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarjetaDAO extends SQLiteDataHelper implements IDAO<TarjetaDTO> {
    private Connection connection;

    // Constructor con conexión abierta
    public TarjetaDAO() throws SQLException {
        this.connection = openConnection();
    }

    // Constructor que recibe una conexión externa
    public TarjetaDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Método para leer una tarjeta por número (tipo Integer).
     */
    @Override
    public TarjetaDTO readBy(Integer numeroTarjeta) throws Exception {
        TarjetaDTO tarjeta = null;
        String query = "SELECT * FROM Tarjeta WHERE id_tarjeta = ? AND estado = 'A'";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, numeroTarjeta);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new TarjetaDTO(
                        rs.getInt("id_tarjeta"),
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
        }
    } catch (SQLException e) {
        throw e;
    }
    return tarjeta;
    }

    /**
     * Método para leer una tarjeta por número (tipo String).
     */
    public TarjetaDTO ReadBy(String numeroTarjeta) throws SQLException {
        String query = "SELECT * FROM Tarjeta WHERE numero_tarjeta = ? AND estado = 'A'" + numeroTarjeta.toString();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(2, numeroTarjeta);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new TarjetaDTO(
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
            }
        } catch (SQLException e) {
            throw e;
        }
        return null;
    }

    /**
     * Método auxiliar para extraer una tarjeta desde ResultSet.
     */
    private TarjetaDTO extractTarjeta(ResultSet rs) throws SQLException {
        return new TarjetaDTO(
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

    /**
     * Método para insertar una nueva tarjeta en la base de datos.
     */
    @Override
    public boolean create(TarjetaDTO tarjeta) throws Exception {
        String query = "INSERT INTO Tarjeta (numero_tarjeta, fecha_expedicion, fecha_vencimiento, cvv, tipo_tarjeta, id_franquicia, fechaCreacion, fechaModificacion, estado, Persona, id_cuentabancaria) VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tarjeta.getNumero_tarjeta());
            stmt.setString(2, tarjeta.getFecha_expedicion());
            stmt.setString(3, tarjeta.getFecha_vencimiento());
            stmt.setString(4, tarjeta.getCvv());
            stmt.setInt(5, tarjeta.getTipo_tarjeta());
            stmt.setInt(6, tarjeta.getId_franquicia());
            stmt.setString(7, tarjeta.getEstado());
            stmt.setInt(8, tarjeta.getPersona());
            stmt.setInt(9, tarjeta.getId_cuentabancaria());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al insertar tarjeta: " + e.getMessage());
        }
    }

    /**
     * Método para actualizar una tarjeta en la base de datos.
     */
    @Override
    public boolean update(TarjetaDTO tarjeta) throws Exception {
        String query = "UPDATE Tarjeta SET numero_tarjeta = ?, fecha_expedicion = ?, fecha_vencimiento = ?, cvv = ?, tipo_tarjeta = ?, id_franquicia = ?, estado = ?, Persona = ?, id_cuentabancaria = ? WHERE Id_tarjeta = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tarjeta.getNumero_tarjeta());
            stmt.setString(2, tarjeta.getFecha_expedicion());
            stmt.setString(3, tarjeta.getFecha_vencimiento());
            stmt.setString(4, tarjeta.getCvv());
            stmt.setInt(5, tarjeta.getTipo_tarjeta());
            stmt.setInt(6, tarjeta.getId_franquicia());
            stmt.setString(7, tarjeta.getEstado());
            stmt.setInt(8, tarjeta.getPersona());
            stmt.setInt(9, tarjeta.getId_cuentabancaria());
            stmt.setInt(10, tarjeta.getId_tarjeta());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar tarjeta: " + e.getMessage());
        }
    }

    /**
     * Método para eliminar (desactivar) una tarjeta.
     */
    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Tarjeta SET estado = '0', fechaModificacion = CURRENT_TIMESTAMP WHERE Id_tarjeta = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar tarjeta: " + e.getMessage());
        }
    }

    /**
     * Método para leer todas las tarjetas activas.
     */
    @Override
    public List<TarjetaDTO> readAll() throws Exception {
        List<TarjetaDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM Tarjeta WHERE estado = 'A'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(extractTarjeta(rs));
            }
        } catch (SQLException e) {
            throw new Exception("Error al listar tarjetas: " + e.getMessage());
        }
        return lista;
    }
}
