package servicio;

import dao.*;
import excepcion.*;
import factoryDAO.*;
import modelo.*;


public class ServiciosCliente {
	
	private  ClienteDAO clienteDAO;
    private  DatosPersonalesDAO dpDAO;

   
    public ServiciosCliente() {
    	this.clienteDAO = FactoryDAO.getClienteDAO();
    	this.dpDAO = FactoryDAO.getDatosPersonalesDAO();
    }

   
    public void registrarCliente(Cliente c, DatosPersonales dp) throws DatosIncompletosException, DatosYaExistenException {
    	boolean dpError = false;
    	boolean clienteError = false;
    	String errores = "";
    	// Validar DP
    	try {
    	    ServiciosDatosPersonales.registrarDatosPersonales(dp);
    	} catch (DatosIncompletosException e) {
    	    dpError = true;
    	    errores += e.getMessage();
    	}

    	// Validar Cliente
    	try {
    	    validarCliente(c);
    	} catch (DatosIncompletosException e) {
    	    clienteError = true;
    	    errores += e.getMessage() + "\n";
    	}

    	// Resolver los 3 casos sin ramas raras
    	if (dpError || clienteError) {
    	    throw new DatosIncompletosException(errores.trim());
    	}
    
        
        if (dpDAO.existeDNI(dp.getDNI())) {
            throw new DatosYaExistenException("El DNI ya está registrado.");
        }
        
        if(clienteDAO.existeEmail(c.getMail())) {
			throw new DatosYaExistenException("El Email ya está registrado.");
		}
        
     
        dpDAO.guardar(dp);

        int idDP = dpDAO.obtenerId(dp);
        if (idDP == -1) throw new RuntimeException("Error interno al obtener ID de Datos Personales.");
        else {
        c.setIdDP(idDP);
        clienteDAO.guardar(c);
        }
    }


    
    // Lógica para el login
    public Cliente obtenerCliente(String email, String contrasenia) throws DatosNoEncontradosException {
    	int id = clienteDAO.obtenerID(email, contrasenia);
    	if (id == -1) {
    		throw new DatosNoEncontradosException("Email o contraseña incorrectos.");
    	}
    	Cliente clienteLogueado = clienteDAO.encontrar(id);
    	return clienteLogueado;
    }

	
	public static void validarCliente(Cliente c) throws DatosIncompletosException {
	 
		 boolean valido= true;

		 String msg = "";
	 
		 // Validar nombre de usuario
		    
		 if((c.getNombre_usuario() == null || c.getNombre_usuario().isBlank())) {
			 msg+= "- El nombre de usuario no puede estar vacio. \n";
			 valido = false;
		 } 
	  

		 //Correo
	 	 
		 if(c.getMail() != null && !c.getMail().isBlank()) {
			 if(!(verificarCorreo(c.getMail()))) {
				 msg+= "- Se introdujo un correo inválido. Debe contener @ \n";
				 valido = false;
			 }
		 } else {
			 msg+="- El email no puede estar vacio. \n";
			 valido = false;
		 }
		 
		 //Contraseña
		 
		 if((c.getContrasenia() == null || c.getContrasenia().isBlank())) {
			 msg+= "- La contraseña no puede estar vacia";
			 valido = false;
		 }
		 

		 if(!valido) {
			 throw new DatosIncompletosException(msg);
		 }

	}

	
	
	public static void guardarCliente(Cliente c) {
	    	ClienteDAO cDAO = FactoryDAO.getClienteDAO();
	    	cDAO.guardar(c);
	    }
	public static void actualizarCliente(Cliente c) {
		ClienteDAO cDAO = FactoryDAO.getClienteDAO();
		cDAO.actualizar(c);
	}
	public DatosPersonales obtenerDatosPersonalesDelCliente(Cliente c) {
        return dpDAO.encontrar(c.getIdDP());
    }
	
	public static boolean verificarCorreo(String c) {
		boolean valido = false;
		if (c.contains("@")) {
		    valido = true;
		} 
	    return valido;
	}
}
