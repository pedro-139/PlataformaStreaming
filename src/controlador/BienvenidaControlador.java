package controlador;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import excepcion.*;
import modelo.*;
import servicio.*;
import thread.*;
import vista.*;

public class BienvenidaControlador {

    private BienvenidaVista vista;
    private ServiciosPelicula servPe;
    private Cliente c;
    private List<Pelicula> listaVisible; 
    

    public BienvenidaControlador(BienvenidaVista vista, ServiciosPelicula servPe, DatosPersonales dp, Cliente c) {
        this.vista = vista;
        this.servPe = servPe;
        this.c = c;

        
        listaVisible = new ArrayList<>();
 
        // LISTENERS
        vista.getBtnCerrarSesion().addActionListener(new CerrarSesionItemListener());
        vista.getBtnBuscar().addActionListener(new BuscarItemListener());
        vista.setNombreCliente(dp.getNombre(), dp.getApellido());
        vista.getTabla().addMouseListener(new TablaMouseListener());
        
    }

    // Inicia la carga de contenido en un hilo
    public void iniciarCargaDeContenido(Cliente c) {

        // Mostrar animación de carga
        vista.mostrarCarga();

        // Iniciar el hilo que carga datos 
        BienvenidaTask task = new BienvenidaTask(vista, servPe, c,this);
        Thread t = new Thread(task,"BienvenidaTask");
        t.start();
    }


    // LISTENERS
    private class CerrarSesionItemListener implements ActionListener {
        public void actionPerformed(ActionEvent evento) {
            vista.dispose();
            LoginVista loginVista = new LoginVista();
            ServiciosCliente loginModelo = new ServiciosCliente();
            new LoginControlador(loginVista, loginModelo);
        }
    }

    private class BuscarItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evento) {
            BusquedaVista iVista = new BusquedaVista();
            new BusquedaControlador(iVista, vista.getBuscar().getText().trim());
        }
    }

    private class TablaMouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
        	try {
        	// Conseguir posicion de la fila y tabla clickeados.	
            JTable tabla = vista.getTabla();
            int fila = tabla.rowAtPoint(e.getPoint());
            int columna = tabla.columnAtPoint(e.getPoint());

            // Solo interactua con la columna del botón
            if (columna != 4) return;
            
            // Obtener la película desde la lista visible
            Pelicula p = listaVisible.get(fila);

            // Verificar si ya fue calificada , si no fue calificada lanza una excepción.
            ServiciosResenia.clienteYaCalifico(c.getId(), p.getId());

            // Abrir ventana de calificación
            CalificacionVista calVista = new CalificacionVista();
            calVista.setTituloPelicula(p.getTitulo());
            new CalificacionControlador(calVista, c, p, vista.getModeloTabla(), fila);
            calVista.setVisible(true);
        	} catch( DatosYaExistenException ex) {
				vista.mostrarMensajeInfo(ex.getMessage());	
        	} catch (Exception ex) {
        		vista.mostrarMensajeError("Error al procesar la calificación: " + ex.getMessage());
            }
        }
    }

    public void setListaActual(List<Pelicula> peliculas) {
        this.listaVisible = peliculas;
        vista.mostrarContenidoPrincipal(peliculas, c);
    }
}
