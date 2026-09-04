package excepcion;

@SuppressWarnings("serial")
public class DatosIncompletosException extends Exception{
	public DatosIncompletosException(String mensaje)  {
		super(mensaje);
	}
}
