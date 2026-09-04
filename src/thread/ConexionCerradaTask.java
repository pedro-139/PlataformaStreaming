package thread;

import db.ConexionSQLite;

public class ConexionCerradaTask implements Runnable{
	
	@Override
   public void run() {
	    ConexionSQLite.cerrarConexion();
        }    
}

