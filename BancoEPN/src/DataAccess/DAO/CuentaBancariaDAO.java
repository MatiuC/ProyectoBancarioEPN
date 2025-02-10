package DataAccess.DAO;

import DataAccess.DTO.CuentaBancariaDTO;
import DataAccess.IDAO;
import DataAccess.SQLiteDataHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaBancariaDAO extends SQLiteDataHelper implements IDAO<CuentaBancariaDTO> {
    
    private Connection connection;

    public CuentaBancariaDAO() throws SQLException {
    this.connection = openConnection();
    }
    public CuentaBancariaDAO(Connection connection) {
    this.connection = connection;
    }


    @Override
    public CuentaBancariaDTO readBy(Integer id) throws Exception {
        CuentaBancariaDTO cuentaBancaria = null;
        String query = "SELECT * FROM CuentaBancaria WHERE id_cuentabancaria = ?" + id.toString();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cuentaBancaria = new CuentaBancariaDTO(
                        rs.getInt("id_cuentabancaria"),
                        rs.getString("numeroCuenta"),
                        rs.getInt("id_persona"),
                        rs.getFloat("saldo"),
                        rs.getString("fechaCreacion"),
                        rs.getString("fechaModificacion"),
                        rs.getString("estado")
                    );
                }
            }
        } catch (SQLException e) {
            throw e;
        }    
        return cuentaBancaria;
    }

    public CuentaBancariaDTO readByuser(Integer id) throws Exception {
        CuentaBancariaDTO cuentaBancaria = null;
        String query = "SELECT * FROM CuentaBancaria WHERE id_persona = ?" + id.toString();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(3, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cuentaBancaria = new CuentaBancariaDTO(
                        rs.getInt("id_cuentabancaria"),
                        rs.getString("numeroCuenta"),
                        rs.getInt("id_persona"),
                        rs.getFloat("saldo"),
                        rs.getString("fechaCreacion"),
                        rs.getString("fechaModificacion"),
                        rs.getString("estado")
                    );
                }
            }
        } catch (SQLException e) {
            throw e;
        }    
        return cuentaBancaria;
    }

    @Override
    public List<CuentaBancariaDTO> readAll() throws Exception {
        List<CuentaBancariaDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM CuentaBancaria";
        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new CuentaBancariaDTO(
                    rs.getInt("id_cuentabancaria"),
                    rs.getString("numeroCuenta"),
                    rs.getInt("id_persona"),
                    rs.getFloat("saldo"),
                    rs.getString("fechaCreacion"),
                    rs.getString("fechaModificacion"),
                    rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }
    @Override
    public boolean create(CuentaBancariaDTO entity) throws Exception {
        String query = "INSERT INTO CuentaBancaria (numeroCuenta, id_persona, saldo, fechaCreacion, fechaModificacion, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, entity.getNumero_cuenta());
            stmt.setInt(2, entity.getId_persona());
            stmt.setFloat(3, entity.getSaldo());
            stmt.setString(4, entity.getFecha_creacion());
            stmt.setString(5, entity.getFecha_modificacion());
            stmt.setString(6, entity.getEstado());
             stmt.executeUpdate();


            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId_cuentabancaria(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al crear persona: " + e.getMessage());
        }
        return true;
    }
    @Override
    public boolean update(CuentaBancariaDTO entity) throws Exception {
        String query = "UPDATE CuentaBancaria SET numeroCuenta = ?, id_persona = ?, saldo = ?, fechaCreacion = ?, fechaModificacion = ?, estado = ? WHERE id_cuentabancaria = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, entity.getNumero_cuenta());
            stmt.setInt(2, entity.getId_persona());
            stmt.setFloat(3, entity.getSaldo());
            stmt.setString(4, entity.getFecha_creacion());
            stmt.setString(5, entity.getFecha_modificacion());
            stmt.setString(6, entity.getEstado());
            stmt.setInt(7, entity.getId_cuentabancaria());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw e;
        }
    }
    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE CuentaBancaria SET estado = 'I' WHERE id_cuentabancaria = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw e;
        }
    }
    

    public boolean actualizarSaldo(Integer id, Double monto) throws Exception {
        String query = "UPDATE CuentaBancaria SET saldo = saldo + ? WHERE id_cuentabancaria = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, monto);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw e;
        }
    }
}

