package excepcion;

@SuppressWarnings("serial")
public class DatosYaExistenException extends Exception {  
    public DatosYaExistenException(String mensaje) {
        super(mensaje);
    }
}