package implementacionJDBC;

import dao.*;
import db.ConexionSQLite;
import modelo.*;
import java.sql.*;
import java.util.*;


public class ClienteDAOjdbc implements ClienteDAO {

	
    // Buscar cliente por ID
    public Cliente encontrar(int id) {
        Cliente cliente = null;
        try {
            Connection con = ConexionSQLite.getCon();
            String sql = "SELECT * FROM CLIENTE WHERE ID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setNombre_usuario(rs.getString("NOMBRE_USUARIO"));
                cliente.setMail(rs.getString("EMAIL"));
                cliente.setContrasenia(rs.getString("CONTRASENIA"));
                cliente.setPrimeraVez(rs.getBoolean("PRIMERAVEZ"));
                cliente.setId(rs.getInt("ID"));
                cliente.setIdDP(rs.getInt("ID_DATOS_PERSONALES"));             
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error de SQL: " + e.getMessage());
        }
        return cliente;
    }

    
    // Eliminar cliente por Email
    public void eliminar(Cliente c) {
        try {
            Connection con = ConexionSQLite.getCon();
            String sql = "DELETE FROM CLIENTE WHERE EMAIL = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getMail());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error de SQL: " + e.getMessage());
        }
    }

    
    // Guardar cliente en la base de datos
    public void guardar(Cliente c) {
        try {
            Connection con = ConexionSQLite.getCon();
            String sql = "INSERT INTO CLIENTE (NOMBRE_USUARIO, EMAIL, CONTRASENIA,PRIMERAVEZ,ID_DATOS_PERSONALES) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getNombre_usuario());
            ps.setString(2, c.getMail());
            ps.setString(3, c.getContrasenia());
            ps.setBoolean(4, c.getPrimeraVez());
            ps.setInt(5, c.getIdDP());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error de SQL: " + e.getMessage());
        }
    }

    
    // Listar todos los clientes
    public List<Cliente> listar() {
        List<Cliente> lista = new LinkedList<>();
        try {
            Connection con = ConexionSQLite.getCon();
            String sql = "SELECT * FROM CLIENTE";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNombre_usuario(rs.getString("NOMBRE_USUARIO"));
                cliente.setMail(rs.getString("EMAIL"));
                cliente.setContrasenia(rs.getString("CONTRASENIA"));
                cliente.setPrimeraVez(rs.getBoolean("PRIMERA_VEZ"));
                cliente.setIdDP(rs.getInt("ID_DATOS_PERSONALES"));
                cliente.setId(rs.getInt("ID"));
                lista.add(cliente);
            }         
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error de SQL: " + e.getMessage());
        }
        return lista;
    }

    
    // Verificar que el Cliente existe en la base de datos a traves de su email y contraseña, si existe devuelve true.
    public boolean validarCliente(String email, String contrasenia) {
    	boolean existe = false;
    	try {
    		Connection con = ConexionSQLite.getCon();
    		String sql = "SELECT 1 FROM CLIENTE WHERE EMAIL = ? AND CONTRASENIA = ?";
    		PreparedStatement ps = con.prepareStatement(sql);
    		ps.setString(1, email);
    		ps.setString(2, contrasenia);
    		ResultSet rs = ps.executeQuery();
    		
    		if (rs.next()) {
    			existe = true;
    		}

    		rs.close();
    		ps.close();
    	} 
    	catch (SQLException e) {
    		System.err.println("Error de SQL: " + e.getMessage());
    	}
    	return existe;
    }


	// Obtener el ID del cliente a partir del email y la contraseña , si no existe o tira -1
    public int obtenerID(String email, String contrasenia) {
    	int id= -1;
    	try {
    		Connection con = ConexionSQLite.getCon();
    		String sql = "SELECT ID FROM CLIENTE WHERE EMAIL = ? AND CONTRASENIA = ?";
    		PreparedStatement ps = con.prepareStatement(sql);
    		ps.setString(1, email);
    		ps.setString(2, contrasenia);
    		ResultSet rs = ps.executeQuery();

    		if (rs.next()) {
	           id = rs.getInt("ID");
    		}

    		rs.close();
    		ps.close();
     } 
    	catch (SQLException e) {
    		System.err.println("Error de SQL: " + e.getMessage());
    	}
    	return id;
    }
    
    
    // Verificar que el Email existe en la base de datos
    public boolean existeEmail(String email) {
    		boolean existe = false;
    		
    		try {
				Connection con = ConexionSQLite.getCon();
				String sql = "SELECT 1 FROM CLIENTE WHERE EMAIL = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, email);
				ResultSet rs = ps.executeQuery();
				
				if (rs.next()) {
					existe = true;
				}

				rs.close();
				ps.close();
			} 
			catch (SQLException e) {
	    		System.err.println("Error de SQL: " + e.getMessage());
			}
    		return existe;
    	}
    
    public void actualizar(Cliente c) {
        try {
            Connection con = ConexionSQLite.getCon();
            String sql = "UPDATE CLIENTE SET PRIMERAVEZ = 0 WHERE (NOMBRE_USUARIO, EMAIL, CONTRASENIA,ID_DATOS_PERSONALES) = (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getNombre_usuario());
            ps.setString(2, c.getMail());
            ps.setString(3, c.getContrasenia());
            ps.setInt(4, c.getIdDP());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error de SQL: " + e.getMessage());
        }
    }
    
    

}