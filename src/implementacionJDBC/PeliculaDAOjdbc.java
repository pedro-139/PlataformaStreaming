package implementacionJDBC;

import db.ConexionSQLite;
import modelo.*;
import dao.PeliculaDAO;
import java.sql.*;
import java.util.*;

public class PeliculaDAOjdbc implements PeliculaDAO {

    // Buscar película por ID
    public Pelicula encontrar(int id) {

        Pelicula p = null;

        try {
            Connection con = ConexionSQLite.getCon();
            String sql = "SELECT * FROM PELICULA WHERE ID = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new Pelicula();
                p.setId(id);
                p.setGenero(Genero.valueOf(rs.getString("GENERO")));
                p.setTitulo(rs.getString("TITULO"));
                p.setResumen(rs.getString("RESUMEN"));
                p.setRating_Promedio(rs.getFloat("RATING_PROMEDIO"));
                p.setAnio(rs.getInt("ANIO"));
                p.setPoster(rs.getString("POSTER"));
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.err.println("Error de SQL (encontrar Pelicula): " + e.getMessage());
        }

        return p;
    }

    // Eliminar película por título
    public void eliminar(Pelicula p) {

        try {
            Connection con = ConexionSQLite.getCon();
            String sql = "DELETE FROM PELICULA WHERE TITULO = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getTitulo());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.err.println("Error de SQL (eliminar Pelicula): " + e.getMessage());
        }
    }

    // Guardar película 
    @Override
    public void guardar(Pelicula p) {

        try {
            Connection con = ConexionSQLite.getCon();

            String sql = "INSERT INTO PELICULA (GENERO, TITULO, RESUMEN, RATING_PROMEDIO, ANIO, POSTER) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, p.getGenero().name());
            ps.setString(2, p.getTitulo());
            ps.setString(3, p.getResumen());
            ps.setFloat(4, p.getRating_Promedio());
            ps.setInt(5, p.getAnio());
            ps.setString(6, p.getPoster());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.err.println("Error de SQL (guardar Pelicula): " + e.getMessage());
        }
    }

    // Listar películas
    public List<Pelicula> listar() {

        List<Pelicula> lista = new LinkedList<>();

        try {
            Connection con = ConexionSQLite.getCon();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM PELICULA");

            while (rs.next()) {

                Pelicula p = new Pelicula();
                p.setId(rs.getInt("ID"));
                p.setGenero(Genero.valueOf(rs.getString("GENERO")));
                p.setTitulo(rs.getString("TITULO"));
                p.setResumen(rs.getString("RESUMEN"));
                p.setRating_Promedio(rs.getFloat("RATING_PROMEDIO"));
                p.setAnio(rs.getInt("ANIO"));
                p.setPoster(rs.getString("POSTER"));

                lista.add(p);
            }

            rs.close();
            st.close();

        } catch (SQLException e) {
            System.err.println("Error de SQL (listar Pelicula): " + e.getMessage());
        }

        return lista;
    }

    // Obtener ID de película por título 
    public int obtenerID(Pelicula p) {

        int id = -1;

        try {
            Connection con = ConexionSQLite.getCon();

            String sql = "SELECT ID FROM PELICULA WHERE TITULO = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, p.getTitulo());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("ID");
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.err.println("Error de SQL (obtenerID Pelicula): " + e.getMessage());
        }

        return id;
    }
    


}