package dao;
import java.util.List;

import Modelo.poo.*;

public interface DatosPersonalesDAO {

List<DatosPersonales> listar();
void eliminar(DatosPersonales d);
DatosPersonales encontrar (int id);
void guardar(DatosPersonales d);
public boolean existeDNI(int i);
public int obtenerId(DatosPersonales d);
}
