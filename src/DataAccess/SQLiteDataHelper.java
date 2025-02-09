package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class SQLiteDataHelper {
    private static String DBConnection = "jdbc:sqlite:C:\\ProyectoBancarioEPN\\BancoEPN\\database\\db_BancoEPN.sqlite\\";


    private static Connection conn = null;
    
    public SQLiteDataHelper() {}

    public static synchronized Connection openConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            try {
                conn = DriverManager.getConnection(DBConnection);
                System.out.println("Conexión a la base de datos establecida.");
            } catch (SQLException e) {
                System.err.println("Error al conectar con la base de datos: " + e.getMessage());
                throw e;
            }
        }
        return conn;
    }

    public static void closeConnection() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            try {
                conn.close();
                conn = null; // Reinicializar la conexión
                System.out.println("Conexión a la base de datos cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
                throw e;
            }
        }
    }
}
