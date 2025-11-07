package comparador;

import java.util.Comparator;
import Modelo.poo.Cliente;

public class ComparadorMail implements Comparator<Cliente> {
    
	public int compare(Cliente c1, Cliente c2){ 
		return c1.getMail().compareTo(c2.getMail());
	}
}

