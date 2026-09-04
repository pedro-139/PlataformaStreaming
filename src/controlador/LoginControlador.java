package controlador;

import java.awt.event.*;

import excepcion.*;
import modelo.*;
import servicio.*;
import vista.*;

public class LoginControlador {
	private LoginVista vista;
	private ServiciosCliente servCliente; 

	public LoginControlador(LoginVista vista, ServiciosCliente modelo) {
		this.vista = vista;
		this.servCliente = modelo;
		this.vista.getBtnLogin().addActionListener(new LoginItemListener());
		this.vista.getBtnRegistro().addActionListener(new RegistroItemListener());
	}

	// Lógica del botón Login
	private class LoginItemListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evento) {
			Cliente clienteLogueado = null;
			String email = vista.getTxtEmail();
			String contrasenia = vista.getTxtContrasenia();
			try {
				
				clienteLogueado = servCliente.obtenerCliente(email, contrasenia);

				DatosPersonales dp = servCliente.obtenerDatosPersonalesDelCliente(clienteLogueado);


				vista.dispose(); // Cierra la ventana actual
				
				
				BienvenidaVista vista = new BienvenidaVista();
				ServiciosPelicula modelo = new ServiciosPelicula();
				BienvenidaControlador controlador = new BienvenidaControlador (vista, modelo,dp,clienteLogueado);
				vista.setVisible(true); 
				controlador.iniciarCargaDeContenido(clienteLogueado);       
			} catch (DatosNoEncontradosException e) {	
				vista.mostrarMensajeError(e.getMessage());
			} catch (Exception e) {
				vista.mostrarMensajeError("Error interno del sistema. Verifique la conexión a la base de datos: " + e.getMessage());
			}
		}
	}

	// Lógica del botón REGISTRO 
	private class RegistroItemListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evento) {
			// Cierra la ventana de Login
	        vista.dispose(); 
	        
	        // Instanciar la nueva Vista de Registro
	        RegistroVista registroVista = new RegistroVista();
	        ServiciosCliente registroModelo = new ServiciosCliente();
	        new RegistroControlador(registroVista, registroModelo);
	        
	        
		}
	}
}
