package main;

import db.*;
import factoryDAO.FactoryDAO;
import dao.*;
import Modelo.poo.*;
import comparador.ComparadorMail;
import comparador.ComparadorNombreUsuario;

import java.util.Scanner;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import servicios.*;
public class Main {
    public static void main(String[] args) {
    	
	    boolean valido = false;
	    boolean ok =false;
	    String res;
		String msg;
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
	        			DatosPersonales d = new DatosPersonales();
	        			valido = false;
						ok =false;
	        			while(!valido){				
	        				// NOMBRE
	        				System.out.print("Ingrese el nombre (obligatorio): ");
    						d.setNombre(s.nextLine());
							// APELLIDO
        					System.out.print("Ingrese el apellido (obligatorio): ");
        					d.setApellido(s.nextLine());
 					       // DNI
						   while(!ok){
							   System.out.print("Ingrese el DNI (obligatorio): ");
							   if (s.hasNextInt()) {
								   d.setDNI(s.nextInt());
								   s.nextLine();
								   ok = true;
							   } else {
								   System.out.println("El DNI solo debe tener numeros. Ingrese otro.");
								   s.nextLine();
							   }
						   }
						   msg = ValidacionesDatosPersonales.registrarDatosPersonales(d);
						   if (msg.equals("Datos Personales creado correctamente")){
							   System.out.println("Los datos ingresados son: '" +d.toString() + "'. ¿Es correcto? (y/n)");
							   res = s.next();
							   s.nextLine(); 
							   if (res.equalsIgnoreCase("y")) 
								   valido = true;
							   	   //guardar en base de datos
						   }
						   else {
							   System.out.println(msg);
							   ok = false;
						   }
	        			}
	        			msg = "";
	        			break;
	        		}		
	        		
	        		case 2: {
	        			Cliente c = new Cliente();
	        			DatosPersonalesDAO daoDatos = FactoryDAO.getDatosPersonalesDAO();        			
	        			List <DatosPersonales> lista = daoDatos.listar();
	        			valido = false;
	        			while(!valido){
	        				
	        				// Seleccionar un dato personal de la base de datos.
	        				
	        				while (!ok) {
	        					System.out.println("Elegir una persona (obligatorio): ");
	        					for(int i = 0; i<lista.size(); i++) {
	        						System.out.println((i + 1) + ": " + lista.get(i).toString());
	        					}    
	        					if (s.hasNextInt()) {
	        						opcion = s.nextInt();
	        						s.nextLine();
	        						if(opcion > 0 && opcion - 1<lista.size()) {        			    		         
	        							c.setIdDP(daoDatos.obtenerId(lista.get(opcion - 1)));       
	        							ok = true;
	        						}
	        						else System.out.println("Se introdujo una opcion inválido.");	            	
	        					} else {
	        						System.out.println("Se debe introducir un numero.");
	        						s.nextLine();
	        					}   
	        				}
	        				ok = false;
	        				
	        				 // Ingresar nombre de usuario
	        				
	        				System.out.print("Ingrese el nombre de usuario (obligatorio): ");
	        				c.setNombre_usuario(s.nextLine());    		
	        				
	        				//Correo	
	        				
	        				System.out.print("Ingrese el correo (obligatorio): ");
	        				c.setMail(s.nextLine());		
	        				
	        				//Contraseña    
	        				
	        				System.out.print("Ingrese la contraseña (obligatorio): ");
	        				c.setContrasenia(s.nextLine());		
	        				
	        				// Validar Cliente	
	        				
	        				msg = ValidacionesCliente.registrarCliente(c);
	        				if(msg.equals("Cliente creado correctamente")) {
	        					System.out.println("Los datos ingresados son: '" +c.toString() + "'. ¿Es correcto? (y/n)");
		        				res = s.next();
		        				s.nextLine(); 
		        				if (res.equalsIgnoreCase("y")) {
		        					valido = true;
		        					
		        					
		        					ClienteDAO cDAO = FactoryDAO.getClienteDAO();
		        					cDAO.guardar(c);// guardar en base de datos.
		        				}  
	        				}
	        				else {
	        					System.out.println(msg);
	        					ok = false;
	        				}
	        			}
	        			msg = "";
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
	        			boolean termino = false;
	        			List<Cliente> lista = new LinkedList<Cliente>();
	        			int i;
	        			 do {
	        				 System.out.println("Ordenar la lista por: ");
	        				 System.out.println("0. Ningun orden");
	        				 System.out.println("1. Mail ");
	        				 System.out.println("2. Nombre de usuario");
	        				 System.out.print("Ingrese una opción: ");
	        				 if (s.hasNextInt()) {	        		            	         		             
	        					 i = s.nextInt();
	        					 s.nextLine();
	        					 
	        					 switch (i) {
	        					 case 0: {
	        						 lista = ValidacionesCliente.listarClientes(i);
	        						 System.out.println("Lista de clientes (sin orden): ");
	        						 termino = true;
	        						 break;
	        					 }
	        					 case 1:{
	        						 lista = ValidacionesCliente.listarClientes(i);
	        						 System.out.println("Lista de clientes (ordenado por Mail): ");
	        						 termino = true;
	        						 break;
	        					 }
	        					 case 2:{  
	        						 lista = ValidacionesCliente.listarClientes(i);
	        						 System.out.println("Lista de clientes (ordenado por nombre): ");
	        						 termino = true;
	        						 break;
	        					 }
	        					 default :
	        						 System.out.println("Opción inválida");		      					 
	        					 }
	        				 } else {
	        					 System.out.println("Opcion invalida. Se debe ingresar un numero");
	        					 s.nextLine();
	        				 }      		            
	        			 } while (!termino);
	        			 
	        			 if(lista.isEmpty()) {
	        				 System.out.println("No hay clientes en la base de datos.");	 
	        			 }
	        			 else {
	        				for (int j = 0; j<lista.size(); j++) {    	
	        					System.out.print(" Cliente " + (j + 1) + ": " + lista.get(j).toString());
	        		//			d = dDao.encontrar(lista.get(i).getIdDP());
	        				//	System.out.print(" Datos Personales: " + d.toString() +"\n"); 					
	        				}	
	        			}
	        			        		
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
	    ConexionSQLite.cerrarConexion();
	        
    } // Salida del static main
    
} // Salida del Main



