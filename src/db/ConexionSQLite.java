package db;

import java.sql.*;
import thread.ConexionCerradaTask;

public class ConexionSQLite {
    
    private static  Connection con = null;
    static {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:PlataformaStreaming.db");
            System.out.println("Conexión establecida con la base de datos.");
            
            Thread cierreThread = new Thread(new ConexionCerradaTask());
            Runtime.getRuntime().addShutdownHook(cierreThread);
        }
        catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver JDBC de SQLite.");
        }
        catch (SQLException e) {
            System.err.println("Error de SQL: " + e.getMessage());
        }
    }
    
    public static Connection getCon() {
        return con;
    }
    
    public static void cerrarConexion() {
        if (con != null) {
            try {
                con.close();
                con = null; 
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    
}

