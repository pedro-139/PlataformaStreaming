package Main;

import DB.*;
import clasesDAO.*;
import Modelo.POO.*;
import java.util.Scanner;
import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
    	
	    boolean valido = false;
	    String res;
	    int opcion = -1;
	    Scanner s = new Scanner(System.in);
	    Connection con = ConexionSQLite.getCon();
	    
	    try {
	        CrearTabla.creacionDeTablasEnBD(con); 
	    } catch (Exception e) {
	        System.out.println("Error creando tablas: " + e.getMessage());
	    }
	        	
	    while (opcion != 0) {
	        System.out.println("\n=== MENU PRINCIPAL ===");
	        System.out.println("Seleccione una opción: ");
	        System.out.println("0. Salir");
	        System.out.println("1. Registrar Datos Personales ");
	        System.out.println("2. Registrar Cliente ");
	        System.out.println("3. Registrar Pelicula ");
	        System.out.println("4. Listar Clientes ");
	        System.out.println("5. Listar Peliculas ");
	        System.out.println("6. Registrar Reseña ");
	        System.out.println("7. Aprobar Reseña ");	  
	        
	        if (s.hasNextInt()) {
	        	opcion =s.nextInt();
	        	s.nextLine();
	        	if (opcion <0 || opcion >7) System.out.println("Opción inválida.");
	        	else {
	        		switch (opcion) {
	        		case 0: {
	        			System.out.println("Saliendo...");
	        			break;
	        		}
	        		case 1:{ 
	        			DatosPersonales d;
	        			DatosPersonalesDAO dDAO = FactoryDAO.getDatosPersonalesDAO();
	        			valido = false;
	        			while(!valido){
	        				d = ValidacionesDatosPersonales.registrarDatosPersonales(s);
	        				System.out.println("Los datos ingresados son: '" +d.toString() + "'. ¿Es correcto? (y/n)");
	        				res = s.next();
	        				s.nextLine(); 
	        				if (res.equalsIgnoreCase("y")) {
	        					valido = true;
	        					dDAO.guardar(d);
	        				}  
	        			}
	        			break;
	        		}
	        		case 2: {
	        			Cliente c;
	        			ClienteDAO cDAO = FactoryDAO.getClienteDAO();
	        			valido = false;
	        			while(!valido){
	        				c = ValidacionesCliente.registrarCliente(s);
	        				System.out.println("Los datos ingresados son: '" +c.toString() + "'. ¿Es correcto? (y/n)");
	        				res = s.next();
	        				s.nextLine(); 
	        				if (res.equalsIgnoreCase("y")) {
	        					valido = true;
	        					cDAO.guardar(c);
	        				}  
	        			}
	        			break;
	        		}
	        		case 3:{ 
	        			Pelicula p;
	        			PeliculaDAO pDAO = FactoryDAO.getPeliculaDAO();                
	        			valido = false;
	        			while(!valido){
	        				p = ValidacionesPelicula.registrarPelicula(s);
	        				System.out.println("Los datos ingresados son: '" +p.toString() + "'. ¿Es correcto? (y/n)");
	        				res = s.next();
	        				s.nextLine(); 
	        				if (res.equalsIgnoreCase("y")) {
	        					valido = true;
	        					pDAO.guardar(p);
	        				}  
	        			} 
	        			break;
	        		}
	        		case 4:{        		
	        			List<Cliente> lista = ValidacionesCliente.listarClientes(s);
	        			DatosPersonalesDAO dDao = FactoryDAO.getDatosPersonalesDAO();
	        			DatosPersonales d = new DatosPersonales();
	        			if(lista.size() != 0) {
	        				for (int i = 0; i<lista.size(); i++) {    	
	        					System.out.print(" Cliente " + (i + 1) + ": " + lista.get(i).toString());
	        					d = dDao.encontrar(lista.get(i).getIdDP());
	        					System.out.print(" Datos Personales: " + d.toString() +"\n");
	        					
	        				}	
	        			}
	        			else  System.out.println("No hay clientes en la base de datos.");	        		
	        			break;
	        		}
	        		case 5:{
	        			List<Pelicula> lista = ValidacionesPelicula.listarPeliculas(s); 
	        			if(lista.size() != 0) {
	        				for (int i = 0; i<lista.size(); i++) {
	        					System.out.println("Pelicula " + (i + 1) + ": " + lista.get(i).toString());
	        				}
	        			}
	        			else System.out.println("No hay peliculas en la base de datos.");	            
	        			break;
	        		}
	        		case 6:{ 
	        			Resenia r;
	        			ReseniaDAO rDAO = FactoryDAO.getReseniaDAO();
	        			valido = false;
	        			while(!valido){
	        				r = ValidacionesResenia.registrarResenia(s);
	        				System.out.println("Los datos ingresados son: '" +r.toString() + "'. ¿Es correcto? (y/n)");
	        				res = s.next();
	        				s.nextLine(); 
	        				if (res.equalsIgnoreCase("y")) {
	        					valido = true;
	        					rDAO.guardar(r);
	        				}  
	        			}
	        			break;
	        		}
	        		case 7 :{ 
	        			ValidacionesResenia.aprobarResenia(s);
	        			break;
	        		}
	        		default :{
	        			System.out.println("Opción inválida");
	        		} 
	        		
		       		} // Salida del switch
	        		
	        	} // Salida del else, si opcion esta entre 0 y 7.
	        	
	        } // Salida del if(s.hasNextInt())
	        
	        else {
	        	System.out.println("Entrada inválida. Debe ser un número.");
	        	s.nextLine();    
	        }
	        
	    } // Salida del while   
	    
	    s.close();
	        
    } // Salida del static main
    
} // Salida del Main



