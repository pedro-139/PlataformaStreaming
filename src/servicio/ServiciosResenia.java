package servicio;


import dao.*;
import excepcion.*;
import factoryDAO.FactoryDAO;
import modelo.*;

public class ServiciosResenia {

	
	public static void registrarResenia(Resenia r) throws DatosIncompletosException, NumberFormatException {
		 if (r == null)
		        throw new DatosIncompletosException("Faltan datos de la reseña.");

		    if (r.getComentario() == null || r.getComentario().isBlank())
		        throw new DatosIncompletosException("El comentario no puede estar vacío.");
		
		    // rating tiene que ser numérico y estar en rango 1-10
		    if (r.getCalificacion() < 1 || r.getCalificacion() > 10) {
		    	throw new NumberFormatException("La calificación debe estar entre 1 y 10");
		    }
		    if (r.getIdC() == -1 || r.getIdP() == -1)
		        throw new DatosIncompletosException("Debe existir cliente y película asociados.");
		}

	
    // Verifica si un cliente ya calificó una película , si la calificó devuelve true, sino false.
	public static boolean clienteYaCalifico(int idCliente, int idPelicula) throws DatosYaExistenException {
		boolean califico = false;
	    ReseniaDAO rDAO = FactoryDAO.getReseniaDAO();
	    califico = rDAO.clienteCalificoPelicula(idCliente, idPelicula);
	    if (califico) 
	    	throw new DatosYaExistenException("Ya calificaste esta película.");
	    return califico; // solo llega  a esta linea si no lanzó la excepción (califico == false).
	}
	

	// Guarda una reseña en la base de datos
    public static void guardarResenia(Resenia r) {
        ReseniaDAO rDAO = FactoryDAO.getReseniaDAO();
        rDAO.guardar(r);
    }
}
