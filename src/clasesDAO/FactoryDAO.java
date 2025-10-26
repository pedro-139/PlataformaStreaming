package clasesDAO;

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
