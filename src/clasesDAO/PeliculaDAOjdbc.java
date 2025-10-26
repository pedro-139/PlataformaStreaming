		package clasesDAO;
		import Modelo.POO.Pelicula;
		import Modelo.POO.Genero;
		import DB.*;
		import java.sql.*;
		import java.util.LinkedList;
		import java.util.List;
		public class PeliculaDAOjdbc implements PeliculaDAO{
			
					public Pelicula encontrar(Long id) {
						Pelicula pelicula = null;
						 try{
						 Connection con = ConexionSQLite.getCon();
						 Statement st = con.createStatement();
						 ResultSet rs= st.executeQuery("Select * from Pelicula where id='"+id+"'");
						 if (rs.next()==true) {
							 pelicula = new Pelicula();
							 pelicula.setGenero(Genero.valueOf(rs.getString("GENERO")));
							 pelicula.setTitulo(rs.getString("TITULO"));
							 pelicula.setResumen(rs.getString("RESUMEN"));
							 pelicula.setDirector(rs.getString("DIRECTOR"));
							 pelicula.setDuracion(rs.getFloat("DURACION"));
						 }
						 rs.close();
						 st.close();
		
						 } catch (java.sql.SQLException e) {
						 System.out.println("Error de SQL: "+e.getMessage());
						 }
						 return pelicula;
						 }
					
					public void eliminar(Pelicula p) {
						 try {
							 Connection con = ConexionSQLite.getCon();
							 
							 String sql = "DELETE FROM PELICULA WHERE TITULO = ?";
							 PreparedStatement ps = con.prepareStatement(sql);
							 ps.setString(1, p.getTitulo());
							 ps.executeUpdate();
							 ps.close();
						 }
						 catch(java.sql.SQLException e) {
							 System.out.println("Error de SQL: "+e.getMessage());
							 }
					}
					public void guardar(Pelicula p) {
						 
						 try {
						 Connection con = ConexionSQLite.getCon();
			
						 String sql = "INSERT INTO PELICULA (GENERO, TITULO, RESUMEN, DIRECTOR, DURACION) VALUES (?, ?, ?, ?, ?)";
						 PreparedStatement ps = con.prepareStatement(sql);
				         ps.setString(1, p.getGenero().name());				         
						 ps.setString(2, p.getTitulo());
						 ps.setString(3, p.getResumen());
						 ps.setString(4, p.getDirector());
						 ps.setFloat(5, p.getDuracion());
		
						 ps.executeUpdate();
						 ps.close();
		
			
					       
						 } catch (java.sql.SQLException e) {
							 System.out.println("Error de SQL: "+e.getMessage());
					   }
					}
					 
					
					public List<Pelicula> listar() {
						List<Pelicula> Lista = new LinkedList<Pelicula>();
						Pelicula pelicula = null;
						 try{
						 Connection con = ConexionSQLite.getCon();
						 Statement st = con.createStatement();
						 ResultSet rs= st.executeQuery("Select * from Pelicula");
						
						 while (rs.next()==true) {
							 pelicula = new Pelicula();
							 pelicula.setTitulo(rs.getString("TITULO"));
							 pelicula.setResumen(rs.getString("RESUMEN"));
							 pelicula.setDirector(rs.getString("DIRECTOR"));
							 pelicula.setDuracion(rs.getFloat("DURACION"));
							 pelicula.setGenero(Genero.valueOf(rs.getString("GENERO")));
							 Lista.add(pelicula);
						 }
						 rs.close();
						 st.close();
		
						 } catch (java.sql.SQLException e) {
						 System.out.println("Error de SQL: "+e.getMessage());
						 }
						 return Lista;
					}
		
		
		public int obtenerID(Pelicula p) {
			int id= -1;
			 try {
		         Connection con = ConexionSQLite.getCon();
		         String sql = "SELECT ID FROM PELICULA WHERE GENERO = ? AND TITULO = ? AND RESUMEN = ? AND DIRECTOR = ? AND DURACION = ?";
		         PreparedStatement ps = con.prepareStatement(sql);
		         ps.setString(1, p.getGenero().name());
		         ps.setString(2, p.getTitulo());
		         ps.setString(3, p.getResumen());
		         ps.setString(4, p.getDirector());
		         ps.setFloat(5, p.getDuracion());
		         ResultSet rs = ps.executeQuery();
		
		         if (rs.next()) {
		            id = rs.getInt("ID");
		         }
		
		         rs.close();
		         ps.close();
		
		     } 
			 catch (SQLException e) {
		         System.out.println("Error de SQL: " + e.getMessage());
		     }
			return id;
			}
		
		    
		}				
