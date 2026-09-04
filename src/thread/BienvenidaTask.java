package thread;

import java.util.List;

import controlador.BienvenidaControlador;
import modelo.*;
import servicio.*;
import vista.BienvenidaVista;

public class BienvenidaTask implements Runnable {
	
	private ServiciosPelicula serviciosPe;
	private BienvenidaVista vista;
	private Cliente c;
	private BienvenidaControlador controlador;
	
	public BienvenidaTask(BienvenidaVista vista,ServiciosPelicula servPe,Cliente c, BienvenidaControlador controlador) {
		this.vista = vista;
		this.serviciosPe = servPe;
		this.c = c;
		this.controlador = controlador;
	}
	@Override
	public void run() {
	    try {
	    	List<Pelicula> peliculasTotal = serviciosPe.obtenerPeliculas(); // Obtener todas las películas
	    	
	    	// Obtener las 10 películas para la bienvenida, si no estan en la base de datos se cargan del CSV.
			List<Pelicula> peliculasTop10 = serviciosPe.obtenerListadoBienvenida(c, peliculasTotal); 

	            try {
	                vista.ocultarCarga();
	                controlador.setListaActual(peliculasTop10);
	            } catch (Exception e) {
	                vista.mostrarMensajeError("Error al presentar el contenido: " + e.getMessage());
	            }

	    } catch(Exception e) {
	            vista.ocultarCarga();
	            vista.mostrarMensajeError("Error en carga concurrente: " + e.getMessage());   
	    }
	}
}