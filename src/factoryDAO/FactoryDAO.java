package factoryDAO;
import dao.ClienteDAO;
import dao.DatosPersonalesDAO;
import dao.PeliculaDAO;
import dao.ReseniaDAO;
import dao.implementacionJDBC.*;
public class FactoryDAO {

	public static DatosPersonalesDAO getDatosPersonalesDAO() {
		return new DatosPersonalesDAOjdbc();
	}
	public static ClienteDAO getClienteDAO() {
	return new ClienteDAOjdbc();
	}
	public static PeliculaDAO getPeliculaDAO() {
		return new PeliculaDAOjdbc();
	}
	public static ReseniaDAO getReseniaDAO() {
		return new ReseniaDAOjdbc();
	}
}
