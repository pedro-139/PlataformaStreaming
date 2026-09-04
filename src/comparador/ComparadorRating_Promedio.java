package comparador;

import java.util.Comparator;

import modelo.Pelicula;

public class ComparadorRating_Promedio implements Comparator<Pelicula> {
	@Override
	public int compare(Pelicula p1, Pelicula p2) {
		 if (p1.getRating_Promedio() < p2.getRating_Promedio()) {
		     return 1;
		 } 
		 else 
			 if (p1.getRating_Promedio() > p2.getRating_Promedio()) {
		         return -1; 
		     }
			 else {
		         return 0;
		     }	
}
}
