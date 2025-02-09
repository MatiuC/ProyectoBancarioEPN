package DataAccess.DAO;
import DataAccess.DTO.VistaTransaccionesDTO;
import DataAccess.IDAO;
import DataAccess.SQLiteDataHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class VistaTransaccionesDAO extends SQLiteDataHelper implements IDAO<VistaTransaccionesDTO> {
   
    private Connection connection;

    public VistaTransaccionesDAO() throws SQLException {
        this.connection = openConnection();
    }

    public VistaTransaccionesDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<VistaTransaccionesDTO> readAll() throws SQLException {
        List<VistaTransaccionesDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM VistaTransacciones";
        try (Statement stmt = openConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new VistaTransaccionesDTO(
                        rs.getInt("transacion_id"),
                        rs.getInt("origen_tarjeta_id"),
                        rs.getInt("destino_tarjeta_id"),
                        rs.getDouble("monto"),
                        rs.getString("tipo_transaccion"),
                        rs.getString("fecha_transaccion")
                ));
            }
        }catch (SQLException e) {
            throw e;//new Exception( e.getMessage(), getClass().getName(), "delete()");
    }
    return lista;
    }

    @Override
    public VistaTransaccionesDTO readBy(Integer id) throws SQLException {
        VistaTransaccionesDTO vistaTransacciones = null;
        String query = "SELECT * FROM VistaTransacciones WHERE transacion_id = ?";
        try (PreparedStatement stmt = openConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new VistaTransaccionesDTO(
                            rs.getInt("transacion_id"),
                            rs.getInt("origen_tarjeta_id"),
                            rs.getInt("destino_tarjeta_id"),
                            rs.getDouble("monto"),
                            rs.getString("tipo_transaccion"),
                        rs.getString("fecha_transaccion")
                );
            }
        }
    } catch (SQLException e) {
        throw e;
    }
    return null;
    


        }

    @Override
    public boolean create(VistaTransaccionesDTO entity) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public boolean update(VistaTransaccionesDTO entity) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
        
}
