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
	            System.out.print("Ingresar nombre de usuario (obligatorio): ");
	            String nombre = s.nextLine();
	            System.out.print("Ingresar contraseña (obligatorio): ");
	            String contrasenia = s.nextLine();
	            if ( daoCliente.validarCliente(nombre, contrasenia)){
	            	idC = daoCliente.obtenerID(nombre, contrasenia);
	            	valido = true;
	            	
	            }
	            else {
	                System.out.println("Nombre de usuario o contraseña incorrectos.");
	            }
	        }
		 
		 // Validar Pelicula
		 valido = false;		 
		 lista = ValidacionesPelicula.listarPeliculas(s);
		 System.out.println("Seleccione una pelicula de la lista (obligatorio): ");
		 for(int i = 0; i<lista.size(); i++) {
          	  System.out.println("Pelicula " + (i + 1) + ": " + lista.get(i).toString());
            }	 
		 while(!valido) {		
			 if (s.hasNextInt()) {
				 opcion = s.nextInt();
				 s.nextLine();
				 if (opcion > 0 && opcion - 1 < lista.size()) {
					 idP = daoPelicula.obtenerID(lista.get(opcion-1));
					 valido = true;
				 }
				 else {
					 System.out.println("Se introdujo una posicion invalida.");
				 }
			 }
			 else {
				 System.out.println("Se debe ingresar un número.");
				 s.nextLine();
			 }
		 }
		 
		 valido = false;
		 //ingresar reseña
		 //calificacion
	        while (!valido) {
	            System.out.print("Ingrese la calificacion de la Pelicula [1-100] (obligatorio): ");
	            if(s.hasNextInt()) {
	            	r.setCalificacion(s.nextInt());
	            	s.nextLine();
	            	if( r.getCalificacion() > 0 &&  r.getCalificacion() <= 100) {
	            		System.out.println("Se ingresó: '" + r.getCalificacion() + "'. ¿Es correcto? (y/n)");
	            		res = s.next();
	            		s.nextLine();
	            		if(res.equalsIgnoreCase("y")) valido = true;
	            	} else {
	            		System.out.println("Se introdujo una calificacion inválida.");
	            	}
	        } else {
	        	System.out.println("La calificacion debe ser un numero.");
	        	s.nextLine();
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
	    if (lista.size() != 0) {
	    	int opcion = -1 ; 
	    	boolean valido = false;
	    	String res;
	    	for (int i = 0 ; i<lista.size() ; i++) {
	    		System.out.println("Reseña " + (i + 1) + ": " + lista.get(i).toString());
	    	}
	    	while (!valido) {
	    		System.out.println("Seleccionar una reseña (obligatorio): ");
	    		if (s.hasNextInt()) {
	    		opcion = s.nextInt();
	    		s.nextLine();
	    		if (opcion >0 && opcion - 1 < lista.size()) {
	    			r= lista.get(opcion-1);
	    			System.out.println("Se ingresó la reseña: '" + r.toString()+ "'. ¿Es correcto? (y/n)");
	    			res = s.next();
	    			s.nextLine();
	    			if (res.equalsIgnoreCase("y")) {
	    				valido = true; 
	    				rDAO.aprobarResenia(r);		           
	    		}
	    		}
	    		else {
	    			System.out.println("La reseña seleccionada no existe.");
	    		}
	    	}
	    	else {
	    			System.out.println("Se debe ingresar un número.");
	    			s.nextLine();
	    	}
	}
	    }
	    else System.out.println("No hay reseñas no aprobadas en la base de datos.");	       
}
}
