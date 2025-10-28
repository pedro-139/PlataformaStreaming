package DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class CrearTabla {


	public static void  creacionDeTablasEnBD(Connection connection) throws SQLException {
	
		Statement stmt = connection.createStatement();
		try {
			//  TABLA DE DATOS PERSONALES 
			String sql = " CREATE TABLE IF NOT EXISTS DATOS_PERSONALES ("+
						 "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
						 "NOMBRE TEXT(100) NOT NULL,"+
						 "APELLIDO TEXT(100) NOT NULL,"+
						 "DNI INTEGER NOT NULL"+
						 ");";
			stmt.executeUpdate(sql);
			
			//  TABLA DE PELICULAS 
			sql= " CREATE TABLE IF NOT EXISTS PELICULA ("+
				 "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
			     "GENERO TEXT(10) NOT NULL,"+ //En una clase de consulta nos recomendaron cambiar genero para que sean 10 carac y guardar el string del genero con valueOf().
			     "TITULO TEXT(100) NOT NULL,"+
			     "RESUMEN TEXT(500),"+
			     "DIRECTOR TEXT(100) NOT NULL,"+
			     "DURACION REAL NOT NULL"+
			     ");";
			stmt.executeUpdate(sql);
			
			//  TABLA DE CLIENTES 
			sql = " CREATE TABLE IF NOT EXISTS CLIENTE ("+
					"ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
					"NOMBRE_USUARIO TEXT NOT NULL,"+
					"EMAIL TEXT NOT NULL,"+
					"CONTRASENIA TEXT NOT NULL," +
					"ID_DATOS_PERSONALES INTEGER NOT NULL,"+
					"CONSTRAINT CLIENTE_DATOS_PERSONALES_FK FOREIGN KEY (ID_DATOS_PERSONALES) REFERENCES DATOS_PERSONALES(ID)"+
					");";
			stmt.executeUpdate(sql);
	        // TABLA DE RESEÑAS 
			sql = " CREATE TABLE IF NOT EXISTS RESENIA ("+
				  "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
				  "CALIFICACION INTEGER NOT NULL,"+
				  "COMENTARIO TEXT(500),"+
				  "APROBADO INTEGER DEFAULT (1) NOT NULL,"+
				  "FECHA_HORA DATETIME NOT NULL,"+
				  "ID_CLIENTE INTEGER NOT NULL,"+
				  "ID_PELICULA INTEGER NOT NULL,"+
				  "CONSTRAINT RESENIA_USUARIO_FK FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE(ID),"+
				  "CONSTRAINT RESENIA_PELICULA_FK FOREIGN KEY (ID_PELICULA) REFERENCES PELICULA(ID)"+
				  ");";
	        stmt.executeUpdate(sql);
	        stmt.close();
	        System.out.println("Tablas creadas correctamente.");
	    } catch (java.sql.SQLException e) {
	        System.out.println("Error de SQL: " + e.getMessage());
	    }
    }
}
