package Main;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import Modelo.POO.Pelicula;
import Modelo.POO.Resenia;
import clasesDAO.ClienteDAO;
import clasesDAO.FactoryDAO;
import clasesDAO.PeliculaDAO;
import clasesDAO.ReseniaDAO;

public class ValidacionesResenia {
	public static Resenia registrarResenia(Scanner s) {
		 Resenia r = new Resenia();
		 ClienteDAO daoCliente = FactoryDAO.getClienteDAO();
		 PeliculaDAO daoPelicula = FactoryDAO.getPeliculaDAO();
		 int idC = -1;
		 int idP = -1;
		 List<Pelicula> lista = new LinkedList<Pelicula>();
		 boolean valido =false;
		 int opcion;
		 String res;
		 
		 //Validar Cliente
		 while (!valido) {
	            System.out.print("Ingresar nombre de usuario: ");
	            String nombre = s.next();
	            System.out.print("Ingresar contrasenia: ");
	            String contrasenia = s.next();
	            if ( daoCliente.validarCliente(nombre, contrasenia)){
	            	idC = daoCliente.obtenerID(nombre, contrasenia);
	            	valido = true;
	            	
	            }
	            else {
	                System.out.println("Nombre de usuario o contraseña incorrectos.");
	            }
	        }
		 
		 //Validar Pelicula
		 valido = false;		 
		 lista = ValidacionesPelicula.listarPeliculas(s);
		 System.out.print("Seleccione una pelicula de la lista: ");
		 for(int i = 0; i<lista.size(); i++) {
          	  System.out.println("Pelicula " + (i + 1) + ": " + lista.get(i).toString());
            }	 
		 while(!valido) {		
		 opcion = s.nextInt();
		 if (opcion - 1 < lista.size()) {
			 idP = daoPelicula.obtenerID(lista.get(opcion-1));
			 valido = true;
		 }
		 else {
			 System.out.println("Se introdujo una posicion invalida.");
		 	}
		 }
		 
		 valido = false;
		 //ingresar reseña
		 //calificacion
	        while (!valido) {
	            System.out.print("Ingrese la calificacion de la Pelicula (1-100): ");
	            r.setCalificacion(s.nextInt());
	            if( r.getCalificacion() > 0 &&  r.getCalificacion() <= 100) {
	                System.out.println("Se ingresó: '" + r.getCalificacion() + "'. ¿Es correcto? (y/n)");
	                res = s.next();
	                s.nextLine();
	                if(res.equalsIgnoreCase("y")) valido = true;
	            } else {
	                System.out.println("Se introdujo una calificacion inválida.");
	            }
	        }
	       //comentario
	       valido = false;
	       while(!valido) {
	    	   System.out.print("Ingrese el comentario de la Pelicula (max 500): ");
	            r.setComentario(s.nextLine());
	            if( r.getComentario() != null && r.getComentario().length() <=500) {
	                System.out.println("Se ingresó: '" + r.getComentario() + "'. ¿Es correcto? (y/n)");
	                res = s.next();
	                s.nextLine();
	                if(res.equalsIgnoreCase("y")) valido = true;
	            } else {
	                System.out.println("Se introdujo un comentario inválido.");
	            }
	       }
	       //aprobado
	       r.setAprobado(false);
	       //fecha_hora
	       r.setFecha_hora(LocalDateTime.now());
	       //id cliente
	       r.setIdC(idC);
	       //id pelicula
	       r.setIdP(idP);
		 return r;
}
	
	public static void aprobarResenia(Scanner s) {
	    Resenia r = new Resenia();
	    ReseniaDAO rDAO = FactoryDAO.getReseniaDAO();
	    List<Resenia> lista = rDAO.listarNoAprobadas();
	    int opcion = -1 ; 
	    boolean valido = false;
	    String res;
	    for (int i = 0 ; i<lista.size() ; i++) {
		    System.out.println("Reseña " + (i + 1) + ": " + lista.get(i).toString());
	    }
	    while (!valido) {
		    System.out.println("Seleccionar una reseña: ");
		    opcion = s.nextInt();
		    if (opcion - 1 < lista.size()) {
			    r= lista.get(opcion-1);
			    System.out.println("Se ingresó la reseña: '" + r.toString()+ "'. ¿Es correcto? (y/n)");
                res = s.next();
                s.nextLine();
                if (res.equalsIgnoreCase("y")) 
                    valido = true;
              
		    }
		    else {
			    System.out.println("La reseña seleccionada no existe.");
		    }
	    }
	rDAO.aprobarResenia(r);
	}
}
