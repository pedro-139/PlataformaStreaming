package comparador;
import java.util.Comparator;

import Modelo.poo.Pelicula;
public class ComparadorGenero implements Comparator<Pelicula> {
	
	public int compare(Pelicula p1, Pelicula p2){ 		
		return p1.getGenero().compareTo(p2.getGenero()); 
	} 
}


