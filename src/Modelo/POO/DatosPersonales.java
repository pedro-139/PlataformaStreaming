package Modelo.POO;

public class DatosPersonales {
private String nombre;
private String apellido;
private int DNI;
private String pais;
private String idioma;

public DatosPersonales() {}


public DatosPersonales(String nombre, String apellido, int DNI, String pais, String idioma) {
	this.nombre = nombre;
	this.apellido = apellido;
	this.pais = pais;
	this.idioma = idioma;
	this.DNI = DNI;
}

public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getApellido() {
	return apellido;
}
public void setApellido(String apellido) {
	this.apellido = apellido;
}

public int getDNI() {
	return DNI;
}

public void setDNI(int dNI) {
	DNI = dNI;
}
public String getPais() {
	return pais;
}
public void setPais(String pais) {
	this.pais = pais;
}
public String getIdioma() {
	return idioma;
}
public void setIdioma(String idioma) {
	this.idioma = idioma;
}



public String toString() {
	return "Nombre = " + nombre + ", Apellido = " + apellido + ", DNI = " + DNI;
}

}
