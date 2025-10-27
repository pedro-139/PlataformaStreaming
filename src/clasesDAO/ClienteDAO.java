package clasesDAO;

import Modelo.POO.*;
import java.util.List;

public interface ClienteDAO {
	
List<Cliente> listar();
void eliminar(Cliente u);
Cliente encontrar (Long id);
void guardar (Cliente u);
boolean validarCliente(String usuario, String contrasenia);
int obtenerID(String usuario, String contrasenia);
}
