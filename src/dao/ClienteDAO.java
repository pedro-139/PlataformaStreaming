package dao;

import java.util.List;

import modelo.*;

public interface ClienteDAO {
	
List<Cliente> listar();
void eliminar(Cliente u);
Cliente encontrar (int id);
void guardar (Cliente u);
boolean validarCliente(String usuario, String contrasenia);
int obtenerID(String email, String contrasenia);
boolean existeEmail(String email);
void actualizar(Cliente c);
}
