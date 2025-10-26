package Comparadores; 
import java.util.Comparator;
import Modelo.POO.Cliente; 
public class ComparadorNombreUsuario implements Comparator<Cliente> {
	
	public int compare(Cliente c1, Cliente c2){ 
		return c1.getNombre_usuario().compareTo(c2.getNombre_usuario()); 
	} 
}