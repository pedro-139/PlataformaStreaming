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
	    Scanner s = new Scanner(System.in);
	    Connection con = ConexionSQLite.getCon();
	    
	    try {
	        CrearTabla.creacionDeTablasEnBD(con); 
	    } catch (Exception e) {
	        System.out.println("Error creando tablas: " + e.getMessage());
	    }
	        	
	    int opcion = -1;

	    do {
	    	System.out.println("Este mensaje es nuevo");
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
	          
	        opcion = s.nextInt();
	        s.nextLine();

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
	            for (int i = 0; i<lista.size(); i++) {
	                System.out.println("Cliente " + (i + 1) + ": " + lista.get(i).toString());
	            }
		        break;
		    }
	        case 5:{
	            List<Pelicula> lista = ValidacionesPelicula.listarPeliculas(s); 
	            for (int i = 0; i<lista.size(); i++) {
	                System.out.println("Pelicula " + (i + 1) + ": " + lista.get(i).toString());
	            }
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
	        }   
	    }  while (opcion != 0) ;
	    s.close();
    }
}

