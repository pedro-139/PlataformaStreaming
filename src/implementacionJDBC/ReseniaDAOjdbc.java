package implementacionJDBC;

import java.sql.*;
import java.time.*;
import java.util.*;

import db.*;
import modelo.*;
import dao.*;

public class ReseniaDAOjdbc implements ReseniaDAO { 

    // Encuentra la reseña en la base de datos por su ID. 
    public Resenia encontrar(int id) { 
        Resenia r = null; 
        try { 
            Connection con = ConexionSQLite.getCon(); 
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from Resenia where id='" + id + "'");
            
            if (rs.next() == true) { 
                r = new Resenia(); 
                r.setCalificacion(rs.getInt("CALIFICACION"));
                r.setComentario(rs.getString("COMENTARIO"));
                r.setAprobado(rs.getBoolean("APROBADO"));
                r.setFecha_hora(rs.getObject("FECHA_HORA", LocalDateTime.class));
                r.setIdC(rs.getInt("ID_CLIENTE")); 
                r.setIdP(rs.getInt("ID_PELICULA"));
                r.setId(id);
            }
            rs.close();
            st.close();
            
        } catch (SQLException e) {
            System.err.println("Error de SQL: " + e.getMessage()); 
        } 
        return r; 
    }  
    

    ///elimina una reseña de la base de datos. 
    public void eliminar(Resenia r) { 
        try { 
            Connection con = ConexionSQLite.getCon(); 
            Statement st = con.createStatement();
            st.executeUpdate("DELETE from Resenia where COMENTARIO='" + r.getComentario() + "'");
            st.close();
        } catch (SQLException e) { 
            System.err.println("Error de SQL: " + e.getMessage()); 
        } 
    } 

    // guarda la reseña r en la base de datos
    public void guardar(Resenia r) { 
        try { 
            Connection con = ConexionSQLite.getCon(); 
            String sql = "INSERT INTO RESENIA (CALIFICACION,COMENTARIO,APROBADO,FECHA_HORA,ID_CLIENTE,ID_PELICULA) VALUES (?,?,?,?,?,?)"; 
            PreparedStatement ps = con.prepareStatement(sql); 
            ps.setInt(1, r.getCalificacion()); 
            ps.setString(2, r.getComentario()); 
            ps.setBoolean(3, r.isAprobado()); 
            ps.setString(4, r.getFecha_hora().toString()); 
            ps.setInt(5, r.getIdC()); 
            ps.setInt(6, r.getIdP()); 
            ps.executeUpdate(); 
            ps.close();
        } catch (SQLException e) { 
            System.err.println("Error de SQL: " + e.getMessage()); 
        } 
    } 

    // lista las reseñas no aprobadas 
    public List<Resenia> listarNoAprobadas() { 
        List<Resenia> Lista = new LinkedList<Resenia>(); 
        Resenia r = null; 
        try { 
            Connection con = ConexionSQLite.getCon(); 
            Statement st = con.createStatement(); 
            ResultSet rs = st.executeQuery("Select * from Resenia WHERE APROBADO = 0"); 
            while (rs.next() == true) { 
                r = new Resenia(); 
                r.setCalificacion(rs.getInt("CALIFICACION")); 
                r.setComentario(rs.getString("COMENTARIO")); 
                r.setAprobado(rs.getBoolean("APROBADO")); 
                r.setFecha_hora(rs.getObject("FECHA_HORA", LocalDateTime.class)); 
                r.setIdC(rs.getInt("ID_CLIENTE")); 
                r.setIdP(rs.getInt("ID_PELICULA")); 
                Lista.add(r); 
            } 
            rs.close(); 
            st.close(); 
        } catch (SQLException e) { 
            System.err.println("Error de SQL: " + e.getMessage()); 
        } 
        return Lista; 
    } 

    // aprueba la reseña r 
    public void aprobarResenia(Resenia r) { 
        try { 
            Connection con = ConexionSQLite.getCon(); 
            String sql = "UPDATE RESENIA SET APROBADO = 1 WHERE CALIFICACION = ? AND COMENTARIO = ? AND ID_CLIENTE = ? AND ID_PELICULA = ?"; 
            PreparedStatement ps = con.prepareStatement(sql); 
            ps.setInt(1, r.getCalificacion()); 
            ps.setString(2, r.getComentario()); 
            ps.setInt(3, r.getIdC()); 
            ps.setInt(4, r.getIdP()); 
            ps.executeUpdate(); 
            ps.close();
        } catch (SQLException e) { 
            System.err.println("Error de SQL: " + e.getMessage()); 
        }
    }
    
    // Verifica si un cliente califico una pelicula especifica
    public boolean clienteCalificoPelicula(int idCliente, int idPelicula) { 
    	boolean existe = false;
        String sql = "SELECT 1 FROM RESENIA WHERE id_cliente = ? AND id_pelicula = ?";
        try {
            Connection con = ConexionSQLite.getCon();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idCliente);
            ps.setInt(2, idPelicula);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) 
				existe = true;
            rs.close();
            ps.close();         
        } catch (SQLException e) {
        	System.err.println("Error de SQL: " + e.getMessage()); 
        }
        return existe;
    } 
    
    // Ve si un cliente ya calificó algun contenido
    public boolean clienteCalificoAlgo(int idCliente) {
    	boolean califico = false;
        String sql = "SELECT 1 FROM RESENIA WHERE id_cliente = ?";
        try {
        	Connection con = ConexionSQLite.getCon();
            PreparedStatement ps = con.prepareStatement(sql); 

            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            	califico = true;
			rs.close();
			ps.close();
        } catch (SQLException e) {
			System.err.println("Error de SQL: " + e.getMessage());
        }
        return califico;
    }

}
