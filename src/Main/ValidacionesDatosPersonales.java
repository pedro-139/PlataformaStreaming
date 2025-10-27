package Main;

import java.util.Scanner;

import Modelo.POO.DatosPersonales;
import clasesDAO.DatosPersonalesDAOjdbc;

public class ValidacionesDatosPersonales {
	public static DatosPersonales registrarDatosPersonales(Scanner s) {
        DatosPersonales d = new DatosPersonales();
        boolean valido;
        String res;

        // NOMBRE
        valido = false;
        while (!valido) {
            System.out.print("Ingrese el nombre: ");
            d.setNombre(s.nextLine());
            if(d.getNombre() != null && !d.getNombre().isBlank() && verificarNomApe(d.getNombre())) {
                System.out.println("Se ingresó: '" + d.getNombre() + "'. ¿Es correcto? (y/n)");
                res = s.next();
                s.nextLine(); 
                if(res.equalsIgnoreCase("y")) valido = true;
            } else {
                System.out.println("Se introdujo un nombre inválido. El nombre no debe contener numeros");
            }
        }

        // APELLIDO
        valido = false;
        while (!valido) {
            System.out.print("Ingrese el apellido: ");
            d.setApellido(s.nextLine());
            if(d.getApellido() != null && !d.getApellido().isBlank() && verificarNomApe(d.getApellido())) {
                System.out.println("Se ingresó: '" + d.getApellido() + "'. ¿Es correcto? (y/n)");
                res = s.next();
                s.nextLine(); 
                if(res.equalsIgnoreCase("y")) valido = true;
            } else {
                System.out.println("Se introdujo un apellido inválido. El apellido no debe contener numeros");
            }
        }

        // DNI
        valido = false;
        while (!valido) {
        	DatosPersonalesDAOjdbc dao = new DatosPersonalesDAOjdbc();
            System.out.print("Ingrese el DNI: ");
            
            if (s.hasNextInt()) {
            	d.setDNI(s.nextInt());
        	   if ( !(dao.existeDNI(d.getDNI()) ) ) { // si no existe en la BD        		   
        		   System.out.println("Se ingresó: '" + d.getDNI() + "'. ¿Es correcto? (y/n)");
        		   res = s.next();
        		   s.nextLine(); 
        		   if (res.equalsIgnoreCase("y")) {
        			   	valido = true;
        		   		
        		   }	
        	   } 	else {
        		   System.out.println("El DNI ya existe en la base de datos. Ingrese otro.");
        	   }
           }
            else {
            	 System.out.println("El DNI solo debe tener numeros. Ingrese otro.");
            }
        }
   
        return d;
    }
	
	public static boolean verificarNomApe(String n) {
  	    boolean valido = true;
  	    for (int i = 0; i < n.length();i++) {
  	    	if ( n.charAt(i) >= '0'  && n.charAt(i) <= '9') {
  	    		valido = false;
  	    		break;
  	    	}
  	    }
  	    return(valido);
  	}
}
