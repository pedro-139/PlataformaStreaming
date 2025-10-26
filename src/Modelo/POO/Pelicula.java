package Modelo.POO;

public class Pelicula {
	
private Genero genero;
private String titulo;
private String resumen;
private String director; 
private float duracion;


public Pelicula() {}
public Pelicula(Genero genero, String titulo, String resumen, String director, int duracion) {
	super();
	this.genero = genero;
	this.titulo = titulo;
	this.resumen = resumen;
	this.director = director;
	this.duracion = duracion;
}

public Genero getGenero() {
		return genero;
	}
public void setGenero(Genero genero) {
		this.genero = genero;
	}
public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}
public String getResumen() {
	return resumen;
}
public void setResumen(String resumen) {
	this.resumen = resumen;
}
public String getDirector() {
	return director;
}
public void setDirector(String director) {
	this.director = director;
}
public float getDuracion() {
	return duracion;
}
public void setDuracion(float duracion) {
	this.duracion = duracion;
}
@Override
public String toString() {
	return "Pelicula [genero=" + genero + ", titulo=" + titulo + ", resumen=" + resumen + ", director=" + director
			+ ", duracion=" + duracion + "]";
}


}
