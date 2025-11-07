package comparador;

import java.util.Comparator;
import Modelo.poo.Pelicula;

public class ComparadorDuracion implements Comparator<Pelicula>{
	public int compare(Pelicula p1, Pelicula p2) {
		 if (p1.getDuracion() < p2.getDuracion()) {
		     return -1;
		 } 
		 else 
			 if (p1.getDuracion() > p2.getDuracion()) {
		         return 1;
		     }
			 else {
		         return 0;
		     }	
    }
}