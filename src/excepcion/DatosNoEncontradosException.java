package excepcion;

@SuppressWarnings("serial")
public class DatosNoEncontradosException extends Exception {
	public DatosNoEncontradosException(String mensaje) {
		super(mensaje);
	}
}

