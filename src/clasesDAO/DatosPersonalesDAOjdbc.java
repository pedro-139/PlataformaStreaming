package clasesDAO;
import Modelo.POO.*;
import DB.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
public class DatosPersonalesDAOjdbc implements DatosPersonalesDAO {

		public DatosPersonales encontrar(Long id) {
			DatosPersonales datosp = null;
			 try{
			 Connection con = ConexionSQLite.getCon();
			 Statement st = con.createStatement();
			 ResultSet rs= st.executeQuery("Select * from Cliente where c.id='"+id+"'");
			 if (rs.next()==true) {
				 datosp = new DatosPersonales();
				 datosp.setNombre(rs.getString(1));
				 datosp.setApellido(rs.getString(2));
				 datosp.setDNI(rs.getInt(3));
			 }
			 rs.close();
			 st.close();

			 } catch (java.sql.SQLException e) {
			 System.out.println("Error de SQL: "+e.getMessage());
			 }
			 return datosp;
			 }
		
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
			 System.out.println("DEBUG: Se encontraron " + Lista.size() + " registros.");
			 rs.close();
			 st.close();

			 } catch (java.sql.SQLException e) {
			 System.out.println("Error de SQL: "+e.getMessage());
			 }
			 return Lista;
		}

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
			
/*
 * package clasesDAO;

import Modelo.POO.DatosPersonales;
import DB.ConexionSQLite;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DatosPersonalesDAOjdbc implements DatosPersonalesDAO {

    // Buscar por ID
    public DatosPersonales encontrar(Long id) {
        DatosPersonales datosp = null;
        try {
            Connection con = ConexionSQLite.getCon();
            String sql = "SELECT * FROM DATOS_PERSONALES WHERE ID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                datosp = new DatosPersonales();
                datosp.setNombre(rs.getString("NOMBRE"));
                datosp.setApellido(rs.getString("APELLIDO"));
                datosp.setDNI(rs.getInt("DNI"));
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
        return datosp;
    }

    // Eliminar por DNI
    public void eliminar(DatosPersonales d) {
        try {
            Connection con = ConexionSQLite.getCon();
            String sql = "DELETE FROM DATOS_PERSONALES WHERE DNI = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, d.getDNI());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
    }

    // Guardar nuevo registro
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
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
    }

    // Listar todos los registros
    public List<DatosPersonales> listar() {
        List<DatosPersonales> lista = new LinkedList<>();
        try {
            Connection con = ConexionSQLite.getCon();
            String sql = "SELECT * FROM DATOS_PERSONALES";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DatosPersonales datosp = new DatosPersonales();
                datosp.setNombre(rs.getString("NOMBRE"));
                datosp.setApellido(rs.getString("APELLIDO"));
                datosp.setDNI(rs.getInt("DNI"));
                lista.add(datosp);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
        return lista;
    }
}

 */
