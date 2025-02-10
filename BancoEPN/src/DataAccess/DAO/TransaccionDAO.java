package DataAccess.DAO;

import DataAccess.IDAO;
import DataAccess.SQLiteDataHelper;
import DataAccess.DTO.TransaccionDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaccionDAO extends SQLiteDataHelper implements IDAO<TransaccionDTO> {
    private Connection connection;

    public TransaccionDAO() throws SQLException {
        this.connection = openConnection();
    }

    public TransaccionDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public TransaccionDTO readBy(Integer id) throws Exception {
        TransaccionDTO transaccion = null;
        String query = "SELECT * FROM Transacciones WHERE Id_transaccion = ? AND estado = 'A'";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                transaccion = new TransaccionDTO(
                        rs.getInt("Id_transaccion"),
                        rs.getInt("Origen"),
                        rs.getInt("Destino"),
                        rs.getDouble("Monto"),
                        rs.getString("TipoTransaccion"),
                        rs.getString("Fecha"),
                        rs.getString("Hora"),
                        rs.getString("Descripcion"),
                        rs.getString("FechaCreacion"),
                        rs.getString("FechaModificacion"),
                        rs.getString("Estado").equals("A")
                );
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer transacción: " + e.getMessage());
        }
        return transaccion;
    }

    @Override
    public List<TransaccionDTO> readAll() throws Exception {
        List<TransaccionDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM Transacciones WHERE estado = 'A'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new TransaccionDTO(
                        rs.getInt("Id_transaccion"),
                        rs.getInt("Origen"),
                        rs.getInt("Destino"),
                        rs.getDouble("Monto"),
                        rs.getString("TipoTransaccion"),
                        rs.getString("Fecha"),
                        rs.getString("Hora"),
                        rs.getString("Descripcion"),
                        rs.getString("FechaCreacion"),
                        rs.getString("FechaModificacion"),
                        rs.getString("Estado").equals("A")
                ));
            }
        } catch (SQLException e) {
            throw new Exception("Error al listar transacciones: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean create(TransaccionDTO transaccion) throws Exception {
        String query = "INSERT INTO Transacciones (Origen, Destino, Monto, TipoTransaccion, Fecha, Hora, Descripcion, fechaCreacion, fechaModificacion, estado) VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A')";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, transaccion.getOrigen());
            stmt.setInt(2, transaccion.getDestino());
            stmt.setDouble(3, transaccion.getMonto());
            stmt.setString(4, transaccion.getTipoTransaccion());
            stmt.setString(5, transaccion.getFecha());
            stmt.setString(6, transaccion.getHora());
            stmt.setString(7, transaccion.getDescripcion());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al insertar transacción: " + e.getMessage());
        }
    }

    @Override
    public boolean update(TransaccionDTO transaccion) throws Exception {
        String query = "UPDATE Transacciones SET Origen = ?, Destino = ?, Monto = ?, TipoTransaccion = ?, Fecha = ?, Hora = ?, Descripcion = ?, fechaModificacion = CURRENT_TIMESTAMP WHERE Id_transaccion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, transaccion.getOrigen());
            stmt.setInt(2, transaccion.getDestino());
            stmt.setDouble(3, transaccion.getMonto());
            stmt.setString(4, transaccion.getTipoTransaccion());
            stmt.setString(5, transaccion.getFecha());
            stmt.setString(6, transaccion.getHora());
            stmt.setString(7, transaccion.getDescripcion());
            stmt.setInt(8, transaccion.getIdTransaccion());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar transacción: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Transacciones SET estado = 'I', fechaModificacion = CURRENT_TIMESTAMP WHERE Id_transaccion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar transacción: " + e.getMessage());
        }
    }

    public List<TransaccionDTO> obtenerUltimasTransacciones(int idCliente) throws Exception {
        List<TransaccionDTO> transacciones = new ArrayList<>();
        String query = "SELECT t.Id_transaccion, " +
                      "t.Origen, t.Destino, " +
                      "po.nombre || ' ' || po.apellido as nombre_origen, " +
                      "pd.nombre || ' ' || pd.apellido as nombre_destino, " +
                      "t.Monto, " +
                      "tt.nombre_tipo_transaccion, " +
                      "t.Fecha || ' ' || t.Hora as fecha_transaccion " +
                      "FROM Transacciones t " +
                      "INNER JOIN CuentaBancaria cbo ON t.Origen = cbo.id_cuentabancaria " +
                      "INNER JOIN CuentaBancaria cbd ON t.Destino = cbd.id_cuentabancaria " +
                      "INNER JOIN Persona po ON cbo.id_persona = po.Id_persona " +
                      "INNER JOIN Persona pd ON cbd.id_persona = pd.Id_persona " +
                      "INNER JOIN TipoTransaccion tt ON t.TipoTransaccion = tt.id_tipo_transaccion " +
                      "WHERE (cbo.id_persona = ? OR cbd.id_persona = ?) " +
                      "AND t.estado = 'A' " +
                      "ORDER BY t.fecha DESC, t.hora DESC LIMIT 5";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idCliente);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                TransaccionDTO transaccion = new TransaccionDTO(
                    rs.getInt("Id_transaccion"),
                    rs.getInt("Origen"),
                    rs.getInt("Destino"),
                    rs.getDouble("Monto"),
                    rs.getString("nombre_tipo_transaccion"),
                    rs.getString("fecha_transaccion")
                );
                // Guardar los nombres en variables temporales
                transaccion.setNombreOrigen(rs.getString("nombre_origen"));
                transaccion.setNombreDestino(rs.getString("nombre_destino"));
                transacciones.add(transaccion);
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener las últimas transacciones: " + e.getMessage());
        }
        return transacciones;
    }
}
