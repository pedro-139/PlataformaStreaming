package Modelo.POO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Resenia {
private int calificacion;
private String comentario;
private boolean aprobado;
private LocalDateTime fecha_hora;
private int idC;
private int idP;

public Resenia() {}
public Resenia(int calificacion, String comentario, boolean aprobado, LocalDateTime fecha_hora, int idC, int idP) {
	super();
	this.calificacion = calificacion;
	this.comentario = comentario;
	this.aprobado = aprobado;
	this.fecha_hora = fecha_hora;
	this.idC = idC;
	this.idP = idP;
}
public int getCalificacion() {
	return calificacion;
}
public void setCalificacion(int calificacion) {
	this.calificacion = calificacion;
}
public String getComentario() {
	return comentario;
}
public void setComentario(String comentario) {
	this.comentario = comentario;
}
public boolean isAprobado() {
	return aprobado;
}
public void setAprobado(boolean aprobado) {
	this.aprobado = aprobado;
}
public LocalDateTime getFecha_hora() {
	return fecha_hora;
}
public void setFecha_hora(LocalDateTime fecha_hora) {
	this.fecha_hora = fecha_hora;
}
public int getIdC() {
	return idC;
}
public void setIdC(int idC) {
	this.idC = idC;
}
public int getIdP() {
	return idP;
}
public void setIdP(int idP) {
	this.idP = idP;
}
@Override
public String toString() {
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    String fechaFormateada = fecha_hora.format(formatter);
	return "Calificación = " + calificacion + ", Comentario = " + comentario + ", Aprobado = " + aprobado
			+ ", Fecha = " + fechaFormateada;
}

}
