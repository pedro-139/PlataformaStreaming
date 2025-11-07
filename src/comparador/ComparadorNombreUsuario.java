package comparador; 
import java.util.Comparator;
import Modelo.poo.Cliente; 
public class ComparadorNombreUsuario implements Comparator<Cliente> {
	
	public int compare(Cliente c1, Cliente c2){ 
		return c1.getNombre_usuario().compareTo(c2.getNombre_usuario()); 
	} 
}