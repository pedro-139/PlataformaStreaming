package dao.implementacionJDBC;
import Modelo.poo.*;
import db.*;
import dao.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
public class DatosPersonalesDAOjdbc implements DatosPersonalesDAO {

	
		//Encuentra el dato personal en la base de datos por su ID.
		public DatosPersonales encontrar(int id) {
			DatosPersonales datosp = null;
			 try{
			 Connection con = ConexionSQLite.getCon();
			 String sql= "SELECT NOMBRE, APELLIDO, DNI FROM DATOS_PERSONALES WHERE ID = ?";
			 PreparedStatement ps = con.prepareStatement(sql);
		        ps.setInt(1, id);
		        ResultSet rs = ps.executeQuery();
			 if (rs.next()==true) {
				 datosp = new DatosPersonales();
				 datosp.setNombre(rs.getString("NOMBRE"));
				 datosp.setApellido(rs.getString("APELLIDO"));
				 datosp.setDNI(rs.getInt("DNI"));
			 }
			 rs.close();
			 ps.close();

			 } catch (java.sql.SQLException e) {
			 System.out.println("Error de SQL: "+e.getMessage());
			 }
			 return datosp;
			 }
		
		//Elimina el dato personal d de la base de datos(por dni)
		public void eliminar(DatosPersonales d) {
			 try {
				 Connection con = ConexionSQLite.getCon();
				 Statement st = con.createStatement();
				 st.executeUpdate("DELETE from DATOS_PERSONALES where DNI='"+d.getDNI()+"'");
			 }
			 catch(java.sql.SQLException e) {
				 System.out.println("Error de SQL: "+e.getMessage());
				 }
		}
		
		
		//Guarda datos personales en la base de datos.
		public void guardar(DatosPersonales d) {
			 try {
			 Connection con = ConexionSQLite.getCon();	
			 String sql = "INSERT INTO DATOS_PERSONALES (NOMBRE, APELLIDO, DNI) VALUES (?, ?, ?)";
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1, d.getNombre());
			 ps.setString(2, d.getApellido());
			 ps.setInt(3, d.getDNI());
			 ps.executeUpdate();
			 ps.close();
		        	   
			 } catch (java.sql.SQLException e) {
				 System.out.println("Error de SQL: "+e.getMessage());
		   }
		}
		 
		
		//Lista los datos personales de la base de datos
		public List<DatosPersonales> listar() {
			List<DatosPersonales> Lista = new LinkedList<DatosPersonales>();
			DatosPersonales datosp = null;
			 try{
			 Connection con = ConexionSQLite.getCon();
			 Statement st = con.createStatement();
			 ResultSet rs= st.executeQuery("Select * from DATOS_PERSONALES");
			
			 while (rs.next()){
				 datosp = new DatosPersonales();
				 datosp.setNombre(rs.getString(2));
				 datosp.setApellido(rs.getString(3));
				 datosp.setDNI(rs.getInt(4));
				 Lista.add(datosp);
			 }
			 rs.close();
			 st.close();

			 } catch (java.sql.SQLException e) {
			 System.out.println("Error de SQL: "+e.getMessage());
			 }
			 return Lista;
		}

		
		// Verifica que exista el DNI en la base de datos.
		public boolean existeDNI(int i) {
			try  {
				String sql = "SELECT 1 FROM DATOS_PERSONALES WHERE DNI = ?";
		        Connection con = ConexionSQLite.getCon();
		        PreparedStatement ps = con.prepareStatement(sql);
		        ps.setLong(1, i);
		        ResultSet rs = ps.executeQuery();
		        boolean existe = rs.next();
		        rs.close();
		        return existe;

			} catch (SQLException e) {
		            System.out.println("Error al verificar DNI: " + e.getMessage());
		            return false;
			}
		    }
		

		
		//Obtiene el ID del dato personal d.
		public int obtenerId(DatosPersonales d) {
			int id= -1;
			try {
				Connection con = ConexionSQLite.getCon();
				String sql = "SELECT ID FROM DATOS_PERSONALES WHERE NOMBRE = ? AND APELLIDO = ? AND DNI = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, d.getNombre());
				ps.setString(2, d.getApellido());
				ps.setInt(3, d.getDNI());
				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					id = rs.getInt("ID");
				}
				else System.out.println("esta mal boludo");
				rs.close();
				ps.close();
     } 
			catch (SQLException e) {
				System.out.println("Error de SQL: " + e.getMessage());
  	  }
			return id;
		}
}
	