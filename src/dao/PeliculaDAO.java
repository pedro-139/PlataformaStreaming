package dao;

import java.util.List;
import modelo.*;


public interface PeliculaDAO {
	
List<Pelicula> listar();
void eliminar(Pelicula p);
Pelicula encontrar (int id);
void guardar (Pelicula p);
int obtenerID(Pelicula p);
}
