package umu.tds.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;

public enum CatalogoPublicaciones {
	INSTANCE;
	
	//private FactoriaDAO factoria;
	
	private HashMap<Integer, Publicacion> publicacionesPorID;
	
	//Constructor
	private CatalogoPublicaciones() {
		publicacionesPorID = new HashMap<Integer, Publicacion>();
		
		try {
			FactoriaDAO factoria = FactoriaDAO.getInstancia();
			//Obtenemos todas las publicaciones
			List<Publicacion> listaPublicaciones = factoria.getPublicacionDAO().getAll();
			for (Publicacion publicacion : listaPublicaciones) {
				publicacionesPorID.put(publicacion.getCodigo(), publicacion);
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	

	
	//Métodos
	public Publicacion findPublicacion(int id) {
		return publicacionesPorID.get(id);
	}
	
	public void addPublicacion(Publicacion publicacion) {
		publicacionesPorID.put(publicacion.getCodigo(), publicacion);
	}
	
	public void removePublicacion(Publicacion publicacion) {
		publicacionesPorID.remove(publicacion.getCodigo());
	}

}
