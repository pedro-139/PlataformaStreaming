package DB;
import java.sql.*;

public class ConexionSQLite {
    
    private static  Connection con = null;
    static {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:streaming.db");
            System.out.println("Conexión establecida con la base de datos.");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver JDBC de SQLite.");
        }
        catch (java.sql.SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
    }
    
    public static Connection getCon() {
        return con;
    }

    private ConexionSQLite() {
    }
    

}
