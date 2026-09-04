package servicio;



import excepcion.*;
import modelo.DatosPersonales;


public class ServiciosDatosPersonales {

	
 	
	
	public static void  registrarDatosPersonales(DatosPersonales d) throws DatosIncompletosException {
		boolean valido = true;
		String msg = "";
		
		// NOMBRE
		
		if(d.getNombre() != null && !d.getNombre().isBlank()) {
			if (!verificarNomApe(d.getNombre())) {
				msg +="- El nombre no puede contener números\n";
				valido = false;
			}
		}else {
			msg +="- El nombre no puede estar vacio\n";
			valido = false;
		}
		// APELLIDO
		
		if(d.getApellido() != null && !d.getApellido().isBlank()) {
			if (!verificarNomApe(d.getApellido())) {
				msg +="- El apellido no puede contener numeros\n";
				valido = false;
			}
 	      }	else {
 	    	  msg +="- El apellido no puede estar vacio\n";
 	    	  valido = false;
 	      }
		

        // DNI
		if (d.getDNI() <= 0) {
			msg += "- El DNI no puede ser menor o igual a cero.\n";
			valido = false;
		} 
		
		if (!valido) {
			throw new DatosIncompletosException("Validación fallida. Revise formatos/campos.\n"+msg);       	
		}
	}

	
	
	// Devuelve true si el nombre/apellido no contiene numeros.
	public static boolean verificarNomApe(String n) { 
  	    boolean valido = true;
  	    for (int i = 0; i < n.length();i++) {
  	    	if ( n.charAt(i) >= '0'  && n.charAt(i) <= '9') {
  	    		valido = false;
  	    		break;
  	    	}
  	    }
  	    return valido;
	}
	
}
