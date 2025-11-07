package servicios;


import dao.implementacionJDBC.*;
import Modelo.poo.DatosPersonales;
public class ValidacionesDatosPersonales {
	public static String  registrarDatosPersonales(DatosPersonales d) {
		boolean valido = true;
    	String msg = "Se introdujeron datos invalidos: \n";
    	DatosPersonalesDAOjdbc dao = new DatosPersonalesDAOjdbc(); 
        // NOMBRE
  
        if(d.getNombre() != null && !d.getNombre().isBlank()) {
            	if (!verificarNomApe(d.getNombre())) {
            		msg +="- Se introdujo un nombre inválido. El nombre no debe contener números\n";
            		valido = false;
            }
        }else {
        	msg +="- Se introdujo un nombre inválido. El campo no puede estar vacio\n";
            	valido = false;
            }
        // APELLIDO
   
        if(d.getApellido() != null && !d.getApellido().isBlank()) {
            	if (!verificarNomApe(d.getApellido())) {
            		msg +="- Se introdujo un apellido inválido. El apellido no debe contener numeros\n";
            		valido = false;
            	}
        }	else {
        	msg +="- Se introdujo un apellido inválido. El campo no puede estar vacio\n";
            	valido = false;
            }
        

        // DNI

       
        if (d.getDNI() <= 0) {
            msg += "- El DNI no puede ser menor o igual a cero. \n ";
            valido = false;
        } else if (dao.existeDNI(d.getDNI())) {
            msg += "- El DNI ya existe en la base de datos. Ingrese otro. \n ";
            valido = false;
        }
        if (valido) {
        	dao.guardar(d);
        	msg = "Datos Personales creado correctamente";
        }
   
        return msg;
    }
	
	public static boolean verificarNomApe(String n) { // Devuelve true si el nombre/apellido no contiene numeros.
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
