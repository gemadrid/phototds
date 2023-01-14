package umu.tds.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public enum CatalogoPublicaciones {
	INSTANCE;
	
	//private FactoriaDAO factoria;
	
	//private HashMap<String, Publicacion> publicaciones;
	private List<Publicacion> publicaciones;
	
	//TODO Ver qué tipo de contenedor utilizar (HashMap con código generado por la FactoriaDAO)
	//Constructor
	private CatalogoPublicaciones() {
		//publicaciones = new HashMap<String, Publicacion>();
		publicaciones = new ArrayList<Publicacion>();
	}
	
	//public List<Publicacion> findPublicaciones() throws DAOException
	
	//Métodos
	//public Publicacion findPublicacion() 
	public void addPublicacion(Publicacion publicacion) {
		publicaciones.add(publicacion);
	}

}
