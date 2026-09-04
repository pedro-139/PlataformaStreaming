package modelo;


public class Cliente {
private String nombre_usuario;
private String mail;
private String contrasenia;
private boolean primeraVez;
private int idDP;
private int id;
public Cliente() {}

public Cliente(String nombre_usuario, String mail, String contrasenia, int idDP, int id) {
	super();
	this.nombre_usuario = nombre_usuario;
	this.mail = mail;
	this.contrasenia = contrasenia;
	this.idDP = idDP; 
	this.id = id;
}

public String getNombre_usuario() {
	return nombre_usuario;
}

public void setNombre_usuario(String nombre_usuario) {
	this.nombre_usuario = nombre_usuario;
}

public String getMail() {
	return mail;
}

public void setMail(String mail) {
	this.mail = mail;
}

public String getContrasenia() {
	return contrasenia;
}

public void setContrasenia(String contrasenia) {
	this.contrasenia = contrasenia;
}
public boolean getPrimeraVez() {
	return primeraVez;
}
public void setPrimeraVez(boolean primeraVez) {
	this.primeraVez = primeraVez;
}

public int getIdDP() {
	return idDP;
}

public void setIdDP(int idDP) {
	this.idDP = idDP;
}
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String toString() {
	return "Nombre de usuario = " + nombre_usuario + ", Email = " + mail + ", Contraseña = " + contrasenia +",Primera vez = " +primeraVez +", ID Datos personales = " +idDP;
}







}


