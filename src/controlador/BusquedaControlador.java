package controlador;

import excepcion.DatosNoEncontradosException;
import modelo.*;
import servicio.ConsultaPeliculasOMDb;
import vista.BusquedaVista;

import java.awt.event.*;


public class BusquedaControlador {

    private BusquedaVista vista;
    
    public BusquedaControlador(BusquedaVista vista, String tituloBuscado) {

        this.vista = vista;
        vista.getBtnCerrar().addActionListener( new CerrarItemListener());
        cargarInformacion(tituloBuscado);
    }

    private void cargarInformacion(String titulo) {
        try {
            Pelicula p = ConsultaPeliculasOMDb.consultarPelicula(titulo);
            if (p == null) 
            return; 
            vista.setTitulo(p.getTitulo());
            vista.setAnio(String.valueOf(p.getAnio()));
            vista.setResumen(p.getResumen());
            vista.setVisible(true);
            
        }
        catch (DatosNoEncontradosException e) {
            	vista.noDisponible(e.getMessage());
            	vista.dispose();
        }
        catch (Exception e) {
        	vista.mostrarError("El servicio OMDb no responde.");
        	vista.dispose();
        }
    }

    private class CerrarItemListener implements ActionListener {
        public void actionPerformed(ActionEvent evento) {
            vista.dispose();
        }
    }
}
