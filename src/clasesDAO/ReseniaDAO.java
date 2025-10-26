package clasesDAO;
import Modelo.POO.*;
import java.util.List;
public interface ReseniaDAO {
	List<Resenia> listarNoAprobadas();
	void eliminar(Resenia r);
	Resenia encontrar (Long id);
	void guardar (Resenia r);
	void aprobarResenia(Resenia r);
}
