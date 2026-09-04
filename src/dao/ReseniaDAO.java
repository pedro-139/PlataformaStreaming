package dao;

import java.util.List;

import modelo.*;

public interface ReseniaDAO {
	List<Resenia> listarNoAprobadas();
	void eliminar(Resenia r);
	Resenia encontrar (int id);
	void guardar (Resenia r);
	void aprobarResenia(Resenia r);
	boolean clienteCalificoPelicula(int idpeli, int idcli);
	boolean clienteCalificoAlgo(int idCliente);
}
