package clasesDAO;
import Modelo.POO.*;
import java.util.List;

public interface PeliculaDAO {
	
List<Pelicula> listar();
void eliminar(Pelicula p);
Pelicula encontrar (Long id);
void guardar (Pelicula p);
int obtenerID(Pelicula p);
}
