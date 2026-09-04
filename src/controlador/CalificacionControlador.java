package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import excepcion.DatosIncompletosException;
import modelo.*;
import servicio.*;
import vista.*;


public class CalificacionControlador {

    private CalificacionVista vista;
    private Cliente cliente;
    private Pelicula pelicula;
    private TableModel model;
    private int filaSeleccionada;
    
    public CalificacionControlador(CalificacionVista vista, Cliente c, Pelicula p, TableModel model, int filaSeleccionada) {
        this.vista = vista;
        this.cliente = c;
        this.pelicula = p;
        this.model = model;
        this.filaSeleccionada = filaSeleccionada;
        vista.setTituloPelicula(p.getTitulo());	
        vista.getBtnGuardar().addActionListener(new GuardarReseniaListener());
    }



	private class GuardarReseniaListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        	Resenia r = new Resenia();
            try {
                String txtRating = vista.getTxtCalificacion().getText().trim();
                String comentario = vista.getTxtComentario().getText().trim();

                
                int rating;
                    rating = Integer.parseInt(txtRating); // Si no se parsea salta NumberFormatException

                    LocalDateTime fecha = LocalDateTime.now();
                    r.setComentario(comentario);
                    r.setCalificacion(rating);
                    r.setAprobado(false);
                    r.setFecha_hora(fecha);
                    r.setIdC(cliente.getId());
                    r.setIdP(pelicula.getId());
                    r.setId(0); //se setea en 0 ya que todavia no se creo la reseña
                    ServiciosResenia.registrarResenia(r);
                    ServiciosResenia.guardarResenia(r);
                    model.setValueAt("Calificado", filaSeleccionada, 4);
                    vista.mostrarDialogoExito("¡Reseña guardada exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Cerrar
                    vista.dispose();
                } catch (DatosIncompletosException e1) {
                	vista.mostrarMensajeError(e1.getMessage());
                }catch (NumberFormatException ex) {
                    vista.mostrarMensajeError("La calificación debe ser un número entero.");
                }
             catch (Exception ex) {
                vista.mostrarMensajeError("Error al guardar la reseña: " + ex.getMessage());
            }
        }
    }
}
