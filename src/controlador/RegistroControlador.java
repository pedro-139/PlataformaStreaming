package controlador;

import excepcion.DatosIncompletosException;
import excepcion.DatosYaExistenException; // Usada para validación de DNI/Email único
import modelo.*;
import servicio.*;
import vista.*;

import java.awt.event.*;
import javax.swing.JOptionPane;


public class RegistroControlador  {
    
    private RegistroVista vista;
    private ServiciosCliente modelo; 
     

    public RegistroControlador(RegistroVista vista,ServiciosCliente modelo) {
        this.vista = vista;
        this.modelo = modelo;
    
        
        this.vista.getBtnRegistro().addActionListener(new RegistroItemListener()); //guarda los datos en la base de datos.
        this.vista.getBtnVolver().addActionListener(new VolverItemListener()); //vuelve a la ventana de login
        
    }
	private class RegistroItemListener implements ActionListener {
    @Override
    	public void actionPerformed(ActionEvent e) {
        // Objetos que se van a llenar con los datos de la vista
        DatosPersonales datosP = new DatosPersonales();
        Cliente cliente = new Cliente();
        
        try {
        	//OBTENER Y VALIDAR DATOS DE ENTRADA (Validación de formato/vacío)
        	//  Carga de Datos
        	datosP.setNombre(vista.getTxtNombre());
        	datosP.setApellido(vista.getTxtApellido()); 
        	int dni;
        	if (vista.getTxtDNI().isEmpty()) {
                dni = 0; 
            } else {
                // Si tiene letras, salta NumberFormatException.
                dni = Integer.parseInt(vista.getTxtDNI());
            }
            datosP.setDNI(dni);
            cliente.setNombre_usuario(vista.getTxtNombreUsuario());
            cliente.setMail(vista.getTxtEmail());
            cliente.setContrasenia(vista.getTxtContrasenia());
            cliente.setPrimeraVez(true); // Por defecto, se pone en false luego de iniciar sesión por primera vez.
            
            modelo.registrarCliente(cliente, datosP);
            vista.mostrarMensaje("¡Registro exitoso! Ya puedes iniciar sesión.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            vista.dispose(); // Cierra la ventana de registro
            
            //abrimos ventana de login
            LoginVista loginVista = new LoginVista();
            ServiciosCliente loginModelo = new ServiciosCliente();
            new LoginControlador(loginVista, loginModelo);
            
        } catch (NumberFormatException ex) {
        	vista.mostrarMensajeError("El DNI debe ser un valor numérico.");
        } catch (DatosYaExistenException ex) {      
            vista.mostrarMensajeError(ex.getMessage());     
        } catch (DatosIncompletosException ex) {           
            vista.mostrarMensajeError(ex.getMessage());     
        } catch (Exception ex) {
        	// Error grave
        	vista.mostrarMensajeError("Error interno del sistema: " + ex.getMessage());
        }
    }
}
	private class VolverItemListener implements ActionListener {
	    @Override
	    	public void actionPerformed(ActionEvent e) {
	    	vista.dispose();
	    	LoginVista loginVista = new LoginVista();
	    	ServiciosCliente loginModelo = new ServiciosCliente();
	    	new LoginControlador(loginVista, loginModelo);
	    }
	}
}


      