package Modelo.POO;

public class DatosPersonales {
private String nombre;
private String apellido;
private int DNI;

public DatosPersonales() {}


public DatosPersonales(String nombre, String apellido, int DNI) {
	this.nombre = nombre;
	this.apellido = apellido;
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

public String toString() {
	return "Nombre = " + nombre + ", Apellido = " + apellido + ", Dni = " + DNI;
}

}
