package comparador;

import java.util.Comparator;

import Modelo.poo.Pelicula;

public class ComparadorTitulo implements Comparator<Pelicula> {
	
	public int compare(Pelicula p1, Pelicula p2){ 
		return p1.getTitulo().compareTo(p2.getTitulo()); 
	} 
}
