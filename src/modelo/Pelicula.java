package modelo;

public class Pelicula {
	
private Genero genero;
private String titulo;
private String resumen;
private int id;
private float rating_Promedio;
private int anio;
private String poster;

public Pelicula() {}
public Pelicula(Genero genero, String titulo, String resumen, float rating_promedio, int anio, String poster,int id) {
	super();
	this.genero = genero;
	this.titulo = titulo;
	this.rating_Promedio = rating_promedio;
	this.resumen = resumen;
	this.id = id;
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

public float getRating_Promedio() {
	return rating_Promedio;
}
public void setRating_Promedio(float ratingPromedio) {
	this.rating_Promedio = ratingPromedio;
}
public int getAnio() {
	return anio;
}
public void setAnio(int anio) {
	this.anio = anio;
}
public String getPoster() {
	return poster;
}
public void setPoster(String poster) {
	this.poster = poster;
}

public int getId() {
	return id;
	}
public void setId(int id) {
	this.id = id;
}

@Override
public String toString() {
	return "Género = " + genero + ", Título = " + titulo + ", Resumen = " + resumen + ", Rating_Promedio = " + rating_Promedio + ", Año = " + anio + ", Poster = " + poster;
}

}
