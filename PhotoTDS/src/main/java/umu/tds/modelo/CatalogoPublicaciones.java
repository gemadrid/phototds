package umu.tds.modelo;

import java.util.HashMap;

public enum CatalogoPublicaciones {
	INSTANCE;
	
	//private FactoriaDAO factoria;
	
	private HashMap<String, Publicacion> publicaciones;
	
	//Constructor
	private CatalogoPublicaciones() {
		publicaciones = new HashMap<String, Publicacion>();
	}
	
	//public List<Publicacion> findPublicaciones() throws DAOException
	
	//Métodos
	//public Publicacion findPublicacion() 

}
