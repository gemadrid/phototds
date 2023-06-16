package umu.tds.persistencia;

import java.util.List;

import umu.tds.modelo.Publicacion;

public interface PublicacionDAO {
	
	void create(Publicacion publicacion);
	boolean delete(Publicacion publicacion);
	void update(Publicacion publicacion);
	Publicacion get(int id);
	List<Publicacion> getAll();

}
