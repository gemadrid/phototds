package umu.tds.modelo;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
	
	
	//Métodos get
	public List<String> getHashtags(String hashtag) {
		String cadena = normalizar(hashtag);
		return publicacionesPorID.values().stream()
				.flatMap(p -> p.getHashtags().stream())
				.filter(h -> normalizar(h).contains(cadena))
				.distinct()
				.collect(Collectors.toList());
	}
	
	public List<Publicacion> getPublicacionesHashtag(String hashtag) {
		return publicacionesPorID.values().stream()
				.filter(p -> p.getHashtags().stream()
								.anyMatch(h -> normalizar(h).equals(hashtag)))
				.collect(Collectors.toList());
	}
	
	private String normalizar(String cadena) {
		return Normalizer.normalize(cadena, Form.NFD)
				.replaceAll("\\p{M}", "")
				.toLowerCase();
	}

}
