package main;

import db.*;
import servicio.*;
import vista.*;

import java.sql.Connection;

import controlador.*;

public class MiApp {

	public MiApp() {

	}
	public void iniciar() {
		Connection con = ConexionSQLite.getCon();
		try {
		 CrearTabla.creacionDeTablasEnBD(con);
		} catch (Exception e) {
		 System.out.println("Error creando tablas: " + e.getMessage());
		}
		LoginVista vista = new LoginVista();

		ServiciosCliente serv = new ServiciosCliente();

		new LoginControlador(vista, serv);

		vista.setVisible(true);

	}

	
}