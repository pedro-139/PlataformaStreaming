package Main;

import java.util.List;
import java.util.Scanner;

import Comparadores.ComparadorMail;
import Comparadores.ComparadorNombreUsuario;
import Modelo.POO.*;
import clasesDAO.*;

public class ValidacionesCliente {
	public static boolean verificarCorreo(String c) {
		boolean valido = false;
		if (c.contains("@")) {
		    valido = true;
		} 
	    return(valido);
	}
	
	public static Cliente registrarCliente(Scanner s) {
		 Cliente u = new Cliente();
		 DatosPersonalesDAO daoDatos = FactoryDAO.getDatosPersonalesDAO();
		 List <DatosPersonales> lista = daoDatos.listar();
		 boolean valido= false;
		 int opcion;
		 String res;
		 
		 // Seleccionar un dato personal de la base de datos.
		 System.out.println("Elegir una persona (obligatorio): ");
         for(int i = 0; i<lista.size(); i++) {
           	 System.out.println((i + 1) + ": " + lista.get(i).toString());
            }
		 while (!valido) {    
	            if (s.hasNextInt()) {
	            	opcion = s.nextInt();
	            	s.nextLine();
	            	if(opcion > 0 && opcion - 1<lista.size()) {
	            		System.out.println("Se seleccionó: '" +lista.get(opcion-1).toString() + "'. ¿Es correcto? (y/n)");
	            		res = s.next();
	            		s.nextLine(); 
	            		if(res.equalsIgnoreCase("y")) {
	            			valido = true;            
	            			u.setIdDP(daoDatos.obtenerId(lista.get(opcion - 1)));             	
	            		}
	            	} else System.out.println("Se introdujo una opcion inválido.");	            	
	            }
	            	else {
	            		System.out.println("Se debe introducir un numero.");
	            		s.nextLine();
	            	}   
	        }
		 
		 // Ingresar nombre de usuario
		 valido = false;
		 
	        while (!valido) {
	            System.out.print("Ingrese el nombre de usuario (obligatorio): ");
	            u.setNombre_usuario(s.nextLine());
	            
	            if(u.getNombre_usuario() != null && !u.getNombre_usuario().isBlank()) {
	                System.out.println("Se ingresó: '" + u.getNombre_usuario() + "'. ¿Es correcto? (y/n)");
	                res = s.next();
	                s.nextLine();
	                if(res.equalsIgnoreCase("y")) valido = true;
	            } else {
	                System.out.println("Se introdujo un nombre inválido.");
	            }
	        }
		 //Correo
	   	 valido = false;
		 
	        while (!valido) {
	            System.out.print("Ingrese el correo (obligatorio): ");
	            u.setMail(s.nextLine());
	          
	            if(u.getMail() != null && !u.getMail().isBlank()) {
	            	if(  verificarCorreo(u.getMail())) {
	            		System.out.println("Se ingresó: '" + u.getMail() + "'. ¿Es correcto? (y/n)");
	            		res = s.next();
	            		s.nextLine();
	            		if(res.equalsIgnoreCase("y")) valido = true;
	            	}
	            	else System.out.println("Se introdujo un correo inválido.");
	            } else {
	                System.out.println("Se introdujo un correo inválido. El campo no debe estar vacio");
	            }
	        }
	   	 //Contraseña
		   	 valido = false;
			 
		        while (!valido) {
		            System.out.print("Ingrese la contraseña (obligatorio): ");
		            u.setContrasenia(s.nextLine());
		            
		            if(u.getContrasenia() != null && !u.getContrasenia().isBlank()) {
		                	System.out.println("Se ingresó: '" + u.getContrasenia() + "'. ¿Es correcto? (y/n)");
		                	res = s.next();
		                	s.nextLine();
		                	if(res.equalsIgnoreCase("y")) valido = true;
		            }	 else {
		            	System.out.println("Se introdujo una contraseña inválida. El campo no debe estar vacio");
		            }
		        }
		 return u;
	}
	

	public static List<Cliente> listarClientes(Scanner s){
		 ClienteDAO cDAO = FactoryDAO.getClienteDAO();
		 List<Cliente> lista = cDAO.listar();
		 int opcion;
		 boolean termino = false;
        do {
       	  System.out.println("Ordenar la lista por: ");
             System.out.println("0. Ningun orden");
             System.out.println("1. Mail ");
             System.out.println("2. Nombre de usuario");              
             if (s.hasNextInt()) {
            	 
             
              opcion = s.nextInt();
              s.nextLine();        
            	  switch (opcion) {
             		case 0: {
             			System.out.println("Lista de clientes (sin orden): ");
             			termino = true;
             			break;
             		}
             		case 1:{
             			lista.sort(new ComparadorMail());
             			System.out.println("Lista de clientes (ordenado por Mail): ");
             			termino = true;
             		break;
             		}
             		case 2:{  
             			lista.sort(new ComparadorNombreUsuario());
             			System.out.println("Lista de clientes (ordenado por nombre): ");
             			termino = true;
             		break;
             		}
             		default :{
             			System.out.println("Opción inválida");
	                    } 
             
              }
             } else {
            	 System.out.println("Opcion invalida. Se debe ingresar un numero");
            	 s.nextLine();
             }
            
         } while (!termino);
             return lista;

	}
}
