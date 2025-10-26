package clasesDAO;

import java.time.LocalDateTime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import DB.ConexionSQLite;
import Modelo.POO.Resenia;

@SuppressWarnings("unused")
public class ReseniaDAOjdbc implements ReseniaDAO {

    public List<Resenia> listarNoAprobadas() {
        List<Resenia> Lista = new LinkedList<Resenia>();
        Resenia resenia = null;
        try {
            Connection con = ConexionSQLite.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from Resenia WHERE APROBADO = 0");
            
            while (rs.next() == true) {
                resenia = new Resenia();
                resenia.setCalificacion(rs.getInt("CALIFICACION"));
                resenia.setComentario(rs.getString("COMENTARIO"));
                resenia.setAprobado(rs.getBoolean("APROBADO"));
                resenia.setFecha_hora(rs.getObject("FECHA_HORA", java.time.LocalDateTime.class));
                resenia.setIdC(rs.getInt("ID_CLIENTE"));
                resenia.setIdP(rs.getInt("ID_PELICULA"));
                Lista.add(resenia);
            }
            rs.close();
            st.close();

        } catch (java.sql.SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
        return Lista;
    }

    public void eliminar(Resenia r) {
        try {
            Connection con = ConexionSQLite.getCon();
            Statement st = con.createStatement();
            st.executeUpdate("DELETE from Resenia where COMENTARIO='" + r.getComentario() + "'");
        } catch (java.sql.SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
    }

    public Resenia encontrar(Long id) {
        Resenia resenia = null;
        try {
            Connection con = ConexionSQLite.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from Resenia where id='" + id + "'");
            if (rs.next() == true) {
                resenia = new Resenia();
                resenia.setCalificacion(rs.getInt(1));
                resenia.setComentario(rs.getString(2));
                resenia.setAprobado(rs.getBoolean(3));
                resenia.setFecha_hora(rs.getObject(4, java.time.LocalDateTime.class));
            }
            rs.close();
            st.close();

        } catch (java.sql.SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
        return resenia;
    }

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
            
        } catch (java.sql.SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
    }

    public void aprobarResenia(Resenia r) {
        try {
            Connection con = ConexionSQLite.getCon();

            String sql = "UPDATE RESENIA SET APROBADO = 1 WHERE CALIFICACION = ? AND COMENTARIO = ? AND ID_CLIENTE = ? AND ID_PELICULA = ?";
            //AND FECHA_HORA = ?
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, r.getCalificacion());
            ps.setString(2, r.getComentario());
        //    ps.setString(3, r.getFecha_hora().toString());
            ps.setInt(3, r.getIdC());
            ps.setInt(4, r.getIdP());
            ps.executeUpdate();

         
        } catch (java.sql.SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
    }
}
