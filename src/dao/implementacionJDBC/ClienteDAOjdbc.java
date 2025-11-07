package dao.implementacionJDBC;

import Modelo.poo.Cliente;
import dao.*;
import db.ConexionSQLite;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
        return cliente;
    }

    
    // Eliminar cliente por mail
    public void eliminar(Cliente c) {
        try {
            Connection con = ConexionSQLite.getCon();
            String sql = "DELETE FROM CLIENTE WHERE EMAIL = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getMail());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
    }

    
    // Guardar cliente
    public void guardar(Cliente c) {
        try {
            Connection con = ConexionSQLite.getCon();
            String sql = "INSERT INTO CLIENTE (NOMBRE_USUARIO, EMAIL, CONTRASENIA,ID_DATOS_PERSONALES) VALUES (?, ?, ?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getNombre_usuario());
            ps.setString(2, c.getMail());
            ps.setString(3, c.getContrasenia());
            ps.setInt(4, c.getIdDP());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
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
                cliente.setIdDP(rs.getInt("ID_DATOS_PERSONALES"));
                lista.add(cliente);
            }         
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
        return lista;
    }

    
    // Verificar que el Cliente existe en la base de datos
    public boolean validarCliente(String usuario, String contrasenia) {
    	boolean existe = false;
    	try {
    		Connection con = ConexionSQLite.getCon();
    		String sql = "SELECT 1 FROM CLIENTE WHERE NOMBRE_USUARIO = ? AND CONTRASENIA = ?";
    		PreparedStatement ps = con.prepareStatement(sql);
    		ps.setString(1, usuario);
    		ps.setString(2, contrasenia);
    		ResultSet rs = ps.executeQuery();
    		
    		if (rs.next()) {
    			existe = true;
    		}

    		rs.close();
    		ps.close();
    	} 
    	catch (SQLException e) {
    		System.out.println("Error de SQL: " + e.getMessage());
    	}
    	return existe;
    }


	// Obtener el ID del cliente
    public int obtenerID(String usuario, String contrasenia) {
    	int id= -1;
    	try {
    		Connection con = ConexionSQLite.getCon();
    		String sql = "SELECT ID FROM CLIENTE WHERE NOMBRE_USUARIO = ? AND CONTRASENIA = ?";
    		PreparedStatement ps = con.prepareStatement(sql);
    		ps.setString(1, usuario);
    		ps.setString(2, contrasenia);
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