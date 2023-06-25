package umu.tds.modelo;

import java.util.LinkedList;
import java.util.List;

import umu.tds.controlador.Controlador;

public class Album extends Publicacion {
	
	//Referencias
	private List<Publicacion> fotos;

	//Constructor
	public Album(String titulo, String descripcion, Usuario usuario) {
		super(titulo, descripcion, usuario);
		this.fotos = new LinkedList<>();
	}
	
	//Getters y setters
	public List<Publicacion> getFotos() {
		return fotos;
	}

	public void setFotos(List<Publicacion> fotos) {
		this.fotos = fotos;
	}

	//Métodos
	public void addFoto(Publicacion foto) {
		fotos.add(foto);
	}
	
	public void removeFoto(Publicacion foto) {
		fotos.remove(foto);
		//Si hemos eliminado la última foto que quedaba en el álbum
		if (fotos.isEmpty()) Controlador.INSTANCE.eliminarPublicacion(this);
	}
	
	@Override
	public void darMegusta() {
		super.darMegusta();
		for(Publicacion foto : fotos) {
			foto.darMegusta();
		}
	}

	@Override
	public String getPath() {
		return fotos.get(0).getPath();
	}

}
