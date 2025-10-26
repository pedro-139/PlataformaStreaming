package clasesDAO;
import java.util.List;

import Modelo.POO.*;

public interface DatosPersonalesDAO {

List<DatosPersonales> listar();
void eliminar(DatosPersonales d);
DatosPersonales encontrar (Long id);
void guardar(DatosPersonales d);
public boolean existeDNI(int i);
public int obtenerId(DatosPersonales d);
}
