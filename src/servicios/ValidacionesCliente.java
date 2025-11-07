package servicios;

import java.util.List;
import java.util.Scanner;

import comparador.*;
import Modelo.poo.*;
import dao.*;
import factoryDAO.FactoryDAO;

public class ValidacionesCliente {
	public static boolean verificarCorreo(String c) {
		boolean valido = false;
		if (c.contains("@")) {
		    valido = true;
		} 
	    return(valido);
	}
	
	public static String registrarCliente(Cliente c) {
		 DatosPersonalesDAO daoDatos = FactoryDAO.getDatosPersonalesDAO();
		 ClienteDAO daoC = FactoryDAO.getClienteDAO();
	//	 List <DatosPersonales> lista = daoDatos.listar();
		 boolean valido= true;
		 int opcion;
		 String res;
		 String msg ="Error: \n";
	 
		 // Validar nombre de usuario
		    
		 if(!(c.getNombre_usuario() != null || !c.getNombre_usuario().isBlank())) {
			 msg+= "- Se introdujo un nombre de usuario inválido, el campo no puede estar vacio. \n";
			 valido = false;
		 } 
	  
		 //Correo
	 	 
		 if(c.getMail() != null && !c.getMail().isBlank()) {
			 if(!(verificarCorreo(c.getMail()))) {
				 msg+= "- Se introdujo un correo inválido. \n";
				 valido = false;
			 }
		 } else {
			 msg+="Se introdujo un correo inválido, el campo no debe estar vacio";
			 valido = false;
		 }
		 
		 //Contraseña
		 
		 if(!(c.getContrasenia() != null || !c.getContrasenia().isBlank())) {
			 msg+= "Se introdujo una contraseña inválida, el campo no debe estar vacio";
			 valido = false;
		 }
		 
		 if(valido) {
			 msg = "Cliente creado correctamente";
		 }
		 return msg;
	}
	

	public static List<Cliente> listarClientes(int opcion){
		 ClienteDAO cDAO = FactoryDAO.getClienteDAO();
		 List<Cliente> lista = cDAO.listar();
         switch (opcion) {
         case 0 : {
        	 break;
         }
         case 1:{
        	 lista.sort(new ComparadorMail());
        	 break;
         }
         case 2:{  
        	 lista.sort(new ComparadorNombreUsuario());
        	 break;
         }
         default :{
        	 System.out.println("Opción inválida");
         }           
         }
             return lista;

	}
}
